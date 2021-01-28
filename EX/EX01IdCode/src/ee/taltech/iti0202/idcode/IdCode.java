
package ee.taltech.iti0202.idcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IdCode {

    private final String idCodeValue;
    private final String genderNum;
    private final String yearNum;
    private final String monthNum;
    private final String dayNum;
    private final String placeNum;
    private final String controlNum;

    enum Gender {
        MALE, FEMALE
    }

    /**
     * Method returns the id code.
     *
     * @return id code.
     */
    public String getIdCodeValue() {
        return idCodeValue;
    }

    public IdCode(String idCodeValue) {

        this.idCodeValue = idCodeValue;
        this.genderNum = idCodeValue.substring(0, 1);
        this.yearNum = idCodeValue.substring(1, 3);
        this.monthNum = idCodeValue.substring(3, 5);
        this.dayNum = idCodeValue.substring(5, 7);
        this.placeNum = idCodeValue.substring(7, 10);
        this.controlNum = idCodeValue.substring(10);
        isCorrect();
    }


    /**
     * Check if the id code is valid or not.
     *
     * @return boolean describing whether or not the id code was correct.
     */
    public boolean isCorrect() {
        if (isNumeric()) {
            if (idCodeValue.length() == 11 && isGenderNumberCorrect() && isYearNumberCorrect() && isMonthNumberCorrect() && isDayNumberCorrect() && isControlNumberCorrect()) {
                return true;
            }
        }
        throw new IllegalArgumentException("wrong ic code");

    }

    /**
     * Get all information about id code.
     *
     * @return String containing information.
     */
    public String getInformation() {
        return null;
    }

    /**
     * Get gender enum.
     *
     * @return enum describing person's gender
     */
    public Gender getGender() {
        if (Integer.valueOf(genderNum) % 2 == 0) {
            return Gender.FEMALE;
        }
        return Gender.MALE;
    }

    /**
     * Get person's birth location.
     *
     * @return String with the person's birth place.
     */
    public String getBirthPlace() {
        return null;
    }

    /**
     * Get the year that the person was born in.
     *
     * @return int with person's birth year.
     */
    public int getFullYear() {
        if (Integer.valueOf(genderNum) < 3) {
            return Integer.parseInt("18" + yearNum);
        } else if (Integer.valueOf(genderNum) < 5) {
            return Integer.parseInt("19" + yearNum);
        } else {
            return Integer.parseInt("20" + yearNum);
        }
    }

    /**
     * Check if gender number is correct.
     *
     * @return boolean describing whether the gender number is correct.
     */
    private boolean isGenderNumberCorrect() {
        if (Integer.valueOf(genderNum) > 0 && Integer.valueOf(genderNum) < 7) {
            return true;
        }
        return false;
    }

    /**
     * Check if the year number is correct.
     *
     * @return boolean describing whether the year number is correct.
     */
    private boolean isYearNumberCorrect() {
        return true; //iga kaks numbrit mis sa sisestad võivad olla ju
    }

    /**
     * Check if the month number is correct.
     *
     * @return boolean describing whether the month number is correct.
     */
    private boolean isMonthNumberCorrect() {
        if (Integer.valueOf(monthNum) > 0 && Integer.valueOf(monthNum) < 13) {
            return true;
        }
        return false;
    }

    /**
     * Check if the day number is correct.
     *
     * @return boolean describing whether the day number is correct.
     */
    private boolean isDayNumberCorrect() {
        List<Integer> month31 = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 8, 10, 12));
        List<Integer> month30 = new ArrayList<>(Arrays.asList(4, 6, 9, 11));
        if (month31.contains(Integer.valueOf(monthNum)) && Integer.valueOf(dayNum) < 31 && dayNum != "00") {
            return true;
        } else if (month30.contains(Integer.valueOf(monthNum)) && Integer.valueOf(dayNum) < 31 && dayNum != "00") {
            return true;
        } else if (isLeapYear(getFullYear()) && Integer.valueOf(dayNum) < 29 && dayNum != "00") {
            return true;
        } else if (isLeapYear(getFullYear()) == false && Integer.valueOf(dayNum) < 28 && dayNum != "00") {
            return true;
        }
        return false;
    }

    /**
     * Check if the control number is correct.
     *
     * @return boolean describing whether the control number is correct.
     */
    private boolean isControlNumberCorrect() {
        List<Integer> scale = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 1));
        List<Integer> scale1 = new ArrayList<>(Arrays.asList(3, 4, 5, 6, 7, 8, 9, 1, 2, 3));

        return true;
    }

    /**
     * Check if the given year is a leap year.
     *
     * @param fullYear
     * @return boolean describing whether the given year is a leap year.
     */
    private static boolean isLeapYear(int fullYear) {
        if (fullYear % 4 != 0) {
            return false;
        } else if (fullYear % 400 == 0) {
            return true;
        } else return fullYear % 100 != 0;
    }

    /**
     * Check if entered IdCode is numeric or not
     */
    public boolean isNumeric() {
        List<String> nums = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0");
        int notNums = 0;
        for (char num : idCodeValue.toCharArray()
        ) {
            if (!nums.contains(Character.toString(num))) {
                notNums++;
            }

        }
        return notNums == 0;
    }

    /**
     * Run tests.
     *
     * @param args info.
     */
    public static void main(String[] args) {
        IdCode validMaleIdCode = new IdCode("60008050299");
//        System.out.println(validMaleIdCode.isNumeric());
        System.out.println(validMaleIdCode.isCorrect());
//        System.out.println(validMaleIdCode.getInformation());
//        System.out.println(validMaleIdCode.getGender());
//        System.out.println(validMaleIdCode.getBirthPlace());
//        System.out.println(validMaleIdCode.getFullYear());
//        System.out.println(validMaleIdCode.isGenderNumberCorrect());
//        System.out.println(validMaleIdCode.isYearNumberCorrect());
//        System.out.println(validMaleIdCode.isMonthNumberCorrect());
//        System.out.println("Is day number correct " + validMaleIdCode.isDayNumberCorrect());
//        System.out.println(validMaleIdCode.isControlNumberCorrect());
//        System.out.println(validMaleIdCode.isLeapYear(validMaleIdCode.getFullYear()));
    }

}

