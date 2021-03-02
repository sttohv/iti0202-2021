package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.MagicOrb;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class MagicOven extends Oven implements Fixable {

    public static final int dustAmount = 3;
    public final int TIMES_BEFORE_BROKEN = 5;
    private int timesFixed;

    /**
     * Constructor
     *
     * @param name            oven name
     * @param resourceStorage where stores resources
     */
    public MagicOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
        untilBroken = TIMES_BEFORE_BROKEN;
        timesFixed = 0;
    }

    @Override
    public Optional<Orb> craftOrb() {
        //CreatedOrbsAmmount
        if (!isBroken() && Storage.hasEnoughResource("gold", 1)
                && Storage.hasEnoughResource("dust", dustAmount)) {
            CreatedOrbsAmount++; //1
            Storage.takeResource("gold", 1);
            Storage.takeResource("dust", dustAmount);
            if (CreatedOrbsAmount == 1 || CreatedOrbsAmount % 2 == 1) {
                Orb justOrb = new Orb(Name);
                justOrb.charge("gold", 1);
                justOrb.charge("dust", dustAmount);

                return Optional.of(justOrb);
            } else {
                MagicOrb magicOrb = new MagicOrb(Name);
                magicOrb.charge("gold", 1);
                magicOrb.charge("dust", dustAmount);
                return Optional.of(magicOrb);
            }
        }
        return Optional.empty();
    }


    @Override
    public void fix() throws CannotFixException {
        int clayNeeded = 25 * (timesFixed + 1);
        int frPowderNeeded = 100 * (timesFixed + 1);
        if (isBroken() && timesFixed < 10 && Storage.hasEnoughResource("clay", clayNeeded)
                && Storage.hasEnoughResource("freezing powder", frPowderNeeded)) {
            untilBroken += TIMES_BEFORE_BROKEN;
            //Annab kasutuskordi juurde ühe terve tsükli võrra, magicOvenil on see 5 enne katki minemist
            timesFixed++;
            Storage.takeResource("clay", clayNeeded);
            Storage.takeResource("freezing powder", frPowderNeeded);
        } else {
            if (!isBroken()) {
                throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
            } else if (timesFixed == 10) {
                throw new CannotFixException(this, CannotFixException.Reason.FIXED_MAXIMUM_TIMES);
            } else {
                throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
            }
        }
    }

    @Override
    public int getTimesFixed() {
        return timesFixed;
    }
}
