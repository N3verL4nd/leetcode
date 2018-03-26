package test;


public class Solution {
    private int BinarySearchLeft(int[] arr, int left, int right, int target) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target <= arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (left < arr.length && arr[left] == target) {
            return left;
        }
        return -1;
    }

    private int BinarySearchRight(int[] arr, int left, int right, int target) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target >= arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (right >= 0 && arr[right] == target) {
            return right;
        }
        return -1;
    }

    public int GetNumberOfK(int[] array, int k) {
        int leftPos = BinarySearchLeft(array, 0, array.length - 1, k);
        if (leftPos == -1) {
            return 0;
        }
        int rightPos = BinarySearchRight(array, 0, array.length - 1, k);
        return rightPos - leftPos + 1;
    }

    public static void main(String[] args) {
        char x = '0' + 9;
        System.out.println(x);
    }
}

