package solutions._451;

import java.util.Arrays;

/**
 * 451. Sort Characters By Frequency
 */
class Solution {
    private static final int len = 68;
    class Pair implements Comparable<Pair> {
        Character key;
        int value;

        @Override
        public int compareTo(Pair o) {
            return o.value - value;
        }
    }

    public String frequencySort(String s) {
        Pair[] pairs = new Pair[len];
        for (int i = 0; i < len; i++) {
            pairs[i] = new Pair();
        }

        char[] str = s.toCharArray();
        for (char c : str) {
            if (Character.isLowerCase(c)) {
                pairs[c - 'a'].key = c;
                pairs[c - 'a'].value++;
            }
            else if (Character.isUpperCase(c)){
                pairs[c - 'A' + 26].key = c;
                pairs[c - 'A' + 26].value++;
            } else if (Character.isDigit(c)) {
                pairs[c - '0' + 52].key = c;
                pairs[c - '0' + 52].value++;
            } else if (c == ' ') {
                pairs[62].key = c;
                pairs[62].value++;
            } else if (c == '"') {
                pairs[63].key = c;
                pairs[63].value++;
            } else if (c == '\\') {
                pairs[64].key = c;
                pairs[64].value++;
            } else if (c == ',') {
                pairs[65].key = c;
                pairs[65].value++;
            } else if (c == '\'') {
                pairs[66].key = c;
                pairs[66].value++;
            } else if (c == '.') {
                pairs[67].key = c;
                pairs[67].value++;
            }
        }
        Arrays.sort(pairs);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (pairs[i].key != null) {
                for (int j = 0; j < pairs[i].value; j++) {
                    sb.append(pairs[i].key);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.frequencySort("Mymommaalwayssaid,\\\"Lifewaslikeaboxofchocolates.Youneverknowwhatyou'regonnaget."));
    }
}