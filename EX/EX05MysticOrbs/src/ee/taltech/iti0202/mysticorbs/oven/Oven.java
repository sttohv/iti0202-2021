package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class Oven implements Comparable<Oven>, Fixable {
    public int untilBroken = 15;
    protected String name;
    protected ResourceStorage storage;
    protected int createdOrbsAmount;

    /**
     * Constructor
     *
     * @param name            oven name
     * @param resourceStorage where stores resources
     */
    public Oven(String name, ResourceStorage resourceStorage) {
        this.name = name;
        storage = resourceStorage;
        createdOrbsAmount = 0;
    }

    /**
     * Get oven name
     *
     * @return oven name
     */
    public String getName() {
        return name;
    }

    /**
     * Get where oven resources storage
     *
     * @return storage
     */
    public ResourceStorage getResourceStorage() {
        return storage;
    }

    /**
     * Get absorbed orbs amount
     *
     * @return amount of absorbed orbs
     */
    public int getCreatedOrbsAmount() {
        return createdOrbsAmount;
    }

    /**
     * Breaks after oven is used 15 times
     *
     * @return if oven is broken
     */
    public boolean isBroken() {
        return createdOrbsAmount >= untilBroken;
    }

    /**
     * Crafts an orb
     *
     * @return orb if could be made
     */
    public Optional<Orb> craftOrb() {
        if (!isBroken() && storage.hasEnoughResource("pearl", 1)
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
    public int compareTo(Oven o) {
        if (isBroken() && !o.isBroken()) {
            return -1;
        } else if (!isBroken() && o.isBroken()) {
            return 1;
        } else { //mõlemad katki v mõlemad korras
            String className1 = this.getClass().getSimpleName();
            String className2 = o.getClass().getSimpleName();

//            boolean areBothMagic = className1.equals("MagicOven") && className2.equals("InfinityMagicOven")
//                    || className2.equals("MagicOven") && className1.equals("InfinityMagicOven")
//                    || className1.equals("MagicOven") && className1.equals(className2)
//                    || className1.equals("InfinityMagicOven") && className1.equals(className2);

            boolean areBothMagic = className1.contains("Magic") && className2.contains("Magic");
            if (!className1.equals(className2) && !(areBothMagic)) {
                //nimed pole võrdsed ja kumbki pole magic

                if (className1.equals("SpaceOven")) {
                    // kui o1 on Space Oven siis see on suurem
                    return 1;
                } else if (className2.equals("SpaceOven")) {
                    // kui o2 on Space Oven siis see on suurem
                    return -1;
                } else if (className1.contains("Magic")) {
                    //kui o1 on ainult maagiline ja o2 simple
                    return 1;
                } else if (className2.contains("Magic")) {
                    //kui o2 on ainult maagiline ja o1 simple
                    return -1;
                }
            } else if (areBothMagic) {
                //kui mõlemad on magicud
                if (createdOrbsAmount + 1 % 2 == 0 && o.createdOrbsAmount + 1 % 2 == 1) {
                    //kui o1 on järgmine kuul maagiline, aga o2 pole  -> o1>o2
                    return 1;
                } else if (createdOrbsAmount + 1 % 2 == 1 && o.createdOrbsAmount + 1 % 2 == 0) {
                    //kui o2 on järgmine kuul maagiline, aga o1 pole
                    return -1;
                } else if (createdOrbsAmount == o.createdOrbsAmount) {
                    //mõlemal sama palju kuule

                    if (className1.equals("InfinityMagicOven") && className2.equals("MagicOven")) {
                        // o1 > o2
                        return 1;
                    } else if (className2.equals("InfinityMagicOven") && className1.equals("MagicOven")) {
                        return -1;
                    }
                }
            } else if (createdOrbsAmount > o.createdOrbsAmount) {
                //kui o1-ga on rohkem kuule loodud
                return -1;
            } else if (o.createdOrbsAmount > createdOrbsAmount) {
                //kui o2-ga on rohkem kuule loodud
                return 1;
            } else if (name.compareTo(o.name) > 0) {
                // kui o1 nimi on pikem kui o2 oma
                return 1;
            } else if (name.compareTo(o.name) < 0) {
                //kui o2 nimi on pikem kui o1 oma
                return -1;
            }
            //nimed on võrdsed
            return 0;
        }
    }

    @Override
    public void fix() throws CannotFixException {
        // throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
    }

    @Override
    public int getTimesFixed() {
        return 0;
    }


}
