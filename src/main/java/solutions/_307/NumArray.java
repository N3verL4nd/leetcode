package solutions._307;

/**
 * 307. Range Sum Query - Mutable
 */
public class NumArray {
    private int[] arr;//树状数组
    private int[] nums;//输入数组


    private int LowBit(int x) {
        return x & (-x);
    }

    private void updates(int pos, int val) {
        while (pos <= nums.length) {
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
        this.nums = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            this.nums[i] = nums[i];
            updates(i + 1, nums[i]);
        }

    }

    public void update(int i, int val) {
        int add = val - nums[i];
        nums[i] = val;
        updates(i + 1, add);
    }

    public int sumRange(int i, int j) {
        return getSum(j + 1) - getSum(i);
    }

    public static void main(String[] args) {
        int[] arr = {7, 2, 7, 2, 0};
        NumArray numArray = new NumArray(arr);
        numArray.update(4, 6);
        numArray.update(0, 2);
        numArray.update(0, 9);
        System.out.println(numArray.sumRange(4, 4));
        numArray.update(3, 8);
        System.out.println(numArray.sumRange(0, 4));
        numArray.update(4, 1);
        System.out.println(numArray.sumRange(0, 3));
        System.out.println(numArray.sumRange(0, 4));
        numArray.update(0, 4);
    }
}