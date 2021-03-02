package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.MagicOrb;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class MagicOven extends Oven implements Fixable {

    public static final int DUST_AMOUNT = 3;
    public static final int CLAY_AMOUNT = 25;
    public static final int FR_POWDER_AMOUNT = 100;
    public final int timesBeforeBroken = 5;
    private int timesFixed;

    /**
     * Constructor
     *
     * @param name            oven name
     * @param resourceStorage where stores resources
     */
    public MagicOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
        untilBroken = timesBeforeBroken;
        timesFixed = 0;
    }

    @Override
    public Optional<Orb> craftOrb() {
        //CreatedOrbsAmmount
        if (!isBroken() && storage.hasEnoughResource("gold", 1)
                && storage.hasEnoughResource("dust", DUST_AMOUNT)) {
            createdOrbsAmount++; //1
            storage.takeResource("gold", 1);
            storage.takeResource("dust", DUST_AMOUNT);
            if (createdOrbsAmount == 1 || createdOrbsAmount % 2 == 1) {
                Orb justOrb = new Orb(name);
                justOrb.charge("gold", 1);
                justOrb.charge("dust", DUST_AMOUNT);

                return Optional.of(justOrb);
            } else {
                MagicOrb magicOrb = new MagicOrb(name);
                magicOrb.charge("gold", 1);
                magicOrb.charge("dust", DUST_AMOUNT);
                return Optional.of(magicOrb);
            }
        }
        return Optional.empty();
    }


    @Override
    public void fix() throws CannotFixException {
        int clayNeeded = CLAY_AMOUNT * (timesFixed + 1);
        int frPowderNeeded = FR_POWDER_AMOUNT * (timesFixed + 1);
        if (isBroken() && timesFixed < 10 && storage.hasEnoughResource("clay", clayNeeded)
                && storage.hasEnoughResource("freezing powder", frPowderNeeded)) {
            untilBroken += timesBeforeBroken;
            //Annab kasutuskordi juurde ühe terve tsükli võrra, magicOvenil on see 5 enne katki minemist
            timesFixed++;
            storage.takeResource("clay", clayNeeded);
            storage.takeResource("freezing powder", frPowderNeeded);
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
