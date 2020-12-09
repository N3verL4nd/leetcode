package solutions._1143;

import java.util.Arrays;

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()][text2.length()];

        for (int i = 0; i < text1.length(); i++) {
            for (int j = 0; j < text2.length(); j++) {

                if (i == 0 && j == 0) {
                    if (text1.charAt(i) == text2.charAt(j)) {
                        dp[i][j] = 1;
                    }
                } else if (i == 0) {
                    if (text1.charAt(i) == text2.charAt(j)) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i][j - 1];
                    }
                } else if (j == 0) {
                    if (text1.charAt(i) == text2.charAt(j)) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                } else {
                    if (text1.charAt(i) == text2.charAt(j)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[text1.length() - 1][text2.length() - 1];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestCommonSubsequence("abcde", "ace"));
        System.out.println(new Solution().longestCommonSubsequence("bsbininm", "jmjkbkjkv"));
        System.out.println(new Solution().longestCommonSubsequence("aaa", "aa"));
        System.out.println(new Solution().longestCommonSubsequence("bl", "yby"));
    }
}