package solutions._397;

import java.util.HashMap;
import java.util.Map;

/**
 * 397. Integer Replacement
 */
class Solution {
    private Map<Integer, Integer> map = new HashMap<>();

    public int integerReplacement(int n) {
        if (n == 0 || n == 1) {
            return 0;
        }

        if (map.containsKey(n)) {
            return map.get(n);
        }

        if (n % 2 == 0) {
            int x = integerReplacement(n / 2) + 1;
            map.put(n, x);
            return x;
        } else {
            long m = n;
            int x = integerReplacement((int) ((m + 1) / 2)) + 2;
            int y = integerReplacement((int) ((m - 1) / 2)) + 2;
            if (x < y) {
                map.put(n, x);
                return x;
            } else {
                map.put(n, y);
                return y;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.integerReplacement(99999));
    }
}