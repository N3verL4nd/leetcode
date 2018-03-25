package study;

import java.util.Arrays;
import java.util.Random;

/**
 * @author N3verL4nd
 * @file MergeSort.java
 * @CopyRight (C) http://blog.csdn.net/x_iya
 * @Description 归并排序
 * @email lgh1992314@qq.com
 * @date 2018/3/18
 */
public class MergeSort {
    /**
     * 将二个有序数列 arr[left ... mid] 和 arr[mid + 1 ... right] 合并 --> O(n)
     */
    private static void Merge(int[] arr, int left, int mid, int right) {
        int i = left, j = mid + 1, index = 0;
        int[] tmp = new int[right - left + 1];
        while (i <= mid && j <= right) {
            if (arr[i] > arr[j]) {
                tmp[index++] = arr[j++];
            } else {
                tmp[index++] = arr[i++];
            }
        }
        while (i <= mid) {
            tmp[index++] = arr[i++];
        }
        while (j <= right) {
            tmp[index++] = arr[j++];
        }
        for (int k = 0; k < index; k++) {
            arr[k + left] = tmp[k];
        }
    }

    private static void MergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + ((right - left) >> 1);
            MergeSort(arr, left, mid);          // 左边有序
            MergeSort(arr, mid + 1, right); // 右边有序
            Merge(arr, left, mid, right);       // 合并两个有序数组
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        int len = random.nextInt(10);
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = random.nextInt(100);
        }
//        int[] arr = {5, 2, 4, 6, 1, 3, 2, 6};
        System.out.println(Arrays.toString(arr));
        MergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
