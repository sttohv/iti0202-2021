package ee.taltech.iti0202.lotr;

public class Person {
    private String race;
    private String name;
    private Ring ring;

    public Person(String race, String name, Ring ring) {
        this.race = race;
        this.name = name;
        this.ring = ring;
    }

    public Person(String race, String name) {
        this.race = race;
        this.name = name;
    }

    public void setRing(Ring ring) {
        this.ring = ring;
    }

    public String isSauron() {
        try {


            if (name.equals("Sauron") && ring.getType() == Ring.Type.THE_ONE && ring.getMaterial() == Ring.Material.GOLD) {
                return "Affirmative";
            } else if (name.equals("Sauron") && ring.getType() == Ring.Type.THE_ONE && !(ring.getMaterial() == Ring.Material.GOLD)) {
                return "No, the ring is fake!";
            } else if (ring.getMaterial() == Ring.Material.GOLD && ring.getType() == Ring.Type.THE_ONE && !name.equals("Sauron")) {
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

    public String getRace() {
        return race;
    }

    public String getName() {
        return name;
    }

    public Ring getRing() {
        return ring;
    }

}

