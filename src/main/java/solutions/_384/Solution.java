package solutions._384;

import java.util.Arrays;
import java.util.Random;

class Solution {

    private int[] nums;
    private Random random = new Random();


    public Solution(int[] nums) {
        this.nums = nums;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        if (nums == null) {
            return null;
        }
        int[] temp = nums.clone();
        for (int i = temp.length - 1; i >= 0; i--) {
            int j = random.nextInt(i + 1);
            swap(temp, i, j);
        }
        return temp;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        Solution solution = new Solution(nums);
        System.out.println(Arrays.toString(solution.shuffle()));
        System.out.println(Arrays.toString(solution.shuffle()));
        System.out.println(Arrays.toString(solution.shuffle()));
        System.out.println(Arrays.toString(solution.shuffle()));
        System.out.println(Arrays.toString(solution.shuffle()));
        System.out.println(Arrays.toString(solution.shuffle()));
        System.out.println(Arrays.toString(solution.reset()));
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */