package solutions._493;

class Solution {

    private int[] arr;
    private int MAXN = 50000;

    private int LowBit(int x) {
        return x & (-x);
    }

    private int getSum(int pos) {
        int sum = 0;
        while (pos > 0) {
            sum += arr[pos];
            pos -= LowBit(pos);
        }
        return sum;
    }

    private void update(int pos, int val) {
        while (pos <= MAXN) {
            arr[pos] += val;
            pos += LowBit(pos);
        }
    }

    public int reversePairs(int[] nums) {
        int count = 0;
        arr = new int[MAXN + 1];
        for (int i = 0; i < nums.length; i++) {
            update(nums[i], 1);
            count += (i + 1 - getSum(nums[i]));
        }
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {99, 88, 77};
        System.out.println(solution.reversePairs(arr));
    }
}