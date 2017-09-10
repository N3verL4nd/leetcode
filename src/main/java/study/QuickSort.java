package study;

import java.util.Arrays;
import java.util.Random;

/**
* @file Main.java
* @CopyRight (C) http://blog.csdn.net/x_iya
* @Description 快速排序
* @author N3verL4nd
* @email lgh1992314@qq.com
* @date 2017/9/10
*/
public class QuickSort {

    private static int partition(int[] arr, int left, int right) {
        /**
         * 步骤 1: 设置基准元素
         * 这里的一个潜台词是 arr[left] 所对应的元素已经是无效的(已经由基准元素代替)
         */
        int pivot = arr[left];

        // 从数列右边开始找一个比基准元素小的元素
        while (left < right) {
            while (left < right && arr[right] >= pivot) {
                right--;
            }

            // 把一个比基准元素小的元素放置在无效位置上
            // 此时 arr[right] 又变成了无效元素
            arr[left] = arr[right];

            // 从数列坐边开始找一个比基准元素大的元素
            while (left < right && arr[left] <= pivot) {
                left++;
            }

            // 把一个比基准元素大的元素放置在无效位置上
            arr[right] = arr[left];
        }
        arr[left] = pivot;
        return left;
    }

    // 步骤3()
    private static void QuickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivot = partition(arr, left, right);
            QuickSort(arr, left, pivot - 1);
            QuickSort(arr, pivot + 1, right);
        }
    }
    public static void main(String[] args) {
        // 构建随机数组
        Random random = new Random();
        int len = random.nextInt(10);
        int[] arr = new int[len];

        // 填充随机数组
        for (int i = 0; i < len; i++) {
            arr[i] = random.nextInt(100);
        }

        // 排序前的数组
        System.out.println(Arrays.toString(arr));

        // 快速排序
        QuickSort(arr, 0, len - 1);

        // 排序后的数组
        System.out.println(Arrays.toString(arr));
    }
}
