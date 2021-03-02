package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.orb.SpaceOrb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class SpaceOven extends Oven implements Fixable {

    public static final int STAR_FRAG_AMOUNT = 15;
    public static final int LIQ_SILV_AMOUNT = 40;
    public static final int STAR_ES_AMMOUNT = 10;
    public final int timesBeforeBroken = 25;
    private int fixCount;

    /**
     * Constructor
     *
     * @param name            oven name
     * @param resourceStorage oven storage where are all the resources
     */
    public SpaceOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
        untilBroken = timesBeforeBroken;
        fixCount = 0;
    }

    @Override
    public Optional<Orb> craftOrb() {
        if (!isBroken() && storage.hasEnoughResource("meteorite stone", 1)
                && storage.hasEnoughResource("star fragment", STAR_FRAG_AMOUNT)) {
            createdOrbsAmount++;
            Orb newOrb = new SpaceOrb(name);
            storage.takeResource("meteorite stone", 1);
            storage.takeResource("star fragment", STAR_FRAG_AMOUNT);
            return Optional.of(newOrb);
        } else if (storage.hasEnoughResource("pearl", 1)
                && storage.hasEnoughResource("silver", 1)) {
            createdOrbsAmount++;
            Orb newOrb = new Orb(name);
            newOrb.charge("pearl", 1);
            newOrb.charge("silver", 1);
            storage.takeResource("pearl", 1);
            storage.takeResource("silver", 1);
            return Optional.of(newOrb);
        }
        return Optional.empty();
    }

    @Override
    public void fix() throws CannotFixException {
        if (fixCount == 5) {
            untilBroken = createdOrbsAmount;
            throw new CannotFixException(this, CannotFixException.Reason.FIXED_MAXIMUM_TIMES);
            //siis isEmpty saab aru, et ahi ei ole katki
            //
        } else if (isBroken() && storage.hasEnoughResource("liquid silver", LIQ_SILV_AMOUNT)) {
            untilBroken += timesBeforeBroken; //annab kasutuskordi juurde ehk masin on fixed
            fixCount++;
            storage.takeResource("liquid silver", LIQ_SILV_AMOUNT);
        } else if (isBroken() && storage.hasEnoughResource("star essence", STAR_ES_AMMOUNT)) {
            untilBroken += timesBeforeBroken; //annab kasutuskordi juurde
            fixCount++;
            storage.takeResource("star essence", STAR_ES_AMMOUNT);
        } else {
            if (!isBroken()) {
                throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
            } else {
                throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
            }
        }
    }

    @Override
    public int getTimesFixed() {
        return fixCount;
    }
}
