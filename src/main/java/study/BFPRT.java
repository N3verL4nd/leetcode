package study;

import java.util.Arrays;

/**
* @file BFPRT.java
* @CopyRight (C) http://blog.csdn.net/x_iya
* @Description 中位数的中位数算法
* @author N3verL4nd
* @email lgh1992314@qq.com
* @date 2017/9/24
*/
public class BFPRT {
    // 对每一组中的5个元素进行插入排序
    private void insertSort(int[] arr, int left, int right) {
        int i, j;
        for (i = left + 1; i <= right; i++) {
            int x = arr[i];
            for (j = i - 1; j >= left && x < arr[j]; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = x;
        }
    }
    public static void main(String[] args) {
        BFPRT bfprt = new BFPRT();
        int[] arr = {2, 5, 1, 3, 4};
        bfprt.insertSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
