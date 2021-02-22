package solutions._860;

/**
 * @author liguanghui02
 * @date 2020/12/18
 */
class Solution {
    public boolean lemonadeChange(int[] bills) {
        int[] count = new int[3];
        for (int bill : bills) {
            if (bill == 5) {
                count[0]++;
            } else if (bill == 10) {
                if (count[0] == 0) {
                    return false;
                }
                count[0]--;
                count[1]++;
            } else {
                if (count[1] == 0) {
                    if (count[0] >= 3) {
                        count[0] -= 3;
                        count[2]++;
                    } else {
                        return false;
                    }
                } else {
                    if (count[0] >= 1) {
                        count[1]--;
                        count[0]--;
                        count[2]++;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        System.out.println(new Solution().lemonadeChange(new int[]{5, 5, 5, 10, 20}));
//        System.out.println(new Solution().lemonadeChange(new int[]{10, 20}));
//        System.out.println(new Solution().lemonadeChange(new int[]{5, 5, 10, 10, 20}));
        System.out.println(new Solution().lemonadeChange(new int[]{5, 5, 5, 5, 20, 20, 5, 5, 20, 5}));
    }
}

