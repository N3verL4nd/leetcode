package solutions._215;

/**
 * 215. Kth Largest Element in an Array
 */
class Solution {
    private int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= pivot) {
                right--;
            }
            arr[left] = arr[right];

            while (left < right && arr[left] <= pivot) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = pivot;
        return left;
    }

    private int QuickSelect(int[] arr, int left, int right, int k) {
        if (left < right) {
            int pivot = partition(arr, left, right);
            System.out.println("pivot = " + pivot);
            if (pivot == k - 1) {
                return arr[pivot];
            } else if (pivot > k - 1) {
                return QuickSelect(arr, left, pivot - 1, k);
            } else {
                return QuickSelect(arr, pivot + 1, right, k);
            }
        }
        return arr[left];
    }

    public int findKthLargest(int[] nums, int k) {
        return QuickSelect(nums, 0, nums.length - 1, nums.length - k + 1);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {1};
        System.out.println(solution.findKthLargest(arr, 1));
    }
}