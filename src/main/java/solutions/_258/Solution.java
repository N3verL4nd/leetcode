package solutions._258;

class Solution {
    public int addDigits(int num) {
        int sum = num;
        while (sum / 10 != 0) {
            sum = 0;
            while (num != 0) {
                sum += num % 10;
                num = num / 10;
            }
            num = sum;
        }
        return sum;
    }

    public int addDigits2(int num) {
        if (num == 0) {
            return 0;
        }
        if (num % 9 == 0) {
            return 9;
        }
        return num % 9;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i + " " + new Solution().addDigits(i));
        }

    }
}