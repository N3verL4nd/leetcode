package solutions._8;

/**
 * 8. String to Integer (atoi)
 */
class Solution {
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        long result = 0;
        boolean flag = false;
        int i = 0;

        // 去除前缀后缀空格
        str = str.trim();
        char[] arr = str.toCharArray();

        // 判断正负
        if (i < arr.length) {
            if (arr[i] == '-') {
                flag = true;
                i++;
            } else if (arr[i] == '+') {
                i++;
            }
        }

        if (i == arr.length) {
            return 0;
        }

        // 正负号后必须为数字
        if (i + 1 < arr.length && !(arr[i] >= '0' && arr[i] <= '9')) {
            return 0;
        }

        // 执行转换
        while (i < arr.length) {
            if (arr[i] >= '0' && arr[i] <= '9') {
                if (flag) {
                    result = result * 10 - (arr[i] - '0');
                } else {
                    result = result * 10 + (arr[i] - '0');
                }

                if (result > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
                if (result < Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
            } else {
                break;
            }
            i++;
        }
        return (int) result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().myAtoi("123"));
        System.out.println(new Solution().myAtoi("-123"));
        System.out.println(new Solution().myAtoi("+-2"));
        System.out.println(new Solution().myAtoi("1"));
        System.out.println(new Solution().myAtoi("  -0012a42"));
    }
}