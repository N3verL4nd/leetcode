package solutions._415;

class Solution1 {
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;

        StringBuilder sb = new StringBuilder();

        int append = 0;
        while (i >= 0 && j >= 0) {
            int sum = (num1.charAt(i) - '0') + (num2.charAt(j) - '0') + append;
            sb.append(sum % 10);
            append = sum / 10;
            i--;
            j--;
        }

        while (i >= 0) {
            int sum = num1.charAt(i) - '0' + append;
            sb.append(sum % 10);
            append = sum / 10;
            i--;
        }

        while (j >= 0) {
            int sum = num2.charAt(j) - '0' + append;
            sb.append(sum % 10);
            append = sum / 10;
            j--;
        }

        if (append == 1) {
            sb.append(1);
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        System.out.println(solution1.addStrings("123", "456"));
        System.out.println(solution1.addStrings("408", "5"));
        System.out.println(solution1.addStrings("1", "9"));
    }
}


/*

给定两个字符串形式的非负整数num1 和num2，计算它们的和。



提示：

num1 和num2的长度都小于 5100
num1 和num2 都只包含数字0-9
num1 和num2 都不包含任何前导零
你不能使用任何內建 BigInteger 库，也不能直接将输入的字符串转换为整数形式


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/add-strings
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */