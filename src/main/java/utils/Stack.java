package utils;

import java.util.LinkedList;

/**
 * 栈
 *
 * @param <E>
 */
public class Stack<E> {
    private LinkedList<E> stack = new LinkedList<>();

    public Stack() {
    }

    public void push(E e) {
        stack.addFirst(e);
    }

    public E pop() {
        return stack.removeFirst();
    }

    public E peek() {
        return stack.getFirst();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        return "Stack{" +
                "stack=" + stack +
                '}';
    }
}
