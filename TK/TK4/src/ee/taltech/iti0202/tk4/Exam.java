package ee.taltech.iti0202.tk4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exam {
    public boolean tripleUp(List<Integer> entry) {
        for (int i = 0; i < entry.size() + (-1 - 3); i++) {
            if (entry.get(i) + 1 == entry.get(i + 1) && entry.get(i + 1) == entry.get(i + 2)) {
                return true;
            }
        }
        return false;
    }

    public boolean evenlySpaced(int a, int b, int c) {
        return true;
    }

    public String zipZap(String entry) {
        return "";
    }

    public Map<String, String> mapSwitchKeysAndValues(Map<String, String> map) {
        return map;
    }
}
