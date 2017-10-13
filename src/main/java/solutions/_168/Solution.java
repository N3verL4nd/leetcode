package solutions._168;

/**
 * 168. Excel Sheet Column Title
 */
class Solution {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            if (n % 26 == 0) {
                sb.append('Z');
                n = n / 26;
                n--;
            } else {
                sb.append((char) (n % 26 - 1 + 'A'));
                n = n / 26;
            }
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.convertToTitle(1405));
        System.out.println(solution.convertToTitle(53));
        System.out.println(solution.convertToTitle(1431));
        System.out.println(solution.convertToTitle(26));
        System.out.println(solution.convertToTitle(52));
        System.out.println(solution.convertToTitle(78));
    }
}