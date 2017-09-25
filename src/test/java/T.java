import org.junit.Test;

public class T {
    private int BinarySearch1(int[] arr, int left, int right, int target) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (arr[mid] > target) {
            return BinarySearch1(arr, left, mid - 1, target);
        } else if (arr[mid] < target) {
            return BinarySearch1(arr, mid + 1, right, target);
        } else {
            return mid;
        }

    }

    private int BinarySearch2(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > target) {
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }

    @Test
    public void test1() {
        int[] arr = {2, 4, 6, 8};
        System.out.println(BinarySearch1(arr, 0, arr.length - 1, 6));
        System.out.println(BinarySearch2(arr, 4));
    }
}
