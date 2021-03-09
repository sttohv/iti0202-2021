package ee.taltech.iti0202.files.morse;

import ee.taltech.iti0202.files.input.InputFilesScanner;

import java.util.*;

public class MorseTranslator {
    private Map<String, String> morseLetter;

    /**
     * meetod, mis lisab failist sisse loetud Morse koodide read Map'i nii,
     * et võtmeks on täht ja väärtuseks on Morse kood.
     * Kuna Morse koodis pole vahet suur- ning väiketähtede vahel,
     * tuleks kõik tähed lisada väiketähtedena.
     * Klass peab saama sisse loetud Morse koode tõlkimisel kasutada.
     *
     * @param lines text lines
     * @return map containing letters and morse code
     */
    public Map<String, String> addMorseCodes(List<String> lines) {
        Map<String, String> result = new HashMap<>();
        for (String line : lines
        ) {
            String[] lineS = line.toLowerCase(Locale.ROOT).split(" ");
            result.putIfAbsent(lineS[0], lineS[1]);
        }
        morseLetter = result;
        return result;
    }

    /**
     * meetod, mis tõlgib etteantud read Morse koodi ning tagastab tõlgitud read.
     * (Vihje: kasuta eelnevalt defineeritud private meetodeid, mis on nähtavad ainult klassi siseselt)
     *
     * @param lines text lines
     * @return list of translated lines that are in Morse
     */
    public List<String> translateLinesToMorse(List<String> lines) {
        List<String> result = new ArrayList<>();
        for (String line : lines
        ) {
            result.add(translateLineToMorse(line));
        }
        return result;
    }

    /**
     * meetod, mis tõlgib etteantud read Morse koodist tavatekstiks
     * ning tagastab tõlgitud read.
     *
     * @param lines text lines
     * @return list of latin letters
     */
    public List<String> translateLinesFromMorse(List<String> lines) {
        List<String> result = new ArrayList<>();
        for (String line : lines
        ){
            result.add(translateLineFromMorse(line));
        }
        return result;
    }

    /**
     * meetod, mis tõlgib etteantud rea Morse koodi.
     * Iga Morse tähe vahele tuleb panna tühik, ning iga sõna vahele tab.
     *
     * @param line text line
     * @return translated line
     */
    private String translateLineToMorse(String line) {
        StringBuilder result = new StringBuilder();
        for (String letter : line.toLowerCase().split("")
        ) {
            if (!letter.equals(" ")) {
                result.append(morseLetter.get(letter)).append(" ");
            } else {
                result.append("\t");
            }
        }
        return result.toString();
    }

    /**
     * meetod, mis tõlgib morse koodis rea taas tavatekstiks
     * (Iga Morse tähe vahel on tühik ning iga sõna vahel on tab).
     *
     * @param line text line
     * @return translated line
     */
    private String translateLineFromMorse(String line) {
        StringBuilder result = new StringBuilder();
        String[]words = line.split("\t");
        for (String word:words
             ) {
            for (String letter:word.split(" ")
                 ) {
                result.append(morseLetter
                        .entrySet()
                        .stream()
                        .filter(entry -> letter.equals(entry.getValue()))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .get());
            }
            result.append(" ");
        }return String.valueOf(result);
    }
}