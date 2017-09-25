package study;

import java.util.*;

/**
* @file Discretization.java
* @CopyRight (C) http://blog.csdn.net/x_iya
* @Description 离散化
* @author N3verL4nd
* @email lgh1992314@qq.com
* @date 2017/9/11
*/
public class Discretization {

    class Node implements Comparable<Node> {
        int pos;
        int value;

        public Node(int pos, int value) {
            this.pos = pos;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            if (value == o.value) {
                return pos - o.pos;
            }
            return value - o.value;
        }
    }

    public void discrete(int[] arr) {
        int len = arr.length;
        Node[] nodes = new Node[len];
        for (int i = 0; i < len; i++) {
            nodes[i] = new Node(i, arr[i]);
        }

        Arrays.sort(nodes);

        int i, j;
        for (i = 0, j = 1; i < len - 1; i++, j++) {
            arr[nodes[i].pos] = j;
            if (nodes[i].value == nodes[i + 1].value) {
                j--;
            }
        }
        arr[nodes[i].pos] = j;
    }
    public static void main(String[] args) {
        // 构造随机序列
        Random random = new Random();
        int len = random.nextInt(10);
        int arr[] = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = random.nextInt(100);
        }

        // 打印随机序列
        System.out.println(Arrays.toString(arr));

        // 离散化
        Discretization discretization = new Discretization();
        discretization.discrete(arr);

        // 打印离散化后的随机序列
        System.out.println(Arrays.toString(arr));
    }
}
