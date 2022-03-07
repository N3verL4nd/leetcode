import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StringHashCodeTest {

    public static int hashCode(char[] value, int prim) {
        int h = 0;
        for (int i = 0; i < value.length; i++) {
            h = prim * h + value[i];
        }
        return h;
    }

    public static void calculateConflictRate(Integer multiplier, List<Integer> hashes) {
        Comparator<Integer> cp = (x, y) -> x > y ? 1 : (x < y ? -1 : 0);
        // 最大的 hashCode
        int maxHash = hashes.stream().max(cp).get();
        // 最小的 hashCode
        int minHash = hashes.stream().min(cp).get();

        // 没有冲突的 hashCode 累计和
        int uniqueHashNum = (int) hashes.stream().distinct().count();
        // 冲突的 hashCode 累计和
        int conflictNum = hashes.size() - uniqueHashNum;
        // 冲突率
        double conflictRate = (conflictNum * 1.0) / hashes.size();

        System.out.println(String.format("multiplier=%4d, minHash=%11d, maxHash=%10d, conflictNum=%6d, conflictRate=%.4f%%",
                multiplier, minHash, maxHash, conflictNum, conflictRate * 100));
    }

    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList<>(235886);
        Reader reader = new FileReader("/usr/share/dict/words");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String string;
        while ((string = bufferedReader.readLine()) != null) {
            list.add(string);
        }
        bufferedReader.close();
        reader.close();

        if (args == null || args.length == 0) {
            return;
        }

        for (String arg : args) {
            int prim = Integer.valueOf(arg);
            List<Integer> result = new ArrayList<>();
            for (String s : list) {
                result.add(hashCode(s.toCharArray(), prim));
            }
            calculateConflictRate(prim, result);
        }
    }
}