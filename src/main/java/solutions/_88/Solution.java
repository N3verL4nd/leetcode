package solutions._88;

import java.util.Arrays;

/**
 * 88. Merge Sorted Array
 */
public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] result = new int[m + n];
        int i = 0, j = 0, pos = 0;
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                result[pos++] = nums1[i++];
            } else {
                result[pos++] = nums2[j++];

            }
        }
        if (i == m) {
            for (i = j; i < n; i++) {
                result[pos++] = nums2[i];
            }
        }

        if (j == n) {
            for (j = i; j < m; j++) {
                result[pos++] = nums1[j];
            }
        }
        for (i = 0; i < (m + n); i++) {
            nums1[i] = result[i];
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr1 = new int[0];
        int[] arr2 = {1};
        solution.merge(arr1, 0, arr2, 1);
        System.out.println(Arrays.toString(arr1));
    }
}