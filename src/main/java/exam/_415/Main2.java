package exam._415;

import java.util.Scanner;

/**
 * 拼硬币
 */
public class Main2 {
    private static long count;
//    private static boolean flag;


    private static void DFS(int[] arr1, int[] arr2, int pos, int cur, int m) {
        if (cur == m) {
            count++;
            return;
        }
        if (cur > m) {
            return;
        }


        for (int i = pos; i < arr1.length; i++) {
            if (cur + arr1[i] <= m) {
                DFS(arr1, arr2, i, cur + arr1[i], m);
            } else {
                break;
            }
        }

        for (int j = 0; j < arr2.length; j++) {
            if (cur + arr2[0] > m) {
                return;
            }
            if (cur + arr2[j] == m) {
                count++;
                return;
            }
        }

        /*if (flag) {
            return;
        }

        for (int i = 0; i < arr2.length; i++) {
            if (cur + arr2[i] == m) {
                flag = true;
                DFS(arr1, arr2, cur + arr2[i], m);
                flag = false;
            }
        }*/
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n1 = in.nextInt();
        int n2 = in.nextInt();
        int m = in.nextInt();
        int[] arr1 = new int[n1];
        int[] arr2 = new int[n2];
        for (int i = 0; i < n1; i++) {
            arr1[i] = in.nextInt();
        }
        for (int i = 0; i < n2; i++) {
            arr2[i] = in.nextInt();
        }
        for (int i = 0; i < n1; i++) {
            DFS(arr1, arr2, i, arr1[i], m);
        }

        System.out.println(count % 1000000007);
    }
}
