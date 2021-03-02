package ee.taltech.iti0202.mysticorbs.orb;

import java.util.Locale;

public class Orb {
    public String Creator;
    protected int Energy;

    public Orb(String creator) {
        Creator = creator;
        Energy = 0;
    }

    public void charge(String resource, int amount) {
        resource = capitalize(resource);
        if (!(resource.equals("Dust") || resource.isBlank())) {
            Energy += resource.length() * amount;
        }
    }

    public int getEnergy() {
        return Energy;
    }

    @Override
    public String toString() {
        return "Orb by " + Creator;
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase(Locale.ROOT);
    }
}
