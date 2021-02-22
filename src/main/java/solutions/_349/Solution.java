package solutions._349;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            set2.add(num);
        }
        set1.retainAll(set2);
        int[] result = new int[set1.size()];
        int pos = 0;
        for (Integer integer : set1) {
            result[pos++] = integer;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        System.out.println(Arrays.toString(solution.intersection(nums1, nums2)));
    }
}