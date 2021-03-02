package ee.taltech.iti0202.mysticorbs.factory;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.oven.Oven;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrbFactory {
    public ResourceStorage Storage;
    private List<Oven> ovens;
    private List<Orb> orbs;
    private List<Oven> unfixableOvens;

    public OrbFactory(ResourceStorage resourceStorage) {
        Storage = resourceStorage;
        ovens = new ArrayList<>();
        orbs = new ArrayList<>();
        unfixableOvens = new ArrayList<>();
    }

    public void addOven(Oven oven) {
        if (oven.getStorage().equals(Storage) && !ovens.contains(oven)) {
            ovens.add(oven);
        }
    }

    public List<Oven> getOvens() {
        return ovens;
    }

    public List<Orb> getAndClearProducedOrbsList() {
        List<Orb> oldOrbs = new ArrayList<>(orbs);
        orbs = new ArrayList<>();
        return oldOrbs;
    }

    public int produceOrbs() {
        int count = 0;
        for (Oven oven : ovens
        ) {
            //kui on Magic- või SpaceOven, siis proovi fixida
            //kui on teised Ovenid siis ei pea seda checkima vaid saab kohe seda alumist sektsiooni katsetada
            if (oven.getClass().getSimpleName().equals("MagicOven")
                    || oven.getClass().getSimpleName().equals("SpaceOven")) {
                try {
                    oven.fix();

                } catch (CannotFixException ex) {
                    if (ex.getReason().equals(CannotFixException.Reason.FIXED_MAXIMUM_TIMES)) {
                        unfixableOvens.add(oven);
                    }
                }
                Optional<Orb> optionalOrb = oven.craftOrb();
                if (optionalOrb.isPresent()) {
                    orbs.add(optionalOrb.get());
                    count++;
                }
            } else {
                Optional<Orb> optionalOrb = oven.craftOrb();
                if (optionalOrb.isPresent()) {
                    orbs.add(optionalOrb.get());
                    count++;
                }

            }
        }
        return count;
    }

    public int produceOrbs(int cycles) {
        int sum = 0;
        for (int i = 0; i < cycles; i++) {
            sum += produceOrbs();
        }
        return sum;
    }

    public List<Oven> getOvensThatCannotBeFixed() {
        return unfixableOvens;
    }

    public void getRidOfOvensThatCannotBeFixed() {
        for (Oven oven : unfixableOvens
        ) {
            ovens.remove(oven);
        }
    }
    public void optimizeOvensOrder(){
        ovens.sort(Oven::compareTo);
    }

}
