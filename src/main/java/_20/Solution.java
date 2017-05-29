package _20;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 20. Valid Parentheses
 */
public class Solution {
    public boolean isValid(String s) {
        Deque<Character> deque = new LinkedList<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                deque.push(ch);
            } else if (ch == ')') {
                if (deque.isEmpty() || deque.pop() != '(') {
                    return false;
                }
            } else if (ch == ']') {
                if (deque.isEmpty() || deque.pop() != '[') {
                    return false;
                }
            } else if (ch == '}'){
                if (deque.isEmpty() || deque.pop() != '{') {
                    return false;
                }
            }
        }
        return deque.isEmpty();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isValid("{}"));
    }
}