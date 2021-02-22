package proxy.test;

/**
 * @author liguanghui02
 * @date 2021/2/5
 */
public class ClassTest {
    public static void main(String[] args) {
        System.out.println(ClassTest.class.getResource("Demo"));
        System.out.println(ClassTest.class.getClassLoader().getResource("proxy.test.Demo"));

        int[] arr = new int[]{1, 2, 3, 5};
//        System.out.println(bytesToHex(arr));
    }


    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
