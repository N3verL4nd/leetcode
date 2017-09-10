package solutions._315;

import java.util.*;

/**
 * 315. Count of Smaller Numbers After Self
 */

public class Solution {
    //内部类
    class pair implements Comparable<pair>{
        private Integer key;
        private Integer val;

        public pair() {
        }

        public pair(Integer key, Integer val) {
            this.key = key;
            this.val = val;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public Integer getVal() {
            return val;
        }

        public void setVal(Integer val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "pair{" +
                    "key=" + key +
                    ", val=" + val +
                    '}';
        }

        @Override
        public int compareTo(pair o) {
            if (Objects.equals(val, o.val)) {
                return key - o.key;
            } else {
                return val - o.val;
            }
        }
    }


    private int[] arr;
    private int n;
    private int LowBit(int x) {
        return x & (-x);
    }

    private void update(int pos, int val) {
        while (pos <= n) {
            arr[pos] += val;
            pos += LowBit(pos);
        }
    }

    private int getSum(int pos) {
        int sum = 0;
        while (pos > 0) {
            sum += arr[pos];
            pos -= LowBit(pos);
        }
        return sum;
    }

    public List<Integer> countSmaller(int[] nums) {
        arr = new int[nums.length + 1];//"树状数组"
        n = nums.length;//"树状数组"长度
        List<Integer> result = new LinkedList<>();

        //离散化
        int[] temp = new int[nums.length + 1];
        List<pair> list = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            list.add(new pair(i + 1, nums[i]));
        }

        Collections.sort(list);

        int pos = 1;
        for (pair p : list) {
            temp[p.getKey()] = pos++;
        }

//        System.out.println(list);
//        System.out.println(Arrays.toString(temp));

        for (int i = n; i >= 1; i--) {
            update(temp[i], 1);
            result.add(getSum(temp[i]) - 1);
        }
        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {-1, -1};
        List<Integer> list = solution.countSmaller(arr);
        list.forEach(System.out::println);
    }
}