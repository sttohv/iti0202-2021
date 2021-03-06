package ee.taltech.iti0202.tk2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Exam {

    /**
     * Return the sum of the numbers in the array,
     * except ignore sections of numbers
     * starting with a 6 and extending to the next 7
     * (every 6 will be followed by at least one 7).
     * Return 0 for no numbers.
     * <p>
     * sum67([1, 2, 2]) => 5
     * sum67([1, 2, 2, 6, 99, 99, 7]) => 5
     * sum67([1, 1, 6, 7, 2]) => 4
     */
    public static int sum67(List<Integer> numbers) {
        int result = 0;
        boolean sixChecker = false;
        for (int num : numbers) {
            if (num == 6) {
                sixChecker = true;
            } else if (!sixChecker) {
                result += num;
            } else if (num == 7) {
                sixChecker = false;
            }
        }
        return result;
    }

    /**
     * Given 3 ints, a b c, return the sum of their rounded values.
     * We'll round an int value up to the next multiple of 10
     * if its rightmost digit is 5 or more, so 15 rounds up to 20.
     * Alternately, round down to the previous multiple of 10
     * if its rightmost digit is less than 5, so 12 rounds down to 10
     * <p>
     * roundSum(16, 17, 18) => 60
     * roundSum(12, 13, 14) => 30
     * roundSum(6, 4, 4) => 10
     */
    public static int roundSum(int a, int b, int c) {
        int sum = round(a) + round(b) + round(c);
        return sum;
    }

    private static int round(int a) {
        if (a % 10 > 4) {
            return a + 10 - a % 10;
        } else {
            return a - a % 10;
        }
    }

    /**
     * Given a string, compute a new string by moving the first char to come after the next two chars,
     * so "abc" yields "bca".
     * Repeat this process for each subsequent group of 3 chars, so "abcdef" yields "bcaefd".
     * Ignore any group of fewer than 3 chars at the end.
     * <p>
     * oneTwo("abc") => "bca"
     * oneTwo("tca") => "cat"
     * oneTwo("tcagdo") => "catdog"
     * oneTwo("abcd") => "bca"
     * oneTwo("a") => ""
     */

    public static String oneTwo(String str) {
        String resultString = "";
        List<String> almostResult = new ArrayList<>();
        for (int i = 0; i < str.length(); i += 3) {
            if (i + 3 > str.length()) {
                break;
            }
            almostResult.add(str.substring(i, i + 3));
        }
        for (String xd : almostResult) {
            xd = xd.substring(1) + xd.charAt(0);
            resultString += xd;
        }
        return resultString;
    }


    /**
     * Modify and return the given map as follows:
     * if exactly one of the keys "a" or "b" exists in the map (but not both),
     * set the other to have that same value in the map.
     * <p>
     * mapAXorB({"a": "aaa", "c": "cake"}) => {"a": "aaa", "b": "aaa", "c": "cake"}
     * mapAXorB({"b": "bbb", "c": "cake"}) => {"a": "bbb", "b": "bbb", "c": "cake"}
     * mapAXorB({"a": "aaa", "b": "bbb", "c": "cake"}) => {"a": "aaa", "b": "bbb", "c": "cake"}
     */
    public static Map<String, String> mapAXorB(Map<String, String> map) {
        Set<String> keys = map.keySet();
        if (keys.contains("a") && !keys.contains("b")) {
            map.put("b", map.get("a"));
        } else if (keys.contains("b") && !keys.contains("a")) {
            map.put("a", map.get("b"));
        }
        return map;
//        if (keys.contains("a") && keys.contains("b") || !(keys.contains("a") && keys.contains("b"))) {
//            return map;
//        } else {
//            map.putIfAbsent("a", map.get("b"));
//            map.putIfAbsent("b", map.get("c"));
//        }
    }
}
