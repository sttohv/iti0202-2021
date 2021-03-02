package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class SpaceOven extends Oven implements Fixable {

    public static final int starFragAmount = 15;
    public static final int liqSilvAmount = 40;
    public static final int starEsAmmount = 10;
    public final int TIMES_BEFORE_BROKEN = 25;
    private int fixCount;

    public SpaceOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
        untilBroken = TIMES_BEFORE_BROKEN;
        fixCount = 0;
    }

    @Override
    public Optional<Orb> craftOrb() {
        if (!isBroken() && Storage.hasEnoughResource("meteorite stone", 1)
                && Storage.hasEnoughResource("star fragment", starFragAmount)) {
            CreatedOrbsAmount++;
            Orb newOrb = new Orb(Name);
            Storage.takeResource("meteorite stone", 1);
            Storage.takeResource("star fragment", starFragAmount);
            return Optional.of(newOrb);
        } else if (Storage.hasEnoughResource("pearl", 1)
                && Storage.hasEnoughResource("silver", 1)) {
            CreatedOrbsAmount++;
            Orb newOrb = new Orb(Name);
            newOrb.charge("pearl", 1);
            newOrb.charge("silver", 1);
            Storage.takeResource("pearl", 1);
            Storage.takeResource("silver", 1);
            return Optional.of(newOrb);
        }
        return Optional.empty();
    }

    @Override
    public void fix() throws CannotFixException {
        if (fixCount == 5) {
            untilBroken = CreatedOrbsAmount;
            throw new CannotFixException(this, CannotFixException.Reason.FIXED_MAXIMUM_TIMES);
            //siis isEmpty saab aru, et ahi ei ole katki
            //
        } else if (isBroken() && Storage.hasEnoughResource("liquid silver", liqSilvAmount)) {
            untilBroken += TIMES_BEFORE_BROKEN; //annab kasutuskordi juurde ehk masin on fixed
            fixCount++;
            Storage.takeResource("liquid silver", liqSilvAmount);
        } else if (isBroken() && Storage.hasEnoughResource("star essence", starEsAmmount)) {
            untilBroken += TIMES_BEFORE_BROKEN; //annab kasutuskordi juurde
            fixCount++;
            Storage.takeResource("star essence", starEsAmmount);
        } else {
            if (isBroken()) {
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
