package exam;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main2 {

    static class Node {
        int m = 1;
        int s = 1;
        int count;

        public Node() {
        }

        public Node(int m, int s, int count) {
            this.m = m;
            this.s = s;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int result = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node());

        while (!queue.isEmpty()) {
            Node p = queue.poll();
            if (p.s == n) {
                result = p.count;
                break;
            }
            queue.add(new Node(p.s, p.s + p.s, p.count + 1));
            queue.add(new Node(p.m, p.m + p.s, p.count + 1));
        }
        System.out.println(result);
    }
}
