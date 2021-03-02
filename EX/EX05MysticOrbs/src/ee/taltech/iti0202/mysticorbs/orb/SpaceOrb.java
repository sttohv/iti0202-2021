package ee.taltech.iti0202.mysticorbs.orb;

public class SpaceOrb extends Orb {
    public SpaceOrb(String creator) {
        super(creator);
        Energy = 100;
    }

    @Override
    public void charge(String resource, int amount) {
    }

    @Override
    public String toString() {
        return "SpaceOrb by " + Creator;
    }

    public boolean absorb(Orb orb) {
        if (Energy > orb.Energy) {
            Energy += orb.Energy;
            orb.Energy = 0;
            return true;
        }
        return false;
    }

}
