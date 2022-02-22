package study.thread;

import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPoolExecutor extends AbstractExecutorService {
    // 初始化状态和数量，状态为RUNNING，线程数为0
    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    // 前3位表示状态，所有线程数占29位
    private static final int COUNT_BITS = Integer.SIZE - 3;
    // 线程池容量
    private static final int CAPACITY = (1 << COUNT_BITS) - 1;  // 00011111111111111111111111111111

    // 可以接受新的任务，也可以处理阻塞队列里的任务
    private static final int RUNNING = -1 << COUNT_BITS;    // 111 00000000000000000000000000000
    // 不接受新的任务，但是可以处理阻塞队列里的任务
    private static final int SHUTDOWN = 0 << COUNT_BITS;    // 000 00000000000000000000000000000
    // 不接受新的任务，不处理阻塞队列里的任务，中断正在处理的任务
    private static final int STOP = 1 << COUNT_BITS;        // 001 00000000000000000000000000000
    // 过渡状态，也就是说所有的任务都执行完了，当前线程池已经没有有效的线程，这个时候线程池的状态将会TIDYING，并且将要调用terminated方法
    private static final int TIDYING = 2 << COUNT_BITS;     // 010 00000000000000000000000000000
    // TERMINATED：终止状态。terminated方法调用完成以后的状态
    private static final int TERMINATED = 3 << COUNT_BITS;  // 011 00000000000000000000000000000

    /**
     * 得到状态，CAPACITY的非操作得到的二进制位11100000000000000000000000000000，然后执行与操作，相当于直接取前3位的的值
     *
     * @param c
     * @return
     */
    private static int runStateOf(int c) {
        return c & ~CAPACITY;
    }

    /**
     * 得到线程数，也就是后29位的数字。 直接跟CAPACITY做一个与操作即可，CAPACITY就是的值就 1 << 29 - 1 = 00011111111111111111111111111111。 与操作的话前面3位肯定为0，相当于直接取后29位的值
     *
     * @param c
     * @return
     */
    private static int workerCountOf(int c) {
        return c & CAPACITY;
    }

    /**
     * 或操作。相当于更新数量和状态两个操作
     *
     * @param rs 线程池状态
     * @param wc 线程数容量
     * @return
     */
    private static int ctlOf(int rs, int wc) {
        return rs | wc;
    }

    private static boolean runStateLessThan(int c, int s) {
        return c < s;
    }

    private static boolean runStateAtLeast(int c, int s) {
        return c >= s;
    }

    /**
     * 只需要校验是否小于 0，不再需要读取 runStateOf
     *
     * @param c
     * @return
     */
    private static boolean isRunning(int c) {
        return c < SHUTDOWN;
    }

    /**
     * 增加工作线程数
     */
    private boolean compareAndIncrementWorkerCount(int expect) {
        return ctl.compareAndSet(expect, expect + 1);
    }

    /**
     * 减少工作线程数
     */
    private boolean compareAndDecrementWorkerCount(int expect) {
        return ctl.compareAndSet(expect, expect - 1);
    }

    /**
     * 减少ctl的workerCount字段
     * 这只在线程突然终止时被调用，参见processWorkerExit
     * 其他减少操作在 getTask 中执行
     */
    private void decrementWorkerCount() {
        do {
        } while (!compareAndDecrementWorkerCount(ctl.get()));
    }

    private final BlockingQueue<Runnable> workQueue;

    private final ReentrantLock mainLock = new ReentrantLock();

    /**
     * 包含所有工作线程的集合
     * 只有在拿到 mainLock 锁时访问
     */
    private final HashSet<Worker> workers = new HashSet<Worker>();

    private final Condition termination = mainLock.newCondition();

    /**
     * 当前线程池最大线程数
     * 只有在拿到 mainLock 锁时访问
     */
    private int largestPoolSize;

    /**
     * 已完成任务的计数器。仅在终止工作线程时更新
     * 只有在拿到 mainLock 锁时访问
     */
    private long completedTaskCount;

    private volatile ThreadFactory threadFactory;

    private volatile RejectedExecutionHandler handler;

    private volatile long keepAliveTime;

    // 是否允许删除空闲线程
    private volatile boolean allowCoreThreadTimeOut;

    /**
     * 核心线程池个数
     */
    private volatile int corePoolSize;

    /**
     * 最大线程池个数
     */
    private volatile int maximumPoolSize;

    /**
     * 拒绝策略
     */
    private static final RejectedExecutionHandler defaultHandler = new AbortPolicy();

    private static final RuntimePermission shutdownPerm = new RuntimePermission("modifyThread");

    private final AccessControlContext acc;

    /**
     * 线程封装
     * 根据 Worker 的锁来判断是否是闲置线程，是否可以被强制中断。
     * 可以获取锁：Worker 处于闲置状态。
     */
    private final class Worker extends AbstractQueuedSynchronizer implements Runnable {
        private static final long serialVersionUID = 6138294804551838833L;

        /**
         * 绑定的线程
         */
        final Thread thread;

        /**
         * 首次执行的任务
         */
        Runnable firstTask;

        /**
         * 完成任务数
         */
        volatile long completedTasks;

        Worker(Runnable firstTask) {
            // 把状态位设置成-1，这样任何线程都不能得到Worker的锁，除非调用了unlock方法。
            // 这个unlock方法会在runWorker方法中一开始就调用，这是为了确保Worker构造出来之后，没有任何线程能够得到它的锁，
            // 除非调用了runWorker之后，其他线程才能获得Worker的锁
            setState(-1);
            this.firstTask = firstTask;
            this.thread = getThreadFactory().newThread(this);
        }

        /**
         * 交由外部的 ThreadFactory::runWorker 执行
         */
        @Override
        public void run() {
            runWorker(this);
        }

        // 0 表示没有占有锁，1 表示占有锁
        @Override
        protected boolean isHeldExclusively() {
            return getState() != 0;
        }

        /**
         * 尝试获取锁
         */
        @Override
        protected boolean tryAcquire(int unused) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /**
         * 尝试释放锁
         */
        @Override
        protected boolean tryRelease(int unused) {
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        /**
         * 加锁
         */
        public void lock() {
            acquire(1);
        }

        /**
         * 尝试加锁
         */
        public boolean tryLock() {
            return tryAcquire(1);
        }

        /**
         * 释放锁
         */
        public void unlock() {
            release(1);
        }

        /**
         * 是否占有锁
         */
        public boolean isLocked() {
            return isHeldExclusively();
        }

        /**
         * 中断所有线程
         */
        void interruptIfStarted() {
            Thread t;
            // Worker无论是否被持有锁，只要还没被中断，那就中断Worker
            // state 只有 -1、0、1三种状态
            if (getState() >= 0 && (t = thread) != null && !t.isInterrupted()) {
                try {
                    // 强行中断Worker的执行
                    t.interrupt();
                } catch (SecurityException ignore) {
                }
            }
        }
    } // class Worker


    /**
     * 将 runState 转换到给定的目标，如果至少已经是给定的目标，则不进行转换。
     *
     * @param targetState 目标状态，可以是 SHUTDOWN 或 STOP (但不是 TIDYING 或 TERMINATED ——为此使用 tryTerminate)
     */
    private void advanceRunState(int targetState) {
        for (; ; ) {
            int c = ctl.get();
            if (runStateAtLeast(c, targetState) || ctl.compareAndSet(c, ctlOf(targetState, workerCountOf(c)))) {
                break;
            }
        }
    }

    /**
     * 尝试终止线程池
     */
    final void tryTerminate() {
        for (; ; ) {
            int c = ctl.get();
            // 满足3个条件中的任意一个，不终止线程池
            // 1. 线程池还在运行，不能终止
            // 2. 线程池处于TIDYING或TERMINATED状态，说明已经在关闭了，不允许继续处理
            // 3. 线程池处于SHUTDOWN状态并且阻塞队列不为空，这时候还需要处理阻塞队列的任务，不能终止线程池
            if (isRunning(c) || runStateAtLeast(c, TIDYING) || (runStateOf(c) == SHUTDOWN && !workQueue.isEmpty())) {
                return;
            }
            // 走到这一步说明线程池已经不在运行，阻塞队列已经没有任务，但是还要回收正在工作的Worker
            if (workerCountOf(c) != 0) {
                // 由于线程池不运行了，调用了线程池的关闭方法
                // 中断闲置Worker，直到回收全部的Worker。
                // 这里没有那么暴力，只中断一个，中断之后退出方法，中断了Worker之后，Worker会回收，然后还是会调用 tryTerminate 方法，如果还有闲置线程，那么继续中断
                interruptIdleWorkers(ONLY_ONE);
                return;
            }

            // 走到这里说明worker已经全部回收了，并且线程池已经不在运行，阻塞队列已经没有任务。可以准备结束线程池了
            final ReentrantLock mainLock = this.mainLock;
            // 加锁，防止并发
            mainLock.lock();
            try {
                // cas操作，将线程池状态改成TIDYING
                if (ctl.compareAndSet(c, ctlOf(TIDYING, 0))) {
                    try {
                        // 调用terminated方法
                        terminated();
                    } finally {
                        // terminated方法调用完毕之后，状态变为TERMINATED
                        ctl.set(ctlOf(TERMINATED, 0));
                        termination.signalAll();
                    }
                    return;
                }
            } finally {
                // 解锁
                mainLock.unlock();
            }
            // else retry on failed CAS
        }
    }

    private void checkShutdownAccess() {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkPermission(shutdownPerm);
            final ReentrantLock mainLock = this.mainLock;
            mainLock.lock();
            try {
                for (Worker w : workers) {
                    security.checkAccess(w.thread);
                }
            } finally {
                mainLock.unlock();
            }
        }
    }

    /**
     * 中断执行中的 Worker
     */
    private void interruptWorkers() {
        // 加锁，防止并发
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            for (Worker w : workers) {
                // 中断Worker的执行
                w.interruptIfStarted();
            }
        } finally {
            // 解锁
            mainLock.unlock();
        }
    }

    private void interruptIdleWorkers(boolean onlyOne) {
        // 中断闲置Worker需要加锁，防止并发
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            for (Worker w : workers) {
                Thread t = w.thread;
                // Worker中的线程没有被打断并且Worker可以获取锁
                // 这里Worker能获取锁说明Worker是个闲置Worker
                // 在阻塞队列里拿数据一直被阻塞，没有数据进来
                // 如果没有获取到Worker锁，说明Worker还在执行任务，不进行中断(shutdown方法不会中断正在执行的任务)
                if (!t.isInterrupted() && w.tryLock()) {
                    try {
                        // 中断Worker线程
                        t.interrupt();
                    } catch (SecurityException ignore) {
                    } finally {
                        // 释放Worker锁
                        w.unlock();
                    }
                }
                // 如果只打断1个Worker的话，直接break退出，否则，遍历所有的Worker
                if (onlyOne) {
                    break;
                }
            }
        } finally {
            // 解锁
            mainLock.unlock();
        }
    }

    /**
     * 调用他的一个重载方法，传入了参数false，表示要中断所有的正在运行的闲置Worker，如果为true表示只打断一个闲置Worker
     */
    private void interruptIdleWorkers() {
        interruptIdleWorkers(false);
    }

    private static final boolean ONLY_ONE = true;

    final void reject(Runnable command) {
        handler.rejectedExecution(command, this);
    }

    void onShutdown() {
    }

    final boolean isRunningOrShutdown(boolean shutdownOK) {
        int rs = runStateOf(ctl.get());
        return rs == RUNNING || (rs == SHUTDOWN && shutdownOK);
    }


    private List<Runnable> drainQueue() {
        BlockingQueue<Runnable> q = workQueue;
        ArrayList<Runnable> taskList = new ArrayList<Runnable>();
        q.drainTo(taskList);
        if (!q.isEmpty()) {
            for (Runnable r : q.toArray(new Runnable[0])) {
                if (q.remove(r)) {
                    taskList.add(r);
                }
            }
        }
        return taskList;
    }

    /**
     * firstTask 表示需要跑的任务
     * boolean类型的core参数为true的话表示使用线程池的基本大小，为false使用线程池最大大小
     * 返回值是boolean类型，true表示新任务被接收了，并且执行了。否则是false
     *
     * @param firstTask
     * @param core
     * @return
     */
    private boolean addWorker(Runnable firstTask, boolean core) {
        retry:
        for (; ; ) {
            int c = ctl.get();
            // 线程池状态
            int rs = runStateOf(c);

            // 这个判断转换成 rs >= SHUTDOWN && (rs != SHUTDOWN || firstTask != null || workQueue.isEmpty)。
            // 1. 线程池在 STOP、TIDYING或TERMINATED中的任意一种状态
            // 2. 线程池在 SHUTDOWN 状态，线程池接受了新的任务
            // 3. 线程池在 SHUTDOWN 状态，阻塞队列为空
            // 满足以上任意一个，则不能添加线程
            if (rs >= SHUTDOWN && !(rs == SHUTDOWN && firstTask == null && !workQueue.isEmpty())) {
                return false;
            }

            for (; ; ) {
                // 线程池线程个数
                int wc = workerCountOf(c);
                // 如果线程池线程数量超过线程池最大容量或者线程数量超过了基本大小(core参数为true，core参数为false的话判断超过最大大小)
                if (wc >= CAPACITY || wc >= (core ? corePoolSize : maximumPoolSize)) {
                    return false;
                }
                // 没有超过各种大小的话，cas操作线程池线程数量+1，cas成功的话跳出循环
                if (compareAndIncrementWorkerCount(c)) {
                    break retry;
                }
                // 重新检查状态
                c = ctl.get();  // Re-read ctl
                // 如果状态改变了，重新循环操作
                if (runStateOf(c) != rs) {
                    continue retry;
                }
                // else CAS failed due to workerCount change; retry inner loop
            }
        }

        // 走到这一步说明cas操作成功了，线程池线程数量+1

        // 任务是否成功启动标识
        boolean workerStarted = false;
        // 任务是否添加成功标识
        boolean workerAdded = false;
        Worker w = null;
        try {
            w = new Worker(firstTask);
            final Thread t = w.thread;
            if (t != null) {
                // 得到线程池的可重入锁
                final ReentrantLock mainLock = this.mainLock;
                mainLock.lock();
                try {
                    int rs = runStateOf(ctl.get());
                    // 如果线程池在RUNNING状态或者线程池在SHUTDOWN状态并且任务是个null
                    if (rs < SHUTDOWN || (rs == SHUTDOWN && firstTask == null)) {
                        // 判断线程是否还活着，也就是说线程已经启动并且还没死掉
                        if (t.isAlive()) {
                            // 如果存在已经启动并且还没死的线程，抛出异常
                            throw new IllegalThreadStateException();
                        }
                        // worker添加到线程池的workers属性中，是个HashSet
                        workers.add(w);
                        // 得到目前线程池中的线程个数
                        int s = workers.size();
                        // 如果线程池中的线程个数超过了线程池中的最大线程数时，更新一下这个最大线程数
                        if (s > largestPoolSize) {
                            largestPoolSize = s;
                        }
                        // 标识一下任务已经添加成功
                        workerAdded = true;
                    }
                } finally {
                    mainLock.unlock();
                }
                // 如果任务添加成功，运行任务，改变一下任务成功启动标识
                if (workerAdded) {
                    // 启动线程，这里的t是Worker中的thread属性，所以相当于就是调用了Worker的run方法
                    t.start();
                    workerStarted = true;
                }
            }
        } finally {
            // 如果任务启动失败，调用addWorkerFailed方法
            if (!workerStarted) {
                addWorkerFailed(w);
            }
        }
        return workerStarted;
    }

    private void addWorkerFailed(Worker w) {
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            if (w != null) {
                workers.remove(w);
            }
            decrementWorkerCount();
            tryTerminate();
        } finally {
            mainLock.unlock();
        }
    }

    /**
     * 回收工作线程
     *
     * @param w
     * @param completedAbruptly
     */
    private void processWorkerExit(Worker w, boolean completedAbruptly) {
        // 如果Worker没有正常结束流程调用processWorkerExit方法，worker数量减一。
        // 如果是正常结束的话，在getTask方法里worker数量已经减一了
        if (completedAbruptly) {
            decrementWorkerCount();
        }

        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            // 记录总的完成任务数
            completedTaskCount += w.completedTasks;
            // 线程池的worker集合删除掉需要回收的Worker
            workers.remove(w);
        } finally {
            // 解锁
            mainLock.unlock();
        }

        // 尝试结束线程池
        tryTerminate();

        int c = ctl.get();
        // 如果线程池还处于RUNNING或者SHUTDOWN状态
        if (runStateLessThan(c, STOP)) {
            // Worker是正常结束流程的话
            if (!completedAbruptly) {
                int min = allowCoreThreadTimeOut ? 0 : corePoolSize;
                if (min == 0 && !workQueue.isEmpty()) {
                    min = 1;
                }
                if (workerCountOf(c) >= min) {
                    // 不需要新开一个 Worker
                    return;
                }
            }
            // 新开一个 Worker 代替原先的Worker
            // 新开一个 Worker 需要满足以下3个条件中的任意一个：
            // 1. 用户执行的任务发生了异常 completedAbruptly 为 true
            // 2. Worker数量比线程池基本大小要小
            // 3. 阻塞队列不空但是没有任何Worker在工作
            addWorker(null, false);
        }
    }

    /**
     * 如果发生了以下四件事中的任意一件，那么Worker需要被回收：
     * 1. Worker 个数比线程池最大大小要大
     * 2. 线程池处于 STOP 状态
     * 3. 线程池处于 SHUTDOWN 状态并且阻塞队列为空
     * 4. 使用超时时间从阻塞队列里拿数据，并且超时之后没有拿到数据(allowCoreThreadTimeOut || workerCount > corePoolSize)
     *
     * @return
     */
    private Runnable getTask() {
        // 如果使用超时时间并且也没有拿到任务的标识
        boolean timedOut = false;

        for (; ; ) {
            int c = ctl.get();
            int rs = runStateOf(c);

            // 如果线程池是SHUTDOWN状态并且阻塞队列为空的话，worker数量减一，直接返回null(SHUTDOWN状态还会处理阻塞队列任务，但是阻塞队列为空的话就结束了)
            // 如果线程池是STOP/TIDYING/TERMINATED状态的话，worker数量建议，直接返回null(STOP状态不处理阻塞队列任务)
            if (rs >= SHUTDOWN && (rs >= STOP || workQueue.isEmpty())) {
                decrementWorkerCount();
                return null;
            }
            // 当前线程数
            int wc = workerCountOf(c);

            // 标记从队列中取任务时是否设置超时时间，如果为true说明这个worker可能需要回收，为false的话这个worker会一直存在，并且阻塞当前线程等待阻塞队列中有数据
            // allowCoreThreadTimeOut属性默认为false，表示线程池中的核心线程在闲置状态下还保留在池中；如果是true表示核心线程使用keepAliveTime这个参数来作为超时时间
            // 如果worker数量比基本大小要大的话，timed就为true，需要进行回收worker
            boolean timed = allowCoreThreadTimeOut || wc > corePoolSize;

            if ((wc > maximumPoolSize || (timed && timedOut)) && (wc > 1 || workQueue.isEmpty())) {
                // worker数量减一，返回null，之后会进行Worker回收工作
                if (compareAndDecrementWorkerCount(c)) {
                    // 外层方法进行 Worker 线程的回收
                    return null;
                }
                continue;
            }

            try {
                // 如果需要设置超时时间，使用poll方法，否则使用take方法一直阻塞等待阻塞队列新进数据
                Runnable r = timed ? workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS) : workQueue.take();
                if (r != null) {
                    return r;
                }
                timedOut = true;
            } catch (InterruptedException retry) {
                // // 闲置Worker被中断
                timedOut = false;
            }
        }
    }

    /**
     * 执行当前任务
     *
     * @param w
     */
    final void runWorker(Worker w) {
        // 得到当前线程
        Thread wt = Thread.currentThread();
        // 得到Worker中的任务task，也就是用户传入的task
        Runnable task = w.firstTask;
        // 将Worker中的任务置空
        w.firstTask = null;
        // state 由 -1 置为 0，才可以竞争锁，进而执行任务
        w.unlock(); // allow interrupts
        // 任务执行是否出现异常
        boolean completedAbruptly = true;
        try {
            // task != null 表明是该 Worker 的首个执行任务
            while (task != null || (task = getTask()) != null) {
                w.lock();
                // 如果拿到了任务，给自己上锁，表示当前 Worker 已经要开始执行任务了，已经不是闲置 Worker
                // 1. 如果线程池已经处于STOP状态并且当前线程没有被中断，中断线程
                // 2. 如果线程池还处于RUNNING或SHUTDOWN状态，并且当前线程已经被中断了，重新检查一下线程池状态，如果处于STOP状态并且没有被中断，那么中断线程
                if ((runStateAtLeast(ctl.get(), STOP) || (Thread.interrupted() && runStateAtLeast(ctl.get(), STOP))) && !wt.isInterrupted()) {
                    wt.interrupt();
                }
                try {
                    // 钩子函数，空实现
                    beforeExecute(wt, task);
                    Throwable thrown = null;
                    try {
                        // 真正的开始执行任务，调用的是run方法，而不是start方法。这里run的时候可能会被中断，比如线程池调用了shutdownNow方法
                        task.run();
                    } catch (RuntimeException x) {
                        thrown = x;
                        throw x;
                    } catch (Error x) {
                        thrown = x;
                        throw x;
                    } catch (Throwable x) {
                        // 任务执行发生的异常全部抛出，不在runWorker中处理
                        thrown = x;
                        throw new Error(x);
                    } finally {
                        // 钩子函数，空实现
                        afterExecute(task, thrown);
                    }
                } finally {
                    task = null;
                    // 记录执行任务的个数
                    w.completedTasks++;
                    // 执行完任务之后，解锁，Worker 变成闲置 Worker
                    w.unlock();
                }
            }
            completedAbruptly = false;
        } finally {
            // 回收Worker方法
            processWorkerExit(w, completedAbruptly);
        }
    }

    public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, Executors.defaultThreadFactory(), defaultHandler);
    }

    public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, defaultHandler);
    }

    public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, Executors.defaultThreadFactory(), handler);
    }

    public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        if (corePoolSize < 0 || maximumPoolSize <= 0 || maximumPoolSize < corePoolSize || keepAliveTime < 0) {
            throw new IllegalArgumentException();
        }
        if (workQueue == null || threadFactory == null || handler == null) {
            throw new NullPointerException();
        }
        this.acc = System.getSecurityManager() == null ? null : AccessController.getContext();
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.handler = handler;
    }

    /**
     * 1. 如果当前正在执行的Worker数量比corePoolSize(基本大小)要小。直接创建一个新的Worker执行任务，会调用addWorker方法
     * 2. 如果当前正在执行的Worker数量大于等于corePoolSize(基本大小)。将任务放到阻塞队列里，如果阻塞队列没满并且状态是RUNNING的话，直接丢到阻塞队列，否则执行第3步。
     * 丢到阻塞队列之后，还需要再做一次验证(丢到阻塞队列之后可能另外一个线程关闭了线程池或者刚刚加入到队列的线程死了)。
     * 如果这个时候线程池不在RUNNING状态，把刚刚丢入队列的任务remove掉，调用reject方法，否则查看Worker数量，如果Worker数量为0，起一个新的Worker去阻塞队列里拿任务执行
     * 3. 丢到阻塞失败的话，会调用addWorker方法尝试起一个新的Worker去阻塞队列拿任务并执行任务，如果这个新的Worker创建失败，调用reject方法
     *
     * @param command
     */
    @Override
    public void execute(Runnable command) {
        if (command == null) {
            throw new NullPointerException();
        }

        int c = ctl.get();
        // 第一个步骤，满足线程池中的线程大小比基本大小要小
        if (workerCountOf(c) < corePoolSize) {
            // 新建线程
            if (addWorker(command, true)) {
                return;
            }
            c = ctl.get();
        }
        // 第二个步骤，线程池的线程大小比基本大小要大，并且线程池还在RUNNING状态，阻塞队列也没满的情况，加到阻塞队列里
        if (isRunning(c) && workQueue.offer(command)) {
            int recheck = ctl.get();
            // 虽然满足了第二个步骤，但是这个时候可能突然线程池关闭了，所以再做一层判断
            if (!isRunning(recheck) && remove(command)) {
                reject(command);
            } else if (workerCountOf(recheck) == 0) {
                addWorker(null, false);
            }
        } else if (!addWorker(command, false)) { // 第三个步骤，直接使用线程池最大大小。addWorker方法第二个参数false表示使用最大大小
            reject(command);
        }
    }


    /**
     * shutdown 与 shutdownNow 区别
     * shutdown 将线程池状态更新为 SHUTDOWN 状态；shutdownNow 将线程池状态更新为 STOP 状态。
     * shutdown 中断所有的空闲线程（可以获取 Worker 锁）; shutdownNow 中断所有线程，包括运行中的线程。
     * shutdownNow 返回空闲任务列表，shutdown 无返回值。
     */

    @Override
    public void shutdown() {
        // 关闭的时候需要加锁，防止并发
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            // 检查关闭线程池的权限
            checkShutdownAccess();
            // 把线程池状态更新到SHUTDOWN
            advanceRunState(SHUTDOWN);
            // 中断闲置的Worker
            interruptIdleWorkers();
            // 钩子方法，默认不处理
            onShutdown();
        } finally {
            // 解锁
            mainLock.unlock();
        }
        // 尝试结束线程池
        tryTerminate();
    }

    @Override
    public List<Runnable> shutdownNow() {
        List<Runnable> tasks;
        // 加锁，防止并发
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            // 检查关闭线程池的权限
            checkShutdownAccess();
            // 把线程池状态更新到STOP
            advanceRunState(STOP);
            // 中断Worker的运行
            interruptWorkers();
            // 空闲任务列表
            tasks = drainQueue();
        } finally {
            // 解锁
            mainLock.unlock();
        }
        // 尝试结束线程池
        tryTerminate();
        return tasks;
    }

    @Override
    public boolean isShutdown() {
        return !isRunning(ctl.get());
    }

    public boolean isTerminating() {
        int c = ctl.get();
        return !isRunning(c) && runStateLessThan(c, TERMINATED);
    }

    @Override
    public boolean isTerminated() {
        return runStateAtLeast(ctl.get(), TERMINATED);
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        long nanos = unit.toNanos(timeout);
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            for (; ; ) {
                if (runStateAtLeast(ctl.get(), TERMINATED)) {
                    return true;
                }
                if (nanos <= 0) {
                    return false;
                }
                nanos = termination.awaitNanos(nanos);
            }
        } finally {
            mainLock.unlock();
        }
    }

    @Override
    protected void finalize() {
        SecurityManager sm = System.getSecurityManager();
        if (sm == null || acc == null) {
            shutdown();
        } else {
            PrivilegedAction<Void> pa = () -> {
                shutdown();
                return null;
            };
            AccessController.doPrivileged(pa, acc);
        }
    }

    public void setThreadFactory(ThreadFactory threadFactory) {
        if (threadFactory == null) {
            throw new NullPointerException();
        }
        this.threadFactory = threadFactory;
    }


    public ThreadFactory getThreadFactory() {
        return threadFactory;
    }

    public void setRejectedExecutionHandler(RejectedExecutionHandler handler) {
        if (handler == null) {
            throw new NullPointerException();
        }
        this.handler = handler;
    }

    public RejectedExecutionHandler getRejectedExecutionHandler() {
        return handler;
    }

    public void setCorePoolSize(int corePoolSize) {
        if (corePoolSize < 0) {
            throw new IllegalArgumentException();
        }
        int delta = corePoolSize - this.corePoolSize;
        this.corePoolSize = corePoolSize;
        if (workerCountOf(ctl.get()) > corePoolSize) {
            interruptIdleWorkers();
        } else if (delta > 0) {
            // We don't really know how many new threads are "needed".
            // As a heuristic, prestart enough new workers (up to new
            // core size) to handle the current number of tasks in
            // queue, but stop if queue becomes empty while doing so.
            int k = Math.min(delta, workQueue.size());
            while (k-- > 0 && addWorker(null, true)) {
                if (workQueue.isEmpty()) {
                    break;
                }
            }
        }
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public boolean prestartCoreThread() {
        return workerCountOf(ctl.get()) < corePoolSize && addWorker(null, true);
    }

    void ensurePrestart() {
        int wc = workerCountOf(ctl.get());
        if (wc < corePoolSize) {
            addWorker(null, true);
        } else if (wc == 0) {
            addWorker(null, false);
        }
    }

    public int prestartAllCoreThreads() {
        int n = 0;
        while (addWorker(null, true)) {
            ++n;
        }
        return n;
    }

    public boolean allowsCoreThreadTimeOut() {
        return allowCoreThreadTimeOut;
    }

    public void allowCoreThreadTimeOut(boolean value) {
        if (value && keepAliveTime <= 0) {
            throw new IllegalArgumentException("Core threads must have nonzero keep alive times");
        }
        if (value != allowCoreThreadTimeOut) {
            allowCoreThreadTimeOut = value;
            if (value) {
                interruptIdleWorkers();
            }
        }
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        if (maximumPoolSize <= 0 || maximumPoolSize < corePoolSize) {
            throw new IllegalArgumentException();
        }
        this.maximumPoolSize = maximumPoolSize;
        if (workerCountOf(ctl.get()) > maximumPoolSize) {
            interruptIdleWorkers();
        }
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setKeepAliveTime(long time, TimeUnit unit) {
        if (time < 0) {
            throw new IllegalArgumentException();
        }
        if (time == 0 && allowsCoreThreadTimeOut()) {
            throw new IllegalArgumentException("Core threads must have nonzero keep alive times");
        }
        long keepAliveTime = unit.toNanos(time);
        long delta = keepAliveTime - this.keepAliveTime;
        this.keepAliveTime = keepAliveTime;
        if (delta < 0) {
            interruptIdleWorkers();
        }
    }

    public long getKeepAliveTime(TimeUnit unit) {
        return unit.convert(keepAliveTime, TimeUnit.NANOSECONDS);
    }


    public BlockingQueue<Runnable> getQueue() {
        return workQueue;
    }

    public boolean remove(Runnable task) {
        boolean removed = workQueue.remove(task);
        tryTerminate(); // In case SHUTDOWN and now empty
        return removed;
    }

    public void purge() {
        final BlockingQueue<Runnable> q = workQueue;
        try {
            Iterator<Runnable> it = q.iterator();
            while (it.hasNext()) {
                Runnable r = it.next();
                if (r instanceof Future<?> && ((Future<?>) r).isCancelled()) {
                    it.remove();
                }
            }
        } catch (ConcurrentModificationException fallThrough) {
            // Take slow path if we encounter interference during traversal.
            // Make copy for traversal and call remove for cancelled entries.
            // The slow path is more likely to be O(N*N).
            for (Object r : q.toArray()) {
                if (r instanceof Future<?> && ((Future<?>) r).isCancelled()) {
                    q.remove(r);
                }
            }
        }

        tryTerminate(); // In case SHUTDOWN and now empty
    }

    /**
     * 返回当前线程池线程数
     */
    public int getPoolSize() {
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            // Remove rare and surprising possibility of isTerminated() && getPoolSize() > 0
            return runStateAtLeast(ctl.get(), TIDYING) ? 0 : workers.size();
        } finally {
            mainLock.unlock();
        }
    }

    /**
     * 返回正在执行任务的线程数
     */
    public int getActiveCount() {
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            int n = 0;
            for (Worker w : workers) {
                if (w.isLocked()) {
                    ++n;
                }
            }
            return n;
        } finally {
            mainLock.unlock();
        }
    }


    public int getLargestPoolSize() {
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            return largestPoolSize;
        } finally {
            mainLock.unlock();
        }
    }

    public long getTaskCount() {
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            long n = completedTaskCount;
            for (Worker w : workers) {
                n += w.completedTasks;
                if (w.isLocked()) {
                    ++n;
                }
            }
            return n + workQueue.size();
        } finally {
            mainLock.unlock();
        }
    }

    public long getCompletedTaskCount() {
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            long n = completedTaskCount;
            for (Worker w : workers) {
                n += w.completedTasks;
            }
            return n;
        } finally {
            mainLock.unlock();
        }
    }

    @Override
    public String toString() {
        long ncompleted;
        int nworkers, nactive;
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            ncompleted = completedTaskCount;
            nactive = 0;
            nworkers = workers.size();
            for (Worker w : workers) {
                ncompleted += w.completedTasks;
                if (w.isLocked()) {
                    ++nactive;
                }
            }
        } finally {
            mainLock.unlock();
        }
        int c = ctl.get();
        String rs = (runStateLessThan(c, SHUTDOWN) ? "Running" : (runStateAtLeast(c, TERMINATED) ? "Terminated" : "Shutting down"));
        return super.toString() + "[" + rs + ", pool size = " + nworkers + ", active threads = " + nactive + ", queued tasks = " + workQueue.size() + ", completed tasks = " + ncompleted + "]";
    }

    protected void beforeExecute(Thread t, Runnable r) {
    }

    protected void afterExecute(Runnable r, Throwable t) {
    }

    protected void terminated() {
    }

    /**
     * 由调用线程处理该任务
     */
    public static class CallerRunsPolicy implements RejectedExecutionHandler {
        public CallerRunsPolicy() {
        }

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            if (!e.isShutdown()) {
                r.run();
            }
        }
    }

    /**
     * 丢弃任务并抛出 RejectedExecutionException 异常，线程池默认采用改种拒绝策略
     */
    public static class AbortPolicy implements RejectedExecutionHandler {
        public AbortPolicy() {
        }

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            throw new RejectedExecutionException("Task " + r.toString() + " rejected from " + e.toString());
        }
    }

    /**
     * 丢弃任务，但是不抛出异常
     */
    public static class DiscardPolicy implements RejectedExecutionHandler {
        public DiscardPolicy() {
        }

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        }
    }

    /**
     * 丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
     */
    public static class DiscardOldestPolicy implements RejectedExecutionHandler {
        public DiscardOldestPolicy() {
        }

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            if (!e.isShutdown()) {
                e.getQueue().poll();
                e.execute(r);
            }
        }
    }
}
