package study;

import java.util.Arrays;
import java.util.Random;

/**
* @file InsertSort.java
* @CopyRight (C) http://blog.csdn.net/x_iya
* @Description 插入排序
* @author N3verL4nd
* @email lgh1992314@qq.com
* @date 2017/9/25
*/
public class InsertSort {

    private static void InsertSort(int[] arr) {
        int i, j;
        for (i = 1; i < arr.length; i++) {
            int temp = arr[i];
            for (j = i - 1; j >= 0 && temp < arr[j]; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = temp;
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
        InsertSort(arr);

        // 排序后的数组
        System.out.println(Arrays.toString(arr));
    }
}
