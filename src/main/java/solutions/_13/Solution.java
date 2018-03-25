package solutions._13;

import java.util.HashMap;
import java.util.Map;

/**
 * 13. Roman to Integer
 */
public class Solution {
    private Map<Character, Integer> map = new HashMap<>();

    public Solution() {
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
    }

    public int romanToInt(String s) {
        char[] arr = s.toCharArray();
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            int val = map.get(arr[i]);
            if (i == arr.length - 1 || map.get(arr[i + 1]) <= map.get(arr[i])) {
                result += val;
            } else {
                result -= val;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.romanToInt("CMXCVIII"));
    }
}