package solutions._155;

import java.util.LinkedList;

/**
 * 155. Min Stack
 */
class MinStackOld {
    private LinkedList<Integer> list;
    private int min;

    /** initialize your data structure here. */
    public MinStackOld() {
        list = new LinkedList<>();
        min = Integer.MAX_VALUE;
    }
    
    public void push(int x) {
        list.push(x);
        if (x < min) {
            min = x;
        }
    }
    
    public void pop() {
        list.pop();
        min = Integer.MAX_VALUE;
        for (int x : list) {
            if (x < min) {
                min = x;
            }
        }
    }
    
    public int top() {
        return list.peek();
    }
    
    public int getMin() {
        return min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

public class MinStack {
    private LinkedList<Integer> list;
    private int min;

    /** initialize your data structure here. */
    public MinStack() {
        list = new LinkedList<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        if (x <= min) {
            list.push(min);
            min = x;
        }
        list.push(x);
    }

    public void pop() {
        if (min == list.pop()) {
            min = list.pop();
        }
    }

    public int top() {
        return list.peek();
    }

    public int getMin() {
        return min;
    }

    public LinkedList<Integer> getList() {
        return list;
    }
}