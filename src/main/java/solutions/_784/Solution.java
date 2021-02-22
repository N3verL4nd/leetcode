package solutions._784;

import java.util.ArrayList;
import java.util.List;

/**
 * 784. Letter Case Permutation
 */
class Solution {
    private List<String> result = new ArrayList<>(5000);

    private void DFS(char[] str, int pos) {
        for (int i = pos + 1; i < str.length; i++) {
            if (Character.isDigit(str[i])) {
                continue;
            }
            if (Character.isLowerCase(str[i])) {
                str[i] = Character.toUpperCase(str[i]);
                result.add(new String(str));
                DFS(str, i);
                str[i] = Character.toLowerCase(str[i]);
            } else {
                str[i] = Character.toLowerCase(str[i]);
                result.add(new String(str));
                DFS(str, i);
                str[i] = Character.toUpperCase(str[i]);
            }
        }
    }

    public List<String> letterCasePermutation(String S) {
        if (S == null || S.length() == 0) {
            result.add("");
            return result;
        }
        result.add(S);
        DFS(S.toCharArray(), -1);
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.letterCasePermutation("a1b2"));
//        System.out.println(solution.letterCasePermutation("3z4"));
//        System.out.println(solution.letterCasePermutation("12345"));
    }
}