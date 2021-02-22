package solutions._151;

public class Solution {
    public String reverseWords(String s) {
        s = s.trim();
        String[] arrs = s.split("\\s+");
        if (arrs.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(s.length());
        for (int i = arrs.length - 1; i > 0; i--) {
            sb.append(arrs[i]);
            sb.append(" ");
        }
        sb.append(arrs[0]);
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.reverseWords("a  b     c  d e f      g"));
    }
}