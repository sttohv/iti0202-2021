package ee.taltech.iti0202.mysticorbs.orb;

import java.util.Locale;

public class Orb {
    public String creator;
    protected int energy;

    /**
     * Constructor
     *
     * @param creator creator name
     */
    public Orb(String creator) {
        this.creator = creator;
        energy = 0;
    }

    /**
     * Charge orb with resources
     *
     * @param resource resource name
     * @param amount   resource amount
     */
    public void charge(String resource, int amount) {
        resource = capitalize(resource);
        if (!(resource.equals("Dust") || resource.isBlank()) && amount >= 0) {
            energy += resource.length() * Math.abs(amount);
        }
    }

    /**
     * Get orb energy
     *
     * @return orb energy
     */
    public int getEnergy() {
        return energy;
    }

    @Override
    public String toString() {
        return "Orb by " + creator;
    }

    /**
     * Uppers strings first letter
     *
     * @param str some string
     * @return capitalized string
     */
    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase(Locale.ROOT);
    }
}
