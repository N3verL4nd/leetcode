package solutions._58;

/**
 * 58. Length of Last Word
 */
class Solution {
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int count = 0;
        char[] arrs = s.toCharArray();
        int i = arrs.length - 1;
        while (i >= 0 && arrs[i] == ' ') {
            i--;
        }
        for (; i >= 0 ; i--) {
            if (arrs[i] != ' ') {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int length = solution.lengthOfLastWord(" ");
        System.out.println(length);
    }
}