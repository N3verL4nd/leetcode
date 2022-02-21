package study.thread;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 乐观锁实现
 *
 * @author N3verL4nd
 * @date 2022/2/18
 */
public class LockMe extends AbstractQueuedSynchronizer {

    @Override
    protected boolean tryAcquire(int arg) {
        if (compareAndSetState(0, 1)) {
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    @Override
    protected boolean isHeldExclusively() {
        return getState() != 0;
    }

    /**
     * 加锁
     */
    public void lock() {
        acquire(1);
    }

    /**
     * 释放锁
     */
    public void unlock() {
        release(1);
    }

    /**
     * 尝试加锁
     */
    public boolean tryLock() {
        return tryAcquire(1);
    }

    /**
     * 是否占有锁
     */
    public boolean isLocked() {
        return isHeldExclusively();
    }
}
