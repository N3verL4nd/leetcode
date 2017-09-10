package solutions._345;

/**
 * 345. Reverse Vowels of a String
 */
public class Solution {
    private boolean isVowel(char ch) {
        char v = Character.toLowerCase(ch);
        return v == 'a' || v == 'e' || v == 'i' || v == 'o' || v == 'u';
    }
    public String reverseVowels(String s) {
        int i = 0, j = s.length() - 1;
        char[] arr = s.toCharArray();
        while (i < j) {
            while (i < j && !isVowel(arr[i])) {
                i++;
            }
            while (i < j && !isVowel(arr[j])) {
                j--;
            }
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        return new String(arr);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.reverseVowels("wwww"));
    }
}