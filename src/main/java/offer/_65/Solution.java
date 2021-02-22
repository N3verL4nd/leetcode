package offer._65;

class Solution {
    public int add(int a, int b) {
        if (b == 0) {
            return a;
        }
        return add(a ^ b, (a & b) << 1);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().add(123, 456));
    }
}