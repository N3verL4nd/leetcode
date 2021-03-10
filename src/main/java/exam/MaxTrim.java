package exam;

import java.util.Arrays;

/**
 * @author liguanghui02
 * @date 2021/3/9
 */
public class MaxTrim {

    public int maxDiff(int[] array) {
        if (array == null || array.length <= 1) {
            return -1;
        }

        int[] dp = new int[array.length];
        dp[0] = 0;

        // 前面子数组最大值
        int sum = array[0];
        for (int i = 1; i < array.length; i++) {
            dp[i] = sum - array[i];
            sum = Math.max(sum, array[i]);
        }
        System.out.println(Arrays.toString(dp));
        return Arrays.stream(dp).max().getAsInt();
    }

    public static void main(String[] args) {
        System.out.println(new MaxTrim().maxDiff(new int[]{0, 4, -5, 9, 8}));
    }
}

/*
求数组最大差值
给定一个无序数组A，求max(Ai - Aj) (i < j)，比如[0, 4, -5,9, 8],最大的间隔差值就是4-(-5) = 9.
这个只需要保存当前元素前面的子数组中的最大元素值，然后减去当前值即可。
 */
