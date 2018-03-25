package solutions._442;

import java.util.ArrayList;
import java.util.List;

/**
 * 287. Find the Duplicate Number
 */
class SolutionNew {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int pos = Math.abs(nums[i]) - 1;
            if (nums[pos] < 0) {
                result.add(Math.abs(nums[i]));
            } else {
                nums[pos] = -nums[pos];
            }
        }
        return result;
    }

    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    public List<Integer> findDuplicates2(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int pos = nums[i] - 1;
            if (nums[i] != nums[pos]) {
                swap(nums, i, pos);
                i--;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                result.add(nums[i]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SolutionNew solution = new SolutionNew();
        List<Integer> list = solution.findDuplicates2(new int[]{10, 2, 5, 10, 9, 1, 1, 4, 3, 7});
        System.out.println(list);
    }
}