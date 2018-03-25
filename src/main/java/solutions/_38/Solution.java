package solutions._38;

/**
 * 38. Count and Say
 */
class Solution {
    private StringBuilder sb = new StringBuilder();

    private String DFS(int i, int n) {
        if (n == 1) {
            return "1";
        }
        if (i == n) {
            return sb.toString();
        }
        char[] arr = sb.toString().toCharArray();
        sb.delete(0, sb.length());
        int j = 0;
        int count = 1;
        while (j < arr.length - 1) {
            if (arr[j] == arr[j + 1]) {
                count++;
            } else {
                sb.append(count);
                sb.append(arr[j]);
                count = 1;
            }
            j++;
        }
        if (arr.length == 1) {
            sb.append(1);
            sb.append(arr[0]);
        } else if (arr.length >= 2) {
            if (arr[arr.length - 1] != arr[arr.length - 2]) {
                sb.append(1);
                sb.append(arr[arr.length - 1]);
            } else {
                sb.append(count);
                sb.append(arr[arr.length - 1]);
            }
        }
        return DFS(i + 1, n);
    }

    public String countAndSay(int n) {
        sb.delete(0, sb.length());
        sb.append(1);
        return DFS(1, n);
    }

    public String countAndSay2(int n) {
        if (n == 1) {
            return "1";
        }
        char[] str = (countAndSay2(n - 1) + "*").toCharArray();
        int count = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length - 1; i++) {
            if (str[i] == str[i + 1]) {
                count++;
            } else {
                sb.append(count);
                sb.append(str[i]);
                count = 1;
            }
        }
        return sb.toString();
    }

    public String countAndSay3(int n) {
        if (n == 1) {
            return "1";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("1");

        for (int i = 2; i <= n; i++) {
            int count = 1;
            char[] arr = (sb.toString() + "*").toCharArray();
            sb.delete(0, sb.length());
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] == arr[j + 1]) {
                    count++;
                } else {
                    sb.append(count);
                    sb.append(arr[j]);
                    count = 1;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.countAndSay(5));

        //System.out.println(solution.countAndSay2(5));

        System.out.println(solution.countAndSay3(5));
    }
}