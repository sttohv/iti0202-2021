package ee.taltech.iti0202.coffee.machine;

import ee.taltech.iti0202.coffee.drink.Drink;
import ee.taltech.iti0202.coffee.exceptions.NoDrinkException;
import ee.taltech.iti0202.coffee.resources.ResourceStorage;
import ee.taltech.iti0202.coffee.resources.WaterTank;
import ee.taltech.iti0202.coffee.trash.Trash;

import java.util.*;
import java.util.logging.Logger;

public abstract class CoffeeMachine {
    protected ResourceStorage storage;
    protected final WaterTank waterTank;
    protected Trash trashCan;
    protected List<Drink> knownDrinks;
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Constructor
     *
     * @param tank where the machine takes its water
     */
    public CoffeeMachine(WaterTank tank) {
        storage = new ResourceStorage();
        waterTank = tank;
        trashCan = new Trash(5);
        knownDrinks = new ArrayList<>();
        log("creating a " + this.getClass().getSimpleName());
    }

    /**
     * log actions
     *
     * @param message message to be logged
     */
    protected void log(String message) {
        LOGGER.info(message);
    }

    /**
     * Start button and when called takes water(if possible)
     * from the tank and starts brewing.
     *
     * @param drink drink requested
     * @return drink made
     * @throws NoDrinkException exception why coffee can't be made
     */
    public Drink start(Drink drink) throws NoDrinkException {
        if (trashCan.isTrashFull()) {
            throw new NoDrinkException(this, NoDrinkException.Reason.TRASH_FULL);
        } else if (!storage.hasResources(drink)) {
            throw new NoDrinkException(this, NoDrinkException.Reason.NOT_ENOUGH_RESOURCES);
        } else if (waterTank.isEmpty()) {
            throw new NoDrinkException(this, NoDrinkException.Reason.NOT_ENOUGH_WATER);
        } else if (!isKnownDrink(drink)) {
            throw new NoDrinkException(this, NoDrinkException.Reason.UNKNOWN_COFFEE);
        } else {
            log("Machine made a " + drink.getName());
            waterTank.useWater();
            storage.takeResources(drink);
            trashCan.throwInTrash();
            return drink;
        }
    }

    /**
     * Checks if the Machine knows how to do the drink
     *
     * @param drink drink to be checked
     * @return if machine knows the drink or not
     */
    protected boolean isKnownDrink(Drink drink) {
        return knownDrinks.contains(drink);
    }

    /**
     * Drink is added to the list of drinks the coffee machine can make
     *
     * @param drink drink
     */
    public void addKnownDrink(Drink drink) {
        if (!isKnownDrink(drink)) {
            log("machine learned a new drink recipe");
            knownDrinks.add(drink);
        }
    }

    /**
     * get known drinks list
     *
     * @return list of known drinks
     */
    public List<Drink> getKnownDrinks() {
        return knownDrinks;
    }

    public ResourceStorage getStorage() {
        return storage;
    }

    public Trash getTrashCan() {
        return trashCan;
    }
}
