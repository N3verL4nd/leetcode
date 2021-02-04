package solutions._560;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int subarraySum2(int[] nums, int k) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            if (sum == k) {
                result++;
            }
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    result++;
                }
            }
        }
        return result;
    }

    public int subarraySum(int[] nums, int k) {
        /**
         扫描一遍数组, 使用map记录出现同样的和的次数, 对每个i计算累计和sum并判断map内是否有sum-k
         **/
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        map.put(0, 1);
        int sum = 0, ret = 0;

        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                ret += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        System.out.println(map);
        return ret;
    }


    public int subarraySum3(int[] nums, int k) {
        int result = 0;
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
//        System.out.println(Arrays.toString(sum));
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= nums.length; i++) {
            
            for (int j = 0; j < i; j++) {
                if (sum[i] - k == sum[j]) {
                    result++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().subarraySum3(new int[]{1, 1, 1}, 2));
        System.out.println(new Solution().subarraySum3(new int[]{1, -1, 0}, 0));
    }
}


/*

给定一个整数数组和一个整数k，你需要找到该数组中和为k的连续的子数组的个数。

示例 1 :

输入:nums = [1,1,1], k = 2
输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
说明 :

数组的长度为 [1, 20,000]。
数组中元素的范围是 [-1000, 1000] ，且整数k的范围是[-1e7, 1e7]。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

sum(i,j)=sum(0,j)-sum(0,i), where sum(i,j) represents the sum of all the elements from index i to j-1. Can we use this property to optimize it.


 */