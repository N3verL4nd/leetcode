import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class StringHashCodeTest2 {

    public static int hashCode(char[] value, int prim) {
        int h = 0;
        for (int i = 0; i < value.length; i++) {
            h = prim * h + value[i];
        }
        return h;
    }

    public static void main(String[] args) throws IOException {

        Reader reader = new FileReader("/usr/share/dict/words");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String string;
        int[] res = new int[32];
        int len = 0;
        while ((string = bufferedReader.readLine()) != null) {
            len++;
            String s = Integer.toBinaryString(hashCode(string.toCharArray(), 32));
            s = new StringBuilder(s).reverse().toString();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '1') {
                    res[i]++;
                }
            }
        }
        System.out.println("len = " + len);
        for (int i = 31; i >= 0; i--) {
            System.out.printf("%d  %.3f\n", i, res[i] * 1.0 / len);
        }
        bufferedReader.close();
        reader.close();
    }
}