
package ee.taltech.iti0202.exam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exam {

    public static final int TEST_NUMBER = 789;
    public static final int INT = 23;

    /**
     * Given a list of score information,
     * return a map with name and the
     * corresponding sum of score for that name.
     * <p>
     * Each line is in format: "score:name1,name2,name3".
     * Score part is always non-negative integer.
     * Names do not contain spaces.
     * There can be one or more names.
     * One line does not have duplicate names.
     * <p>
     * One line indicates the score for the given names.
     * If the same name (case-sensitive) is present in another line,
     * the score is added.
     * <p>
     * The order of the output Map is not important.
     * Map should not contain names with 0 score.
     * <p>
     * Example:
     * "10:Ago,Mati"
     * "20:Ago,Kati"
     * =>
     * {Ago=30, Kati=20, Mati=10}
     * <p>
     * "1:Ago"
     * "2:ago"
     * =>
     * {Ago=1, ago=2}
     * <p>
     * "0:Ago"
     * =>
     * {}
     */
    public static Map<String, Integer> sumScoresFromText(List<String> scores) {
        Map<String, Integer> result = new HashMap<>();
        for (String score : scores
        ) {
            String[] split = score.split(":");
            String[] splitNames = split[1].split(",");
            for (String name : splitNames
            ) {
                if (!split[0].equals("0")) {
                    if (result.containsKey(name)) {
                        int old = result.get(name);
                        result.put(name, result.get(name) + Integer.parseInt(split[0]));
                    } else {
                        result.put(name, Integer.parseInt(split[0]));
                    }
                }
            }
        }
        return result;
    }

    /**
     * Write a method that finds from given array two elements of which sum of cross sum is the largest.
     * Cross sum is sum of all numbers in the element (23 => 2 + 3 = 5).
     * In given method you have to apply cross sum, until it is smaller than 10 (one-digit).
     * For example cross sum of 123 is 1+2+3 = 6.
     * Cross sum on 991 is 9+9+1=19, this number has 2 digits, so we apply cross sum again,
     * 1+9=10, and again, 1+0 = 1. So the final cross sum is 1.
     * <p>
     * Two elements are always in the same order as in the list:
     * in case 1,2,3 you have to consider pairs:
     * 1,2
     * 1,3
     * 2,3
     * <p>
     * 3,2 is NOT allowed
     * <p>
     * combineNumbers([1, 2, 3]) => 23 (2+3 is largest)
     * combineNumbers([1]) => 0 (only one element)
     * combineNumbers([1, 2, 3, 12, 66]) => 312 (3+1+2=6 is largest or
     * for example 1266 => 1+2+6+6=15 => 6. return one that is first in the array)
     * combineNumbers([1, 2, 1, 3]) => 23 (2+3 is largest)
     */

    public static int combineNumbers(int[] numbers) {
        if (numbers.length == 1) {
            return 0;
        } else if (numbers.length == 2) {
            return Integer.parseInt(String.valueOf(numbers[0]) + String.valueOf(numbers[1]));
        }

        return INT;
    }

    /**
     * Get cross sum
     *
     * @param number number to get cross sum from
     * @return cross sum of a number
     */
    public static int getCrossSum(int number) {
        String num = String.valueOf(number);
        if (num.length() == 1) {
            return Integer.parseInt(num);
        } else {
            int sum = 0;
            String[] nums = num.split("");
            for (String i : nums
            ) {
                sum += Integer.parseInt(i);
            }
            return getCrossSum(sum);
        }
    }


    public static void main(String[] args) {
        System.out.println(getCrossSum(TEST_NUMBER));
//        System.out.println(sumScoresFromText(List.of("10:Ago,Mati", "20:Ago,Kati"))); // {Ago=30, Mari=20}
//        System.out.println(sumScoresFromText(List.of("1:Ago", "2:ago")));
//        System.out.println(sumScoresFromText(List.of("0:Ago")));
//        System.out.println(combineNumbers(new int[]{1, 2, 3}));  // 23
    }

}


