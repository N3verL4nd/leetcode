package solutions._303;

/**
 * 303. Range Sum Query - Immutable
 */
public class NumArray {
    private int[] arr;
    private int len;


    private int LowBit(int x) {
        return x & (-x);
    }

    private void update(int pos, int val) {
        while (pos <= len) {
            arr[pos] += val;
            pos += LowBit(pos);
        }
    }

    private int getSum(int pos) {
        int sum = 0;
        while (pos >= 1) {
            sum += arr[pos];
            pos -= LowBit(pos);
        }
        return sum;
    }

    public NumArray(int[] nums) {
        arr = new int[nums.length + 1];
        len = nums.length;
        for (int i = 0; i < nums.length; i++) {
            update(i + 1, nums[i]);
        }

    }
    
    public int sumRange(int i, int j) {
        return getSum(j + 1) - getSum(i);
    }

    public static void main(String[] args) {
        int[] arr = {-2, 0, 3, -5, 2, -1};
        NumArray numArray = new NumArray(arr);
        System.out.println(numArray.sumRange(0, 2));
        System.out.println(numArray.sumRange(2, 5));
        System.out.println(numArray.sumRange(0, 5));
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */