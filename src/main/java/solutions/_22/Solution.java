package solutions._22;

import java.util.ArrayList;
import java.util.List;

/**
 * 19. Remove Nth Node From End of List
 */
class Solution {
    private StringBuilder sb = new StringBuilder();
    private List<String> list = new ArrayList<>();

    private void DFS(int left, int right, int n) {
        if (left == n && right == n) {
            list.add(sb.toString());
            return;
        }

        if (right > left) {
            return;
        }

        if (left < n) {
            sb.append('(');
            DFS(left + 1, right, n);
            sb.delete(sb.length() - 1, sb.length());
        }
        if (right < n) {
            sb.append(')');
            DFS(left, right + 1, n);
            sb.delete(sb.length() - 1, sb.length());
        }
    }

    public List<String> generateParenthesis(int n) {
        DFS(0, 0, n);
        return list;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> list = solution.generateParenthesis(3);
        System.out.println(list);
    }

}