package exam;

import java.util.List;

public class Exam {

    /**
     * 01
     *
     * For each multiple of 10 in the given array,
     * change all the values following it to be that multiple of 10,
     * until encountering another multiple of 10.
     * So {2, 10, 3, 4, 20, 5} yields {2, 10, 10, 10, 20, 20}.
     *
     * tenRun([2, 10, 3, 4, 20, 5]) => [2, 10, 10, 10, 20, 20]
     * tenRun([10, 1, 20, 2]) => [10, 10, 20, 20]
     * tenRun([10, 1, 9, 20]) => [10, 10, 10, 20]
     */
    public static List<Integer> tenRun(List<Integer> nums) {
        return null;
    }

    /**
     * 02
     *
     * Write a method that analyzes input String and returns all pairs of same letter that is present as lower-case and upper-case in input String.
     * Returned letter pairs have to be in alphabetic order. If there are multiple same letter pairs, then return only one. If there are no suitable pairs, return "".
     * Take latin alphabet 'a' - 'z' as base.
     * mixedPairs("abcABD") => "AaBb" (a and b are represented by both lowe and upper cased letter)
     * mixedPairs("aaaAAAaaaa") => "Aa"
     * mixedPairs("tere") => ""
     * mixedPairs("bBaacA") => "AaBb" (result is in alphabetic order, although in input String, b is earlier than a).
     * @param input
     * @return
     */
    public static String mixedPairs(String input) {
        return "wrong";
    }


    /**
     * 03
     *
     * Write a recursive method (no loops, no global variables, calls itself)
     * which separates different letters by single space.
     *
     * recSeparate("abc") => "a b c"
     * recSeparate("aabbc") => aa bb c"
     * recSeparate("aaaabbbccd") => "aaaa bbb cc d"
     * recSeparate("") => ""
     * recSeparate("aaa") => "aaa"
     *
     * @param text
     * @return
     */
    public static String recSeparate(String text) {
        if(text.length()<2){
            return text;
        }
        else if(text.charAt(0)!=text.charAt(1)){
            return text.charAt(0) + " " + recSeparate(text.substring(1));
        }
        else{
            return text.charAt(0)+recSeparate(text.substring(1));
        }
    }

    public static void main(String[] args) {
        System.out.println(recSeparate("abc"));
        System.out.println(recSeparate("aabbc"));
        System.out.println(recSeparate("aaaabbbccd"));
        System.out.println(recSeparate(""));
        System.out.println(recSeparate("aaa"));
    }

}
