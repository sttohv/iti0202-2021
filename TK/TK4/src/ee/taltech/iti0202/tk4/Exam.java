package ee.taltech.iti0202.tk4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exam {
    /**
     * Return true if the array contains, somewhere, three increasing adjacent numbers like .... 4, 5, 6, ... or 23, 24, 25.
     *
     * tripleUp(List.of(1, 4, 5, 6, 2)) => true
     * tripleUp(List.of(1, 2, 3)) => true
     * tripleUp(List.of(1, 2, 4)) => false
     *
     * @param numbers List of integers.
     * @return Whether the list contains adjacent numbers.
     */
    public static boolean tripleUp(List<Integer> numbers) {
        for (int i = 0; i < numbers.size() + (-1 - 2); i++) {
            if (numbers.get(i) + 1 == numbers.get(i + 1) && numbers.get(i + 1) == numbers.get(i + 2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Given three ints, a b c, one of them is small, one is medium and one is large.
     *
     * Return true if the three values are evenly spaced,
     * so the difference between small and medium is the same as the difference between medium and large.
     *
     * evenlySpaced(2, 4, 6) => true
     * evenlySpaced(4, 6, 2) => true
     * evenlySpaced(4, 6, 3) => false
     */
    public static boolean evenlySpaced(int a, int b, int c) {
        List<Integer> numbers = List.of(a, b, c).stream().sorted().collect(Collectors.toList());
        // numbrid suurus järjekorras
        Integer biggest = numbers.get(2);
        Integer middle = numbers.get(1);
        Integer smallest = numbers.get(0);
        return biggest - middle == middle - smallest;
    }

    /**
     * Look for patterns like "zip" and "zap" in the string --
     * length-3, starting with 'z' and ending with 'p'.
     *
     * Return a string where for all such words, the middle letter is gone, so "zipXzap" yields "zpXzp".
     *
     * zipZap("zipXzap") => "zpXzp"
     * zipZap("zopzop") => "zpzp"
     * zipZap("zzzopzop") => "zzzpzp"
     */
    public static String zipZap(String str) {
        for (int i = 0; i < str.length() - (1 + 2); i++) {
            if (str.charAt(i) == 'z' && str.charAt(i + 2) == 'p') {
                str = str.substring(0, i + 1) + str.substring(i + 2);
            }

        }
        return str;
    }

    /**
     * Create a new map and switch keys and values in the input map.
     *
     * If the key and value of an entry are the same, then this entry is skipped.
     *
     * mapSwitchKeysAndValues({"a": "b", "c": "d"}) => {"b": "a", "d": "c"}
     * mapSwitchKeysAndValues({"a": "a", "e": "e"}) => {}
     */
    public static Map<String, String> mapSwitchKeysAndValues(Map<String, String> map) {
        return null;
    }

    public static void main(String[] args) {
        System.out.println(zipZap("zipazupa"));

        System.out.println();

    }
}

