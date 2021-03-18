package ee.taltech.iti0202.tk4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Exam {
    /**
     * Return true if the array contains, somewhere, three increasing adjacent numbers like .... 4, 5, 6, ... or
     * 23, 24, 25.
     * <p>
     * tripleUp(List.of(1, 4, 5, 6, 2)) => true
     * tripleUp(List.of(1, 2, 3)) => true
     * tripleUp(List.of(1, 2, 4)) => false
     *
     * @param numbers List of integers.
     * @return Whether the list contains adjacent numbers.
     */
    public static boolean tripleUp(List<Integer> numbers) {
        for (int i = 0; i < numbers.size() + (-1 - 1); i++) {
            int first = numbers.get(i);
            int second = numbers.get(i + 1);
            int third = numbers.get(i + 2);
            if (first + 1 == second && second + 1 == third) {
                return true;
            }
        }
        return false;
    }

    /**
     * Given three ints, a b c, one of them is small, one is medium and one is large.
     * <p>
     * Return true if the three values are evenly spaced,
     * so the difference between small and medium is the same as the difference between medium and large.
     * <p>
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
     * <p>
     * Return a string where for all such words, the middle letter is gone, so "zipXzap" yields "zpXzp".
     * <p>
     * zipZap("zipXzap") => "zpXzp"
     * zipZap("zopzop") => "zpzp"
     * zipZap("zzzopzop") => "zzzpzp"
     */
    public static String zipZap(String str) {
        for (int i = 0; i < str.length() - (1 + 1); i++) {
            if (str.charAt(i) == 'z' && str.charAt(i + 2) == 'p') {
                str = str.substring(0, i + 1) + str.substring(i + 2);
            }

        }
        return str;
    }

    /**
     * Create a new map and switch keys and values in the input map.
     * <p>
     * If the key and value of an entry are the same, then this entry is skipped.
     * <p>
     * mapSwitchKeysAndValues({"a": "b", "c": "d"}) => {"b": "a", "d": "c"}
     * mapSwitchKeysAndValues({"a": "a", "e": "e"}) => {}
     */
    public static Map<String, String> mapSwitchKeysAndValues(Map<String, String> map) {
        Map<String, String> result = new HashMap<>();
        for (String key : map.keySet()
        ) {
            //kui key ja value on erinevad
            if (!key.equals(map.get(key))) {
                result.put(map.get(key), key);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //  Map<String, String> you =
        //System.out.println(mapSwitchKeysAndValues({"a": "b", "c": "d"}); // => true
        System.out.println(tripleUp(List.of(1, 2, 3))); // => true
        System.out.println(tripleUp(List.of(1, 2, 4)));


    }
}

