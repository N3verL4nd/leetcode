package solutions._217;

import java.util.HashSet;
import java.util.Set;

/**
 * 217. Contains Duplicate
 */
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            if (set.contains(x)) {
                return true;
            } else {
                set.add(x);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {1, 2, 3, 4, 5, 1};
        System.out.println(solution.containsDuplicate(arr));
    }
}