package solutions._65;

/**
 * 65. Valid Number
 * https://www.cnblogs.com/grandyang/p/4084408.html
 * https://blog.csdn.net/fightforyourdream/article/details/12900751
 */
class Solution {
    public boolean isNumber(String s) {
        if (s.trim().isEmpty()) {
            return false;
        }
        /**
         * [xyz] 匹配包含的任一字符
         * ? 零次或一次匹配前面的字符或子表达式
         * + 一次或多次匹配前面的字符或子表达式
         * * 零次或多次匹配前面的字符或子表达式
         */
        String regex = "[-+]?(\\d+\\.?|\\.\\d+)\\d*([eE][-+]?\\d+)?";
        if (s.trim().matches(regex)) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isNumber(".123"));
        System.out.println(solution.isNumber("0"));
        System.out.println(solution.isNumber(" 0.1"));
        System.out.println(solution.isNumber("abc"));
        System.out.println(solution.isNumber("1 a"));
        System.out.println(solution.isNumber("2e10"));
        System.out.println(solution.isNumber("e"));
    }
}

/*
"0"     => true
" 0.1 " => true
"abc"   => false
"1 a"   => false
"2e10"  => true
 */