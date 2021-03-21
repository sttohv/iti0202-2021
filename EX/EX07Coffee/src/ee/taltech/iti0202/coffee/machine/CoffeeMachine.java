package ee.taltech.iti0202.coffee.machine;

import ee.taltech.iti0202.coffee.drink.Drink;
import ee.taltech.iti0202.coffee.exceptions.NoDrinkException;
import ee.taltech.iti0202.coffee.water.WaterTank;

import java.util.*;
import java.util.logging.Logger;

public abstract class CoffeeMachine {
    protected Map<String, Integer> resources;
    protected final WaterTank waterTank;
    protected int trashCount;
    protected int trashCapacity;
    protected List<Drink> knownDrinks;
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Constructor
     *
     * @param tank where the machine takes its water
     */
    public CoffeeMachine(WaterTank tank) {
        resources = new HashMap<>();
        waterTank = tank;
        trashCapacity = 5;
        trashCount = 0;
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
        if (isTrashFull()) {
            throw new NoDrinkException(this, NoDrinkException.Reason.TRASH_FULL);
        } else if (!hasResources(drink)) {
            throw new NoDrinkException(this, NoDrinkException.Reason.NOT_ENOUGH_RESOURCES);
        } else if (waterTank.isEmpty()) {
            throw new NoDrinkException(this, NoDrinkException.Reason.NOT_ENOUGH_WATER);
        } else if (!isKnownDrink(drink)) {
            throw new NoDrinkException(this, NoDrinkException.Reason.UNKNOWN_COFFEE);
        } else {
            log("Machine made a " + drink.getName());
            waterTank.useWater();
            takeResources(drink);
            trashCount++;
            return drink;
        }
    }

    /**
     * Removes every resource from drink
     *
     * @param drink drink to be made
     */
    protected void takeResources(Drink drink) {
        //Iterates all requirements for the drink
        for (String resource : drink.getResourcesNeeded().keySet()
        ) {
            log("Machine used resource " + resource);
            takeResource(resource, drink.getResourcesNeeded().get(resource));
        }
    }

    /**
     * takes a resource from resource storage
     *
     * @param resource resource to be taken
     * @param amount   the amount of the resource
     */
    protected void takeResource(String resource, Integer amount) {
        resource = resource.toLowerCase();
        resources.put(resource, resources.get(resource) - amount);
    }

    /**
     * Checks if machine has all the resources needed
     *
     * @param drink drink that has to be checked
     * @return if machine has enough resources
     */
    protected boolean hasResources(Drink drink) {
        for (String resource : drink.getResourcesNeeded().keySet()
        ) {
            //If storage doesn't have enough of some kind of a resource then returns false
            if (!hasResource(resource, drink.getResourcesNeeded().get(resource))) {
                return false;
            }
        }
        return true;

    }

    /**
     * Checks if resource storage has enough resources
     *
     * @param resource a
     * @param amount   b
     * @return if resource storage has enough resources
     */
    protected boolean hasResource(String resource, Integer amount) {
        resource = resource.toLowerCase();
        return resources.containsKey(resource) && resources.get(resource) >= amount;
    }


    /**
     * Checks if the trash is full
     *
     * @return if the trash is full or not
     */
    protected boolean isTrashFull() {
        return trashCount == trashCapacity;
    }

    /**
     * Empties the trash
     */
    public void emptyTrash() {
        log("trash thrown out");
        trashCount = 0;
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
     * adds resources to the coffee machine
     *
     * @param resource resource to be added
     * @param amount   the amount of the resource to be added
     */
    public void addResource(String resource, Integer amount) {
        resource = resource.toLowerCase();
        log(amount + " " + resource + " resources were added to the machine");
        if (hasResource(resource, amount)) {
            resources.put(resource, resources.get(resource) + amount);
        } else {
            resources.put(resource, amount);
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

    public Map<String, Integer> getResources() {
        return resources;
    }
}
