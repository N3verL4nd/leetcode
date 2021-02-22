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
        char[] arr = s.toCharArray();
        int i = arr.length - 1;
        while (i >= 0 && arr[i] == ' ') {
            i--;
        }
        while (i >= 0 && arr[i] != ' ') {
            i--;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int length = solution.lengthOfLastWord(" ");
        System.out.println(length);
    }
}