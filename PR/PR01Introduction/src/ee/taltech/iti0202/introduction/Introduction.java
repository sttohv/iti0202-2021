package ee.taltech.iti0202.introduction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

public class Introduction {


    /**
     * Method gets two numbers as parameters.
     * Method must answer if the given pair gives bad, normal or good outcome.
     * Outcome is "bad" if any of values is less than 5.
     * Outcome is "good" if one value equals doubled second value.
     * Outcome is "ok" if both values equal at least 5.
     * The priority is as follows: "bad", "good", "ok" (if several cases apply, take the higher / earlier).
     *
     * @param valueOne int
     * @param valueTwo int
     * @return String based on the values of valueOne and valueTwo
     */
    public String howIsOutcome(int valueOne, int valueTwo) {
        if (valueOne < 5 || valueTwo < 5) {
            return "bad";
        } else if (valueOne == 2 * valueTwo || valueTwo == 2 * valueOne) {
            return "good";
        } else {
            return "ok";
        }
    }

    /**
     * Method gets a list of numbers.
     * Return a list containing only even numbers of the given list.
     * If the given list does not contain any even numbers, return an empty list.
     *
     * @param numbers given list that contains numbers.
     * @return list of even numbers.
     */
    public List<Integer> findEvenNumbersList(List<Integer> numbers) {

        return numbers.stream().filter(num -> num % 2 == 0).collect(Collectors.toList());
    }


    /**
     * Method gets an array of numbers.
     * Return an array containing only even numbers of the given array.
     * If the given array does not contain any even numbers, return an empty array.
     * <p>
     * You must not use the previous function in this function!
     *
     * @param numbers given array that contains numbers.
     * @return array of even numbers.
     */
    public int[] findEvenNumbersArray(int[] numbers) {
        return Arrays.stream(numbers).filter(num -> num % 2 == 0).toArray();
    }

    /**
     * Run tests.
     *
     * @param args info.
     */
    public static void main(String[] args) {
        Introduction introduction = new Introduction();
        System.out.println(introduction.howIsOutcome(3, 6)); // "bad"
        System.out.println(introduction.howIsOutcome(1, 10)); //"bad"
        System.out.println(introduction.howIsOutcome(6, 12)); //"good"
        System.out.println(introduction.howIsOutcome(8, 9)); //"ok"


        List<Integer> nums = new ArrayList<>(Arrays.asList(4, 7, 5, 2, 1, 2, -2, 0));
        List<Integer> nums1 = new ArrayList<>(Arrays.asList());
        List<Integer> nums2 = new ArrayList<>(Arrays.asList(7, 5, 1));
        System.out.println(introduction.findEvenNumbersList(nums)); // [4, 2, 2, -2, 0]
        System.out.println(introduction.findEvenNumbersList(nums1));
        System.out.println(introduction.findEvenNumbersList(nums2));

        int[] array = {9, 0, 24, -6, 3};
        int[] array1 = {};
        int[] array2 = {9, 1, 245, -67, 3};
        System.out.println(Arrays.toString(introduction.findEvenNumbersArray(array))); // [0, 24, -6]
        System.out.println(Arrays.toString(introduction.findEvenNumbersArray(array1)));
        System.out.println(Arrays.toString(introduction.findEvenNumbersArray(array2)));
    }
}
