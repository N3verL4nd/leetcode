package exam;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        Set<Integer> set = new HashSet<>(1000005);
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            set.add(arr[i]);
        }
        Iterator<Integer> iterator = set.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            if (set.contains(iterator.next() + k)) {
                count++;
            }
        }

        System.out.println(count);
    }
}
