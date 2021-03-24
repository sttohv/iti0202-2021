package ee.taltech.iti0202.coffee.machine;

import ee.taltech.iti0202.coffee.drink.Drink;
import ee.taltech.iti0202.coffee.exceptions.NoDrinkException;
import ee.taltech.iti0202.coffee.resources.WaterTank;
import ee.taltech.iti0202.coffee.trash.Trash;

import java.util.HashMap;
import java.util.Map;

public class CapsuleCoffeeMachine extends CoffeeMachine {
    private String capsuleName;
    private boolean isCapsuleIn;

    /**
     * Constructor
     *
     * @param tank watertank
     */
    public CapsuleCoffeeMachine(WaterTank tank) {
        super(tank);
        trashCan = new Trash(10);
        capsuleName = "empty";
        isCapsuleIn = false;
    }

    /**
     * Returns a drink that has been made.
     * If the capsule is empty or there isn't a capsule in returns water
     * Otherwise returns the drink entered if trash isn't full and there is enough water
     *
     * @param drink drink requested
     * @return drink made
     * @throws NoDrinkException why the drink wasn't made
     */
    @Override
    public Drink start(Drink drink) throws NoDrinkException {
        if (trashCan.isTrashFull()) {
            throw new NoDrinkException(this, NoDrinkException.Reason.TRASH_FULL);
        } else if (waterTank.isEmpty()) {
            throw new NoDrinkException(this, NoDrinkException.Reason.NOT_ENOUGH_WATER);
        } else if (!isCapsuleIn || capsuleName.equals("empty")) {
            log("made water instead of ordered" + drink.getName() + " because capsule was empty or not entered");
            waterTank.useWater();
            trashCan.throwInTrash();
            return new Drink("water", new HashMap<>());
        } else {
            log("made " + drink.getName());
            waterTank.useWater();
            storage.takeResources(drink);
            trashCan.throwInTrash();
            capsuleName = "empty";
            return drink;
        }

    }


    /**
     * Adds a capsule in the machine if the machine has the capsule in resources
     * and if the capsule socket is empty
     * Otherwise throws an exception
     *
     * @param capsuleName resource name
     * @throws NoDrinkException Reason why you can't add a capsule in
     */
    public void addCapsuleToMachine(String capsuleName) throws NoDrinkException {
        capsuleName = capsuleName.toLowerCase();
        if (!(storage.getResources().containsKey(capsuleName) && storage.getResources().get(capsuleName) > 0)) {
            throw new NoDrinkException(this, NoDrinkException.Reason.NOT_ENOUGH_RESOURCES);
        } else if (isCapsuleIn) {
            throw new NoDrinkException(this, NoDrinkException.Reason.CAPSULE_ALREADY_IN);
        } else {
            log("added " + capsuleName + " capsule to the machine");
            isCapsuleIn = true;
            this.capsuleName = capsuleName;
            Map<String, Integer> resources = storage.getResources();
            resources.put(capsuleName, resources.get(capsuleName) - 1);
            storage.setResources(resources);
        }
    }

    /**
     * removes capsule from socket
     */
    public void removeCapsule() {
        if (isCapsuleIn) {
            log("removed capsule from socket");
            isCapsuleIn = false;
            capsuleName = "empty";
        }

    }

    public String getCapsuleName() {
        return capsuleName;
    }
}
