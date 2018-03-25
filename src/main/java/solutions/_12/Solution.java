package solutions._12;

/**
 * 12. Integer to Roman
 */
class Solution {
    public String intToRoman(int num) {
        /**
         *     "M" "CM" "D" "CD" "C" "XC" "L" "XL" "X" "IX" "V" "IV" "I"
         *    1000 900  500 400  100  90  50  40   10   9    5   4    1
         */
        StringBuilder sb = new StringBuilder();
        String symbol[] = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int value[] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        for (int i = 0; num != 0; i++) {
            while (num >= value[i]) {
                num -= value[i];
                sb.append(symbol[i]);
            }
        }
        return sb.toString();
    }

    public String intToRoman2(int num) {
        //1000  2000  3000
        String M[] = {"", "M", "MM", "MMM"};
        //100 200 300 400 500 600 700 800 900
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        //10 20 30 40 50 60 70 80 90
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        //1 2 3 4 5 6 7 8 9
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num / 1000] +
                C[(num % 1000) / 100] +
                X[(num % 100) / 10] +
                I[num % 10];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        for (int i = 1; i <= 3999; i++) {
            System.out.println(i + ":" + solution.intToRoman(i));
        }
    }
}