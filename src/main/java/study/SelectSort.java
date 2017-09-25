package study;

import java.util.Arrays;
import java.util.Random;

/**
* @file SelectSort.java
* @CopyRight (C) http://blog.csdn.net/x_iya
* @Description 简单选择排序
* @author N3verL4nd
* @email lgh1992314@qq.com
* @date 2017/9/25
*/
public class SelectSort {
    // 交换数组中的两个数
    private static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    private static void SelectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minPos = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minPos]) {
                    minPos = j;
                }
            }
            swap(arr, i, minPos);
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

        // 冒泡排序
        SelectSort(arr);

        // 排序后的数组
        System.out.println(Arrays.toString(arr));
    }
}
