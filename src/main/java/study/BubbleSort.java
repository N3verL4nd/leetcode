package study;

import java.util.Arrays;
import java.util.Random;

/**
* @file BubbleSort.java
* @CopyRight (C) http://blog.csdn.net/x_iya
* @Description 冒泡排序
* @author N3verL4nd
* @email lgh1992314@qq.com
* @date 2017/9/10
*/
public class BubbleSort {

    // 交换数组中的两个元素
    private static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    private static void BubbleSort(int[] arr) {
        // 标识是否进行了交换
        boolean flag;
        for (int i = 0; i < arr.length; i++) {
            flag = false;
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    flag = true;
                    swap(arr, j - 1, j);
                }
            }
            // 扫描一遍如果没有发生交换操作，则证明数组已经有序
            if (!flag) {
                break;
            }
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
        BubbleSort(arr);

        // 排序后的数组
        System.out.println(Arrays.toString(arr));
    }
}
