package ee.taltech.iti0202.mysticorbs.orb;

public class MagicOrb extends Orb {
    /**
     * Constructor
     *
     * @param creator creator name
     */
    public MagicOrb(String creator) {
        super(creator);
    }

    @Override
    public void charge(String resource, int amount) {
        amount = 2 * amount;
        super.charge(resource, amount);
    }

    @Override
    public String toString() {
        return "MagicOrb by " + creator;
    }
}
