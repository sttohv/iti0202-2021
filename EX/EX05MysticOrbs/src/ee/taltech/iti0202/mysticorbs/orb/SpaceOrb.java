package ee.taltech.iti0202.mysticorbs.orb;

public class SpaceOrb extends Orb {
    /**
     * Constructor
     *
     * @param creator name
     */
    public SpaceOrb(String creator) {
        super(creator);
        energy = 100;
    }

    @Override
    public void charge(String resource, int amount) {
    }

    @Override
    public String toString() {
        return "SpaceOrb by " + creator;
    }

    /**
     * Space orb absorbs another orb
     *
     * @param orb orb to be absorbed
     * @return if entered orb can be absorbed by current one
     */
    public boolean absorb(Orb orb) {
        if (energy > orb.energy) {
            energy += orb.energy;
            orb.energy = 0;
            return true;
        }
        return false;
    }

}
