package ee.taltech.iti0202.files.morse;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MorseTranslator {
    private Map<String, String> morseLetter = new HashMap<>();

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
        for (String line : lines
        ) {
            String[] lineS = line.toLowerCase().split(" ");
            morseLetter.putIfAbsent(lineS[0], lineS[1]);
        }
        return morseLetter;
    }

    /**
     * meetod, mis tõlgib etteantud read Morse koodi ning tagastab tõlgitud read.
     * (Vihje: kasuta eelnevalt defineeritud private meetodeid, mis on nähtavad ainult klassi siseselt)
     *
     * @param lines text lines
     * @return list of translated lines that are in Morse
     */
    public List<String> translateLinesToMorse(List<String> lines) { //["Su ema on traktor", "",  "traktor on automaat"]
        List<String> result = new ArrayList<>();
        for (String line : lines
        ) {
            result.add(translateLineToMorse(line.toLowerCase()));
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
        ) {
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
    private String translateLineToMorse(String line) { //"su ema on xd"
        String result = "";  //tühi vastuse string

        for (String word : line.toLowerCase().split(" ")  //[su,ema,on,xd]
        ) {
            result += translateWordToMorse(word);
        }

        if (!result.isEmpty()) {
            return result.substring(0, result.length() - 1);
        }
        return "";

    }

    /**
     * tõlgib sõna morse koodi
     *
     * @param word sõna mida tõlgitakse
     * @return tõlgitud sõna
     */
    private String translateWordToMorse(String word) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= (word.length() - 1); i++) { //su length on 2
            if (i != (word.length() - 1)) { //kui pole sõna lõpp, siis tühik
                result.append(morseLetter.get(String.valueOf(word.charAt(i)))).append(" ");
            } else { //kui on sõna lõpp, siis lisa tab
                result.append(morseLetter.get(String.valueOf(word.charAt(i))));
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
        String result = "";
        String[] words = line.split("\t");  //sõnad on eraldatud tabiga ja splitides eraldab sõnad
        for (String word : words
        ) {
            result += translateWordFromMorse(word);

        }
        if (!result.equals("")) {
            return result.substring(0, result.length() - 1);
            //kui rida pole tühi, siis eemaldab viimase sõna lõpust tühiku
        }
        return result;
    }

    /**
     * tõlgib morse koodist sõna tavatähtedesse
     *
     * @param word sõna, mis on morse koodis ja mida tõlkima hakatakse
     * @return tõlgitud sõna
     */
    private String translateWordFromMorse(String word) {
        String result = "";
        String[] wordLetters = word.split(" "); // tühikute järgi sest iga tähe vahel on morses tühik
        for (int i = 0; i < wordLetters.length; i++) {
            for (Map.Entry<String, String> morse : morseLetter.entrySet()) {
                if (wordLetters[i].equals(morse.getValue())) {
                    result += morse.getKey();
                }
            }
        }
        return result + " "; //lisab iga sõna järgi tühiku
    }
}
