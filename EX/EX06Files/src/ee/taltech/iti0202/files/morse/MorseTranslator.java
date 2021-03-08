package ee.taltech.iti0202.files.morse;

import java.util.List;
import java.util.Map;

public class MorseTranslator {

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
        return null;
    }

    /**
     * meetod, mis tõlgib etteantud rea Morse koodi.
     * Iga Morse tähe vahele tuleb panna tühik, ning iga sõna vahele tab.
     *
     * @param lines text lines
     * @return list of translated lines that are in Morse
     */
    public List<String> translateLinesToMorse(List<String> lines) {
        return null;
    }

    /**
     * meetod, mis tõlgib morse koodis rea taas tavatekstiks
     * (Iga Morse tähe vahel on tühik ning iga sõna vahel on tab).
     *
     * @param lines text lines
     * @return list of latin letters
     */
    public List<String> translateLinesFromMorse(List<String> lines) {
        return null;
    }

    /**
     * meetod, mis tõlgib etteantud read Morse koodi ning tagastab tõlgitud read.
     * Vihje: kasuta eelnevalt defineeritud private meetodeid, mis on nähtavad ainult klassi siseselt
     *
     * @param line text line
     * @return translated line
     */
    private String translateLineToMorse(String line) {
        return null;
    }

    /**
     * meetod, mis tõlgib etteantud read Morse koodist tavatekstiks
     * ning tagastab tõlgitud read.
     *
     * @param line text line
     * @return translated line
     */
    private String translateLineFromMorse(String line) {
        return null;
    }
}