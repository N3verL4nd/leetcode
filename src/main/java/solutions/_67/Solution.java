package solutions._67;

/**
 * 67. Add Binary
 */
public class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder(1000);
        char[] arrA = a.toCharArray();
        char[] arrB = b.toCharArray();
        int i = arrA.length - 1;
        int j = arrB.length - 1;
        // 进位
        int plus = 0;

        // 处理两个二进制字符串的相同部分
        while (i >= 0 && j >= 0) {
            int sum = (arrA[i] - '0') + (arrB[j] - '0') + plus;
            plus = sum / 2;
            sb.append(sum % 2);
            i--;
            j--;
        }

        // 处理剩余二进制位
        while (i >= 0) {
            int sum = (arrA[i] - '0') + plus;
            plus = sum / 2;
            sb.append(sum % 2);
            i--;
        }

        while (j >= 0) {
            int sum = (arrB[j] - '0') + plus;
            plus = sum / 2;
            sb.append(sum % 2);
            j--;
        }

        // 进位
        if (plus == 1) {
            sb.append(1);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.addBinary("100010", "11"));
    }
}

