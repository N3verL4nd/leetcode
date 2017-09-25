package solutions._15;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class New {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new LinkedList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (nums[i - 1] != nums[i])) {
                int left = i + 1;
                int right = nums.length - 1;
                int target = -nums[i];

                while (left < right) {
                    if (nums[left] + nums[right] == target) {
                        lists.add(Arrays.asList(nums[left], nums[i], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else if (nums[left] + nums[right] < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }

        }
        return lists;
    }

    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 2, -1, -4};
        Solution solution = new Solution();
        List<List<Integer>> list = solution.threeSum(arr);
        System.out.println(list);
    }
}
