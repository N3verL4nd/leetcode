package solutions._88;

import java.util.Arrays;

/**
 * 88. Merge Sorted Array
 */
public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int index = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[index--] = nums1[i--];
            } else {
                nums1[index--] = nums2[j--];
            }
        }
        while (j >= 0) {
            nums1[index--] = nums2[j--];
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr1 = new int[10];
        int[] arr2 = {1, 2, 3};
        solution.merge(arr1, 0, arr2, 3);
        System.out.println(Arrays.toString(arr1));
    }
}