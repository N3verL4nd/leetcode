package solutions._415;

/**
 * 415. Add Strings
 */
class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder(5005);
        char[] x = num1.toCharArray();
        char[] y = num2.toCharArray();
        int i = x.length - 1;
        int j = y.length - 1;
        int plus = 0;
        while (i >= 0 && j >= 0) {
            int sum = (x[i] - '0') + (y[j] - '0') + plus;
            plus = sum / 10;
            sb.append(sum % 10);
            i--;
            j--;
        }
        while (i >= 0) {
            int sum = (x[i] - '0') + plus;
            plus = sum / 10;
            sb.append(sum % 10);
            i--;
        }
        while (j >= 0) {
            int sum = (y[j] - '0') + plus;
            plus = sum / 10;
            sb.append(sum % 10);
            j--;
        }
        if (plus == 1) {
            sb.append(1);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.addStrings("999", "1"));
        System.out.println(solution.addStrings("408", "5"));
    }
}