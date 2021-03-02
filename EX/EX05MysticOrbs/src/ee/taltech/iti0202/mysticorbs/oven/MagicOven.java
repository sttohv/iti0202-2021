package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.MagicOrb;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class MagicOven extends Oven implements Fixable {

    public static final int dustAmount = 3;
    public final int TIMES_BEFORE_BROKEN = 5;
    private int fixCount;

    public MagicOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
        untilBroken = TIMES_BEFORE_BROKEN;
        fixCount = 0;
    }

    @Override
    public Optional<Orb> craftOrb() {
        //CreatedOrbsAmmount
        if (!isBroken() && Storage.hasEnoughResource("gold", 1)
                && Storage.hasEnoughResource("dust", dustAmount)) {
            CreatedOrbsAmount++;
            Storage.takeResource("gold", 1);
            Storage.takeResource("dust", 1);
            if (CreatedOrbsAmount % 2 != 0) {
                Orb newOrb = new Orb(Name);
                newOrb.charge("gold", 1);
                newOrb.charge("dust", dustAmount);

                return Optional.of(newOrb);
            } else {
                MagicOrb orb = new MagicOrb(Name);
                orb.charge("gold", 1);
                orb.charge("dust", dustAmount);
                return Optional.of(orb);
            }
        }
        return Optional.empty();
    }


    @Override
    public void fix() throws CannotFixException {
        int clayNeeded = 25 * fixCount;
        int frPowderNeeded = 100 * fixCount;
        if (isBroken() && fixCount < 10 && Storage.hasEnoughResource("clay", clayNeeded)
                && Storage.hasEnoughResource("freezing powder", frPowderNeeded)) {
            untilBroken += TIMES_BEFORE_BROKEN;
            //Annab kasutuskordi juurde ühe terve tsükli võrra, magicOvenil on see 5 enne katki minemist
            fixCount++;
            Storage.takeResource("clay", clayNeeded);
            Storage.takeResource("freezing powder", frPowderNeeded);
        } else {
            if (!isBroken()) {
                throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
            }
            else if(fixCount == 10){
                throw new CannotFixException(this, CannotFixException.Reason.FIXED_MAXIMUM_TIMES);
            }
            else{
                throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
            }
        }
    }

    @Override
    public int getTimesFixed() {
        return fixCount;
    }
}
