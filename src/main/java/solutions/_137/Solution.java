package solutions._137;

/**
 * 137. Single Number II
 * <p>
 * Given an array of integers,
 * every element appears three times except for one,
 * which appears exactly once. Find that single one.
 * </p>
 */
public class Solution {
    public int singleNumber(int[] nums) {
        int[] count = new int[32];
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < nums.length; j++) {
                if ((nums[j] & 1) == 1) {
                    count[i]++;
                }
                nums[j] >>= 1;
            }
        }
//        System.out.println(Arrays.toString(count));
        int result = 0;
        for (int i = 0; i < 32; i++) {
            if ((count[i] % 3) != 0) {
                result |= (1 << i);
            }
        }
        return result;
    }

    public int singleNumber1(int[] nums) {
        int result = 0;
        int count;
        for (int i = 0; i < 32; i++) {
            count = 0;
            for (int j = 0; j < nums.length; j++) {
                if ((nums[j] & 1) == 1) {
                    count++;
                }
                nums[j] >>= 1;
            }
            result |= ((count % 3) << i);
        }
        return result;
    }


    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 3};
        Solution solution = new Solution();
        System.out.println(solution.singleNumber1(arr));
    }
}