package study.proto;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author N3verL4nd
 * @date 2022/2/16
 */
public class Test2 {
    /**
     * int to varint 编码
     *
     * @param n
     * @return
     */
    public static byte[] IntToVarInt(int n) {
        List<Byte> list = new ArrayList<>();
        while (true) {
            if ((n & ~0x7F) == 0) {
                list.add((byte) n);
                break;
            } else {
                list.add((byte) ((n & 0x7F) | 0x80));
                n >>>= 7;
            }
        }
        byte[] bytes = new byte[list.size()];

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    /**
     * long to zigZag 编码
     *
     * @param l
     * @return
     */
    public static long longToZigzag(long l) {
        return (l << 1) ^ (l >> 63);
    }

    /**
     * 对于负数，n >> 1 全为 1
     * 对于正数，n >> 1 全为 0
     *
     * @param n
     * @return
     */
    public static int intToZigZag(int n) {
        return (n << 1) ^ (n >> 31);
    }

    public static int zigZagToInt(int n) {
        return (n >>> 1) ^ -(n & 1);
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println(intToZigZag(-1));
        System.out.println(intToZigZag(Integer.MIN_VALUE));
        System.out.println(Arrays.toString(IntToVarInt(intToZigZag(Integer.MIN_VALUE))));
        System.out.println(Arrays.toString(IntToVarInt(-1)));
        System.out.println(Arrays.toString(IntToVarInt(Integer.MIN_VALUE)));
    }
}
