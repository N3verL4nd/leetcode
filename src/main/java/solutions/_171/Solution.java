package solutions._171;

/**
 * 171. Excel Sheet Column Number
 */
class Solution {
    public int titleToNumber(String s) {
        int num = 0;
        int k = s.length() - 1;
        for (char c : s.toCharArray()) {
            num += (c - 'A' + 1) * (int) Math.pow(26, k);
            k--;
        }
        return num;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.titleToNumber("BBA"));
        System.out.println(solution.titleToNumber("BA"));
        System.out.println(solution.titleToNumber("BCA"));
    }
}