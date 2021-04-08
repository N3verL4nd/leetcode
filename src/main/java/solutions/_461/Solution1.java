package solutions._461;

class Solution1 {
    public int hammingDistance(int x, int y) {
        int len = 0;
        while (x != 0 && y != 0) {
            int a = x & 1;
            int b = y & 1;
            if (a != b) {
                len++;
            }
            x = x >> 1;
            y = y >> 1;
        }
        while(x != 0) {
            int a = x & 1;
            if (a == 1) {
                len++;
            }
            x = x >> 1;
        }
            while(y != 0) {
            int b = y & 1;
            if (b == 1) {
                len++;
            }
            y = y >> 1;
        }
        return len;
    }

    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        System.out.println(solution.hammingDistance(1, 3));
        System.out.println(solution.hammingDistance(1, 4));
    }
}