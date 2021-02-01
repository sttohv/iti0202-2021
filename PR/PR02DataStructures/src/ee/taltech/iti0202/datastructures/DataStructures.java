package ee.taltech.iti0202.datastructures;

import java.util.*;

public class DataStructures {
    public static Map<String, Integer> students = new HashMap<>();

    /**
     * Given String is a sentence with some words.
     * There are only single whitespace between every word and no punctuation marks.
     * Also there are no capital letters in input string.
     * <p>
     * Return the longest word from the input sentence.
     * <p>
     * If there are more than one word with the same length then return the word which comes alphabetically first.
     * <p>
     * Hints:
     * You can split words into an array using "str.split()"
     * Sorting the list with the longest words can definitely help you to find the word which comes alphabetically first.
     *
     * @param sentence input String to find the longest words
     * @return the longest String from input
     */
    public static String findLongestWord(String sentence) {
        String[] words = sentence.split(" ");
        Arrays.sort(words);
        String longest = "";
        for (String word : words
        ) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        return longest;
    }

    /**
     * Classic count the words exercise.
     * <p>
     * From input count all the words and collect results to map.
     *
     * @param sentence array of strings, can't be null.
     * @return map containing all word to count mappings.
     */
    public static Map<String, Integer> wordCount(String[] sentence) {
        Map<String, Integer> result = new HashMap<>();
        for (String word : sentence
        ) {
            if (!result.containsKey(word)) {
                result.put(word, Collections.frequency(Arrays.asList(sentence), word));
            }

        }
        return result;
    }

    /**
     * Loop over the given list of strings to build a resulting list of string like this:
     * when a string appears the 2nd, 4th, 6th, etc. time in the list, append the string to the result.
     * <p>
     * Return the empty list if no string appears a 2nd time.
     * <p>
     * Use map to count times that the string has appeared.
     *
     * @param words input list to filter
     * @return list of strings matching criteria
     */
    public static List<String> onlyEvenWords(List<String> words) {
        List<String> result = new ArrayList<>();
        for (String word : words
        ) {
            int frequency = Collections.frequency(words, word);
            if (!result.contains(word) && frequency > 1) {
                result.add(word);
            }
        }
        return result;
    }

    /**
     * Method to save student and student's grade(you should use a Map here).
     * Only add student if his/hers grade is in the range of 0-5.
     *
     * @param studentInfo String with a pattern (name:grade)
     */
    public void addStudent(String studentInfo) {
        String name = studentInfo.split(":")[0];
        int grade = Integer.parseInt(studentInfo.split(":")[1]);

        if (students.isEmpty() || !students.containsKey(name) && grade >= 0 && grade < 6) {
            students.put(name, grade);
        }
    }

    /**
     * Method to get student's grade.
     * Return the student's grade by his/hers name.
     * You can assume that the user is already added(previous function with student's info already called).
     *
     * @param name String students name
     * @return int student's grade.
     */
    public int getStudentGrade(String name) {
        if (students.containsKey(name)) {
            return students.get(name);
        } else {
            return -1;
        }
    }

    /**
     * Main.
     *
     * @param args Commend line arguments.
     */
    public static void main(String[] args) {
//        System.out.println(findLongestWord("nimi on salastatud"));  // "salastatud"
//        System.out.println(findLongestWord("aaa bbbbb"));  // "bbbbb"
//        System.out.println(findLongestWord("hello ahllo")); // "ahllo"

//        System.out.println(wordCount(new String[]{})); // empty
//        System.out.println(wordCount(new String[]{"eggs", "SPAM", "eggs", "bacon", "SPAM", "bacon", "SPAM"})); // {bacon=2, eggs=2, SPAM=3}

        System.out.println(onlyEvenWords(Arrays.asList("foo", "bar", "baz", "baz", "bar", "foo"))); // [baz, bar, foo] any order
        System.out.println(onlyEvenWords(Arrays.asList("a", "b", "b", "a"))); // [b, a] any order
        System.out.println(onlyEvenWords(Arrays.asList("eggs", "bacon", "SPAM", "ham", "SPAM", "SPAM","SPAM"))); // [SPAM]

//        DataStructures dataStructures = new DataStructures();
//
//        dataStructures.addStudent("Ago:5");
//        dataStructures.addStudent("Martin:0");
//        dataStructures.addStudent("Margo:3");
//        dataStructures.addStudent("Cheater:6");
//
//        System.out.println(dataStructures.getStudentGrade("Ago")); // 5
//        System.out.println(dataStructures.getStudentGrade("Martin")); // 0
//        System.out.println(dataStructures.getStudentGrade("Margo")); // 3
//        System.out.println(dataStructures.getStudentGrade("Cheater")); // -1
    }
}
