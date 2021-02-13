package ee.taltech.iti0202.lotr;

public class Person {
    public String race;
    public String name;
    public Ring ring;

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
        if(name.equals("Sauron")&& ring.type==Ring.Type.THE_ONE && ring.material==Ring.Material.GOLD){
            return "Affirmative";
        }
        else if(name.equals("Sauron")&&ring.type==Ring.Type.THE_ONE&& !(ring.material==Ring.Material.GOLD)){
            return "No, the ring is fake!";
        }
        else if(ring.material==Ring.Material.GOLD && ring.type==Ring.Type.THE_ONE && !name.equals("Sauron")){
            return "No, he just stole the ring.";
        }
        else if(name.equals("Sauron") && !(ring.type==Ring.Type.THE_ONE)|| ring == null){
            return "No, but he's claiming to be.";
        }
        return "No";
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

