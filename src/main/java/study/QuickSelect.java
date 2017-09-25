package study;

/**
* @file QuickSelect.java
* @CopyRight (C) http://blog.csdn.net/x_iya
* @Description 快速选择算法
* @author N3verL4nd
* @email lgh1992314@qq.com
* @date 2017/9/24
*/
public class QuickSelect {

    private static int partition(int[] arr, int left, int right) {
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

    private static int QuickSelect(int[] arr, int left, int right, int k) {
        if (left < right) {
            int pivot = partition(arr, left, right);
            if (pivot == k - 1) {
                return arr[pivot];
            } else if (pivot > k - 1) {
                return QuickSelect(arr, left, pivot - 1, k);
            } else {
                return QuickSelect(arr, pivot + 1, right, k);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, -32, 1442, 203, 37, 874, 2, 193, 34};
        System.out.println(QuickSelect(arr, 0, arr.length - 1, 1));
        System.out.println(QuickSelect(arr, 0, arr.length - 1, 2));
        System.out.println(QuickSelect(arr, 0, arr.length - 1, 5));
        System.out.println(QuickSelect(arr, 0, arr.length - 1, 4));
        System.out.println(QuickSelect(arr, 0, arr.length - 1, 10));
    }
}
