import java.util.Arrays;

public class Solution {

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

    private static void QuickSort(int[] arr, int left ,int right) {
        if (left < right) {
            int pos = partition(arr, left, right);
            QuickSort(arr, left, pos - 1);
            QuickSort(arr, pos + 1, right);
        }
    }

    public static void main(String[] args) {
        int[] arr = {72, 6, 57, 88, 60, 42, 83, 73, 48, 85};
        QuickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}