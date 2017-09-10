package solutions._67;

/**
 * 67. Add Binary
 */
public class Solution {
    public String addBinary(String a, String b) {
        StringBuilder buffer = new StringBuilder();
        int plus = 0;
        int i, j;
        for (i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = 0;
            if (i >= 0) {
                sum = (a.charAt(i) - '0');
            }
            if (j >= 0) {
                sum += (b.charAt(j) - '0');
            }

            sum += plus;

            plus = sum / 2;
            buffer.append(sum % 2);
        }
        if (plus == 1) {
            buffer.append(1);
        }
        return buffer.reverse().toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.addBinary("111", "1"));
    }
}