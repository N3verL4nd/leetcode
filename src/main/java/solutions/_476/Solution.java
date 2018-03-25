package solutions._476;

/**
 * 476. Number Complement
 */
class Solution {
    public int findComplement(int num) {
        char[] str = Integer.toBinaryString(num).toCharArray();
        StringBuilder sb = new StringBuilder(50);
        for (char c : str) {
            if (c == '1') {
                sb.append('0');
            } else {
                sb.append('1');
            }
        }
        return Integer.valueOf(sb.toString(), 2);
    }

    public int findComplement1(int num) {
        boolean flag = false;
        for (int i = 31; i >= 0; i--) {
            if ((num & (1 << i)) != 0) {
                flag = true;
            }
            if (flag) {
                num ^= (1 << i);
            }
        }
        return num;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findComplement1(5));
    }
}