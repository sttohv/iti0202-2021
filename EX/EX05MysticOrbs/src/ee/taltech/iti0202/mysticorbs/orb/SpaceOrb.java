package ee.taltech.iti0202.mysticorbs.orb;

public class SpaceOrb extends Orb {
    /**
     * Constructor
     *
     * @param creator name
     */
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

    /**
     * Space orb absorbs another orb
     *
     * @param orb orb to be absorbed
     * @return if entered orb can be absorbed by current one
     */
    public boolean absorb(Orb orb) {
        if (Energy > orb.Energy) {
            Energy += orb.Energy;
            orb.Energy = 0;
            return true;
        }
        return false;
    }

}
