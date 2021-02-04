package solutions._204;

class Solution {

    private boolean isPrime(int num) {
        int tmp = (int) Math.sqrt(num);
        for (int i = 2; i <= tmp; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isPrime_2(int num) {
        if (num <= 3) {
            return num > 1;
        }
        // 不在6的倍数两侧的一定不是质数
        if (num % 6 != 1 && num % 6 != 5) {
            return false;
        }
        int sqrt = (int) Math.sqrt(num);
        for (int i = 5; i <= sqrt; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    public int countPrimes(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().countPrimes(12));
    }
}