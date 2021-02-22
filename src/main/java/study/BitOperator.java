package study;

/**
 * @author liguanghui02
 * @date 2020/12/29
 */
public class BitOperator {
    /**
     * 两数相加
     *
     * @param x
     * @param y
     * @return
     */
    private static int add(int x, int y) {
        if (y == 0) {
            return x;
        }
        return add(x ^ y, (x & y) << 1);
    }

    public static void main(String[] args) {
        System.out.println(add(123, 456));
    }
}
