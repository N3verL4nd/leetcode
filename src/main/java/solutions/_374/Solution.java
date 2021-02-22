package solutions._374;

/**
 * 374. Guess Number Higher or Lower
 */

public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int left = 1, right = n;
        while (left <= right) {
//            int mid = left + (right - left) >> 1;
            int mid = left + (right - left) / 2;
            int flag = guess(mid);
            if (flag == -1) {
                right = mid - 1;
            } else if (flag == 1){
                left = mid + 1;
            } else if (flag == 0){
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

    }
}