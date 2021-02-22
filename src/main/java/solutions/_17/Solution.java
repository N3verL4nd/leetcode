package solutions._17;

/**
 * 17. Letter Combinations of a Phone Number
 */

import java.util.LinkedList;
import java.util.List;

class Solution {
    private char[][] maps;
    private List<String> result;
    private StringBuilder cur;
    private char[] digits;

    public Solution() {
        maps = new char[10][4];
        maps[2] = new char[]{'a', 'b', 'c'};
        maps[3] = new char[]{'d', 'e', 'f'};
        maps[4] = new char[]{'g', 'h', 'i'};
        maps[5] = new char[]{'j', 'k', 'l'};
        maps[6] = new char[]{'m', 'n', 'o'};
        maps[7] = new char[]{'p', 'q', 'r', 's'};
        maps[8] = new char[]{'t', 'u', 'v'};
        maps[9] = new char[]{'w', 'x', 'y', 'z'};

        cur = new StringBuilder();
        result = new LinkedList<>();
    }

    private void DFS(int i) {
        if (i  == digits.length) {
            result.add(cur.toString());
            return;
        }

        int x = digits[i] - '0';
        if (x != 7 && x != 9) {
            for (int j = 0; j < 3; j++) {
                cur.append(maps[x][j]);
                DFS(i + 1);
                cur.delete(cur.length() - 1, cur.length());
            }
        } else {
            for (int j = 0; j < 4; j++) {
                cur.append(maps[x][j]);
                DFS(i + 1);
                cur.delete(cur.length() - 1, cur.length());
            }
        }
    }
    public List<String> letterCombinations(String digits) {
        this.digits = digits.toCharArray();
        if (digits.length() == 0) {
            return result;
        }
        DFS(0);
        return result;
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "234";
        List<String> list = solution.letterCombinations(str);
        System.out.println(list);
    }
}