
package ee.taltech.iti0202.idcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IdCode {

    public static final int BEGIN_INDEX = 0;
    public static final int END_INDEX = 1;
    public static final int END_INDEX1 = 3;
    public static final int END_INDEX2 = 5;
    public static final int END_INDEX3 = 7;
    public static final int END_INDEX4 = 10;
    public static final int INT = 11;
    public static final int INT1 = 2;
    public static final int INT2 = 2012;
    public static final int INT3 = 21;
    public static final int INT4 = 270;
    public static final int INT5 = 371;
    public static final int INT6 = 221;
    public static final int INT7 = 470;
    public static final int INT8 = 491;
    public static final int INT9 = 271;
    public static final int INT10 = 421;
    public static final int INT11 = 471;
    public static final int INT12 = 521;
    public static final int INT13 = 571;
    public static final int INT14 = 601;
    public static final int INT15 = 651;
    public static final int INT16 = 711;
    public static final int INT17 = 13;
    public static final int INT18 = 4;
    public static final int INT19 = 6;
    public static final int INT20 = 9;
    public static final int INT21 = 32;
    public static final int INT22 = 31;
    public static final int INT23 = 30;
    public static final int INT24 = 29;
    public static final int INT25 = 8;
    public static final int INT26 = 400;
    public static final int INT27 = 100;
    public static final int INT28 = 12;
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

    /**
     * constructor
     *
     * @param idCodeValue entered idCode
     */
    public IdCode(String idCodeValue) {

        this.idCodeValue = idCodeValue;
        try {
            this.genderNum = idCodeValue.substring(BEGIN_INDEX, END_INDEX);
            this.yearNum = idCodeValue.substring(END_INDEX, END_INDEX1);
            this.monthNum = idCodeValue.substring(END_INDEX1, END_INDEX2);
            this.dayNum = idCodeValue.substring(END_INDEX2, END_INDEX3);
            this.placeNum = idCodeValue.substring(END_INDEX3, END_INDEX4);
            this.controlNum = idCodeValue.substring(END_INDEX4);
        } catch (Exception e) {
            throw new IllegalArgumentException("Wrong Id Code");
        }
        isCorrect();
    }


    /**
     * Check if the id code is valid or not.
     *
     * @return boolean describing whether or not the id code was correct.
     */
    public boolean isCorrect() {
        if (isNumeric()) {
            if (idCodeValue.length() == INT && isGenderNumberCorrect() && isYearNumberCorrect()
                    && isMonthNumberCorrect() && isDayNumberCorrect() && isControlNumberCorrect()) {
                return true;
            }
        }
        throw new IllegalArgumentException("wrong id code");

    }

    /**
     * Get all information about id code.
     *
     * @return String containing information.
     */
    public String getInformation() {
        return "This is a " + getGender() + " born on " + dayNum + "." + monthNum + "." + getFullYear() + " in "
                + getBirthPlace();
    }

    /**
     * Get gender enum.
     *
     * @return enum describing person's gender
     */
    public Gender getGender() {
        if (Integer.parseInt(genderNum) % INT1 == BEGIN_INDEX) {
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
        Integer placeNumber = Integer.parseInt(placeNum);
        if (getFullYear() > INT2) {
            return "unknown";
        } else if (placeNumber < INT) {
            return "Kuressaare";
        } else if (placeNumber < INT3 || placeNumber > INT4 && placeNumber < INT5) {
            return "Tartu";
        } else if (placeNumber < INT6 || placeNumber > INT7 && placeNumber < INT8) {
            return "Tallinn";
        } else if (Integer.parseInt(placeNum) < INT9) {
            return "Kohtla-Järve";
        } else if (Integer.parseInt(placeNum) < INT10) {
            return "Narva";
        } else if (Integer.parseInt(placeNum) < INT11) {
            return "Pärnu";
        } else if (Integer.parseInt(placeNum) < INT12) {
            return "Paide";
        } else if (Integer.parseInt(placeNum) < INT13) {
            return "Rakvere";
        } else if (Integer.parseInt(placeNum) < INT14) {
            return "Valga";
        } else if (Integer.parseInt(placeNum) < INT15) {
            return "Viljandi";
        } else if (Integer.parseInt(placeNum) < INT16) {
            return "Võru";
        } else {
            return "unknown";
        }

    }


    /**
     * Get the year that the person was born in.
     *
     * @return int with person's birth year.
     */
    public int getFullYear() {
        if (Integer.parseInt(genderNum) < END_INDEX1) {
            return Integer.parseInt("18" + yearNum);
        } else if (Integer.parseInt(genderNum) < END_INDEX2) {
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
        return Integer.parseInt(genderNum) > BEGIN_INDEX && Integer.parseInt(genderNum) < END_INDEX3;
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
        return Integer.parseInt(monthNum) > BEGIN_INDEX && Integer.parseInt(monthNum) < INT17;
    }

    /**
     * Check if the day number is correct.
     *
     * @return boolean describing whether the day number is correct.
     */
    private boolean isDayNumberCorrect() {
        List<Integer> month31 = new ArrayList<>(Arrays.asList(END_INDEX, END_INDEX1, END_INDEX2, END_INDEX3, INT25,
                END_INDEX4, INT28));
        List<Integer> month30 = new ArrayList<>(Arrays.asList(INT18, INT19, INT20, INT));
        if (month31.contains(Integer.valueOf(monthNum)) && Integer.parseInt(dayNum) < INT21 && !dayNum.equals("00")) {
            return true;
        } else if (month30.contains(Integer.valueOf(monthNum)) && Integer.parseInt(dayNum) < INT22
                && !dayNum.equals("00")) {
            return true;
        } else if (isLeapYear(getFullYear()) && Integer.parseInt(dayNum) < INT23 && !dayNum.equals("00")) {
            return true;
        } else return !isLeapYear(getFullYear()) && Integer.parseInt(dayNum) < INT24 && !dayNum.equals("00");
    }

    /**
     * Check if the control number is correct.
     *
     * @return boolean describing whether the control number is correct.
     */
    private boolean isControlNumberCorrect() {
        List<Integer> scale = new ArrayList<>(Arrays.asList(END_INDEX, INT1, END_INDEX1, INT18, END_INDEX2, INT19,
                END_INDEX3, INT25, INT20, END_INDEX));
        List<Integer> scale1 = new ArrayList<>(Arrays.asList(END_INDEX1, INT18, END_INDEX2, INT19, END_INDEX3, INT25,
                INT20, END_INDEX, INT1, END_INDEX1));
        int sum = BEGIN_INDEX;
        char[] idCode = idCodeValue.toCharArray();
        for (int i = BEGIN_INDEX; i < END_INDEX4; i++) {
            sum += Character.getNumericValue(idCode[i]) * scale.get(i);
        }
        if (sum % INT == END_INDEX4) {
            sum = BEGIN_INDEX;
            for (int i = BEGIN_INDEX; i < END_INDEX4; i++) {
                sum += Character.getNumericValue(idCode[i]) * scale1.get(i);
            }
            if (sum % INT == END_INDEX4) {
                return Integer.parseInt(controlNum) == BEGIN_INDEX;
            } else {
                return Integer.parseInt(controlNum) == sum % INT;
            }
        } else {
            return Integer.parseInt(controlNum) == sum % INT;
        }
    }

    /**
     * Check if the given year is a leap year.
     *
     * @param fullYear do something
     * @return boolean describing whether the given year is a leap year.
     */
    private static boolean isLeapYear(int fullYear) {
        if (fullYear % INT18 != BEGIN_INDEX) {
            return false;
        } else if (fullYear % INT26 == BEGIN_INDEX) {
            return true;
        } else return fullYear % INT27 != BEGIN_INDEX;
    }

    /**
     * Check if entered IdCode is numeric or not
     *
     * @return boolean describing whether the idCode consists of all numbers.
     */
    public boolean isNumeric() {
        List<String> nums = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0");
        int notNums = BEGIN_INDEX;
        for (char num : idCodeValue.toCharArray()
        ) {
            if (!nums.contains(Character.toString(num))) {
                notNums++;
            }

        }
        return notNums == BEGIN_INDEX;
    }

    /**
     * Run tests.
     *
     * @param args info.
     */
    public static void main(String[] args) {
        IdCode validMaleIdCode = new IdCode("61405310004");
//        System.out.println(validMaleIdCode.isNumeric());
        System.out.println(validMaleIdCode.isCorrect());
//        System.out.println(validMaleIdCode.getInformation());
//        System.out.println(validMaleIdCode.getGender());
        System.out.println(validMaleIdCode.getBirthPlace());
//        System.out.println(validMaleIdCode.getFullYear());
//        System.out.println(validMaleIdCode.isGenderNumberCorrect());
//        System.out.println(validMaleIdCode.isYearNumberCorrect());
//        System.out.println(validMaleIdCode.isMonthNumberCorrect());
//        System.out.println("Is day number correct " + validMaleIdCode.isDayNumberCorrect());
//        System.out.println(validMaleIdCode.isControlNumberCorrect());
//        System.out.println(validMaleIdCode.isLeapYear(validMaleIdCode.getFullYear()));
    }

}

