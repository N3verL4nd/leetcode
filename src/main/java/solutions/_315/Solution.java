package solutions._315;

import java.util.*;

/**
 * 315. Count of Smaller Numbers After Self
 */

public class Solution {
    private int[] arr;
    private int n;

    //内部类
    class Node implements Comparable<Node> {
        int pos;
        int value;

        public Node(int pos, int value) {
            this.pos = pos;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return value - o.value;
        }
    }

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
        n = nums.length;       // 树状数组长度
        arr = new int[n + 1];  // 树状数组

        List<Integer> result = new ArrayList<>();

        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i, nums[i]);
        }

        Arrays.sort(nodes);

        int i, j;
        for (i = 0, j = 1; i < n - 1; i++, j++) {
            nums[nodes[i].pos] = j;
            if (nodes[i].value == nodes[i + 1].value) {
                j--;
            }
        }

        if (i == n - 1) {
            nums[nodes[i].pos] = j;
        }

        System.out.println(Arrays.toString(nums));

        for (int k = n - 1; k >= 0; k--) {
            update(nums[k], 1);
            result.add(getSum(nums[k] - 1));
        }

        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {-1, -1};
        List<Integer> list = solution.countSmaller(arr);
        System.out.println(list);
    }
}