package ee.taltech.iti0202.lotr;

public class Person {
    private String race;
    private String name;
    private Ring ring;

    /**
     * Constructor 1
     *
     * @param name Persons name
     * @param race Persons race
     * @param ring Persons ring
     */
    public Person(String race, String name, Ring ring) {
        this.race = race;
        this.name = name;
        this.ring = ring;
    }

    /**
     * Constructor 2
     *
     * @param race Persons race
     * @param name Persons name
     */
    public Person(String race, String name) {
        this.race = race;
        this.name = name;
    }

    /**
     * Set ring
     *
     * @param ring
     */
    public void setRing(Ring ring) {
        this.ring = ring;
    }

    /**
     * Is sauron?
     *
     * @return Returns if persons is rightful Sauron
     */
    public String isSauron() {
        try {


            if (name.equals("Sauron") && ring.getType() == Ring.Type.THE_ONE && ring.getMaterial() ==
                    Ring.Material.GOLD) {
                return "Affirmative";
            } else if (name.equals("Sauron") && ring.getType() == Ring.Type.THE_ONE && !(ring.getMaterial() ==
                    Ring.Material.GOLD)) {
                return "No, the ring is fake!";
            } else if (ring.getMaterial() == Ring.Material.GOLD && ring.getType() == Ring.Type.THE_ONE &&
                    !name.equals("Sauron")) {
                return "No, he just stole the ring";
            } else if (name.equals("Sauron") && !(ring.getType() == Ring.Type.THE_ONE)) {
                return "No, but he's claiming to be";
            }
            return "No";
        } catch (Exception e) {
            if (name.equals("Sauron")) {
                return "No, but he's claiming to be";
            } else {
                return "No";
            }
        }
    }

    /**
     * get method that returns private field race
     *
     * @return Persons race
     */
    public String getRace() {
        return race;
    }

    /**
     * Get method that returns private field name
     *
     * @return Persons name
     */
    public String getName() {
        return name;
    }

    /**
     * Get method that returns private field ring
     *
     * @return Persons ring
     */
    public Ring getRing() {
        return ring;
    }

}

