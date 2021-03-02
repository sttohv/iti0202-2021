package ee.taltech.iti0202.mysticorbs.orb;

public class Orb {
    public String Creator;
    protected int Energy;

    public Orb(String creator) {
        Creator = creator;
        Energy = 0;
    }

    public void charge(String resource, int amount) {
        if (!(resource.equals("dust") || resource.isBlank())) {
            Energy += resource.length() * amount;
        }
    }

    public int getEnergy() {
        return Energy;
    }

    @Override
    public String toString(){
        return "Orb by " + Creator;
    }
}
