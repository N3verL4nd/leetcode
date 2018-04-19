package solutions._225;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 225. Implement Stack using Queues
 */
class MyStack {
    private Queue<Integer> queue;

    /**
     * Initialize your data structure here.
     */
    public MyStack() {
        queue = new LinkedList<>();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        queue.add(x);
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        Queue<Integer> temp = new LinkedList<>();
        int length = queue.size();
        for (int i = 1; i < length; i++) {
            temp.add(queue.poll());
        }
        int x = queue.poll();
        queue = temp;
        return x;
    }

    /**
     * Get the top element.
     */
    public int top() {
        int x = pop();
        queue.add(x);
        return x;
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return queue.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */