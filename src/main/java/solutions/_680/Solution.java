package solutions._680;

/**
 * 680. Valid Palindrome II
 */
class Solution {

    private boolean check(char[] str, int i, int j) {
        while (i < j) {
            if (str[i] != str[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public boolean validPalindrome(String s) {
        char[] str = s.toCharArray();
        int i = 0;
        int j = str.length - 1;
        while (i < j) {
            if (str[i] != str[j]) {
                boolean flag = check(str, i + 1, j);
                if (!flag) {
                    return check(str, i, j - 1);
                } else {
                    return true;
                }
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.validPalindrome("deeee"));
    }
}