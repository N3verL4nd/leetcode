package solutions._739;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
    public int[] dailyTemperatures2(int[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int pos = 0;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[i]) {
                    pos = j;
                    break;
                }
            }
            if (pos == 0) {
                result[i] = 0;
            } else {
                result[i] = pos - i;
            }
        }
        return result;
    }

    public int[] dailyTemperatures(int[] arr) {
        int[] result = new int[arr.length];
        Deque<Integer> stack = new ArrayDeque<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                int val = stack.pop();
                result[val] = i - val;
            }
            stack.push(i);
        }
        return result;
    }


    public static void main(String[] args) {
        int[] ints = new Solution().dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
        System.out.println(Arrays.toString(ints));
    }
}

/*

请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用0 来代替。

例如，给定一个列表temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是[1, 1, 4, 2, 1, 1, 0, 0]。

提示：气温 列表长度的范围是[1, 30000]。每个气温的值的均为华氏度，都是在[30, 100]范围内的整数。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/daily-temperatures
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */