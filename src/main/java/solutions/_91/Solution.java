package solutions._91;

class Solution {
    private int result;

    private void DFS(int pos, int[] arr) {
        if (pos == arr.length) {
            result++;
            return;
        }

        DFS(pos + 1, arr);
        if (pos + 1 < arr.length && arr[pos] * 10 + arr[pos + 1] <= 26) {
            DFS(pos + 2, arr);
        }
    }

    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int[] arr = new int[s.length()];
        for (int i = 0; i < chars.length; i++) {
            arr[i] = chars[i] - '0';
        }
        DFS(0, arr);
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numDecodings("10"));
    }
}