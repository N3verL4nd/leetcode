package exam;


import java.util.LinkedList;
import java.util.Scanner;

public class Main3 {
    private static char[][][] show = new char[10][][];

    static {
        show[0] = new char[][]{
                {'6', '6', '6', '6', '6'},
                {'6', '.', '.', '.', '6'},
                {'6', '.', '.', '.', '6'},
                {'6', '.', '.', '.', '6'},
                {'6', '6', '6', '6', '6'}};
        show[1] = new char[][]{
                {'.', '.', '.', '.', '6'},
                {'.', '.', '.', '.', '6'},
                {'.', '.', '.', '.', '6'},
                {'.', '.', '.', '.', '6'},
                {'.', '.', '.', '.', '6'}};
        show[2] = new char[][]{
                {'6', '6', '6', '6', '6'},
                {'.', '.', '.', '.', '6'},
                {'6', '6', '6', '6', '6'},
                {'6', '.', '.', '.', '.'},
                {'6', '6', '6', '6', '6'}};
        show[3] = new char[][]{
                {'6', '6', '6', '6', '6'},
                {'.', '.', '.', '.', '6'},
                {'6', '6', '6', '6', '6'},
                {'.', '.', '.', '.', '6'},
                {'6', '6', '6', '6', '6'}};
        show[4] = new char[][]{
                {'6', '.', '.', '.', '6'},
                {'6', '.', '.', '.', '6'},
                {'6', '6', '6', '6', '6'},
                {'.', '.', '.', '.', '6'},
                {'.', '.', '.', '.', '6'}};
        show[5] = new char[][]{
                {'6', '6', '6', '6', '6'},
                {'6', '.', '.', '.', '.'},
                {'6', '6', '6', '6', '6'},
                {'6', '.', '.', '.', '6'},
                {'6', '6', '6', '6', '6'}};
        show[6] = new char[][]{
                {'6', '6', '6', '6', '6'},
                {'6', '.', '.', '.', '.'},
                {'6', '6', '6', '6', '6'},
                {'6', '.', '.', '.', '6'},
                {'6', '6', '6', '6', '6'}};
        show[7] = new char[][]{
                {'6', '6', '6', '6', '6'},
                {'.', '.', '.', '.', '6'},
                {'.', '.', '.', '.', '6'},
                {'.', '.', '.', '.', '6'},
                {'.', '.', '.', '.', '6'}};
        show[8] = new char[][]{
                {'6', '6', '6', '6', '6'},
                {'6', '.', '.', '.', '6'},
                {'6', '6', '6', '6', '6'},
                {'6', '.', '.', '.', '6'},
                {'6', '6', '6', '6', '6'}};

        show[9] = new char[][]{
                {'6', '6', '6', '6', '6'},
                {'6', '.', '.', '.', '6'},
                {'6', '6', '6', '6', '6'},
                {'.', '.', '.', '.', '6'},
                {'6', '6', '6', '6', '6'}};

    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            String str = in.next();
            char[] arr = str.toCharArray();
            LinkedList<Integer> nums = new LinkedList<>();
            LinkedList<Character> symbols = new LinkedList<>();
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == '6') {
                    nums.push(6);
                } else {
                    if ((!symbols.isEmpty())) {
                        if (arr[j] >= symbols.peek()) {
                            int x = nums.pop();
                            int y = nums.pop();
                            Character c = symbols.pop();
                            symbols.push(arr[j]);
                            if (c == '+') {
                                nums.push(x + y);
                            } else if (c == '-') {
                                nums.push(x - y);
                            } else {
                                nums.push(x * y);
                            }
                        } else {
                            symbols.push(arr[j]);
                        }
                    } else {
                        symbols.push(arr[j]);
                    }
                }
            }
            while (!symbols.isEmpty()) {
                Character c = symbols.pop();
                int x = nums.pop();
                int y = nums.pop();
                if (c == '+') {
                    nums.push(x + y);
                } else if (c == '-') {
                    nums.push(x - y);
                } else {
                    nums.push(x * y);
                }
            }
            char[] chars = String.valueOf(nums.pop()).toCharArray();
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < chars.length; k++) {
                    for (int l = 0; l < 5; l++) {
                        System.out.print(show[chars[k] - '0'][j][l]);
                    }
                    if (k != chars.length - 1)
                        System.out.print("..");
                }
                System.out.println();
            }
        }
    }
}
