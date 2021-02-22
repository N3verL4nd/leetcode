package solutions._150;


import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int evalRPN(String[] tokens) {

        Deque<Integer> stack = new ArrayDeque<>();
        for (String token : tokens) {
            if ("+".equals(token)) {
                Integer x = stack.pop();
                Integer y = stack.pop();
                stack.push(y + x);
            } else if ("-".equals(token)) {
                Integer x = stack.pop();
                Integer y = stack.pop();
                stack.push(y - x);
            } else if ("*".equals(token)) {
                Integer x = stack.pop();
                Integer y = stack.pop();
                stack.push(y * x);
            } else if ("/".equals(token)) {
                Integer x = stack.pop();
                Integer y = stack.pop();
                stack.push(y / x);
            } else {
                stack.push(Integer.valueOf(token));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().evalRPN(new String[]{"2", "1", "+", "3", "*"}));
        System.out.println(new Solution().evalRPN(new String[]{"4", "13", "5", "/", "+"}));
        System.out.println(new Solution().evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
    }
}