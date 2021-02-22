package solutions._557;

/**
 * 557. Reverse Words in a String III
 */
public class Solution {
    public String reverseWords(String s) {
        StringBuilder buffer = new StringBuilder();
        String[] arr = s.split(" ");
        for(int i = 0; i < arr.length; i++) {
            buffer.append(new StringBuilder(arr[i]).reverse());
            if (i != arr.length - 1) {
                buffer.append(' ');
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.reverseWords("Let's take LeetCode contest"));
    }
}