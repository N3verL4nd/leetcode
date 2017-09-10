import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class pair implements Comparable<pair>{
    private Integer key;
    private Integer val;

    public pair() {
    }

    public pair(Integer key, Integer val) {
        this.key = key;
        this.val = val;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "pair{" +
                "key=" + key +
                ", val=" + val +
                '}';
    }

    @Override
    public int compareTo(pair o) {
        if (val == o.val) {
            return key - o.key;
        } else {
            return val - o.val;
        }
    }
}

public class Solution {

    public static void main(String[] args) {
        List<pair> list = new ArrayList<>();
        list.add(new pair(5, 1));
        list.add(new pair(2, 2));
        list.add(new pair(6, 3));
        list.add(new pair(1, 4));

        list.forEach(pair -> System.out.println(pair.getKey() + " " + pair.getVal()));
    }
}