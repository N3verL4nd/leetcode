package solutions._4;

/**
 * 4. Median of Two Sorted Arrays
 */
class Solution {
    // O(m+n)
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        int mid = len / 2;
        int[] arr = new int[mid + 1];
        int i = 0, j = 0;
        int index = 0;
        while (index <= mid && i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                arr[index++] = nums1[i++];
            } else {
                arr[index++] = nums2[j++];
            }
        }
        while (index <= mid && i < nums1.length) {
            arr[index++] = nums1[i++];
        }
        while (index <= mid && j < nums2.length) {
            arr[index++] = nums2[j++];
        }
        if (len % 2 == 0) {
            return (arr[mid] + arr[mid - 1]) / 2.0;
        } else {
            return arr[mid];
        }
    }

    public double findMedianSortedArray1(int[] nums1, int[] nums2) {

        return 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr1 = {1, 2};
        int[] arr2 = {3, 4};
        System.out.println(solution.findMedianSortedArrays1(arr1, arr2));
    }
}