
package ee.taltech.iti0202.kt1;

public class Exam {
    /**
     * Given an array of integers,
     * every element appears twice except for one. Find that single one.
     * If there are not such (and in any other case) return 0.
     * <p>
     * singleNumber([2,2,1]) => 1
     * singleNumber([4,1,2,1,2]) => 4
     */
    public static int singleNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (j != i && nums[j] == nums[i]) {

                }

            }

        }
        return -1;
    }

    /**
     * Given a string, consider the prefix string made of the first N chars of the string.
     * Does that prefix string appear somewhere else in the string.
     * Assume that the string is not empty and that N is in the range 1 .. str.length().
     * <p>
     * prefixExistsAgain("abXXabc", 1) => true
     * prefixExistsAgain("abXXabc", 2) => true
     * prefixExistsAgain("abXXabc", 3) => false
     * prefixExistsAgain("ababa", 3) => true
     */
    public static boolean prefixExistsAgain(String str, int n) {
        String prefix = str.substring(0, n);
        String check = str.substring(1, str.length());
        if (check.contains(prefix)){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(prefixExistsAgain("abXXabc", 1));
        System.out.println(prefixExistsAgain("abXXabc", 2));
        System.out.println(prefixExistsAgain("abXXabc", 3));
        System.out.println(prefixExistsAgain("ababa", 3));
    }
}