package solutions._70;

/**
 * 70. Climbing Stairs
 */
public class Solution {
    private int[] result = new int[1000];

    public Solution() {
        result[1] = 1;
        result[2] = 2;
    }

    public int climbStairs(int target) {
        if (result[target] != 0) {
            return result[target];
        }

        result[target] = climbStairs(target - 1) + climbStairs(target - 2);
        return result[target];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().climbStairs(3));
    }
}

