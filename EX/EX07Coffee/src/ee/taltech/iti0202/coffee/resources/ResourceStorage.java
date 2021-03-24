package ee.taltech.iti0202.coffee.resources;

import ee.taltech.iti0202.coffee.drink.Drink;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ResourceStorage {
    protected Map<String, Integer> resources;
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    /**
     * Constructor
     */
    public ResourceStorage() {
        resources = new HashMap<>();
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
     * Checks if machine has all the resources needed
     *
     * @param drink drink that has to be checked
     * @return if machine has enough resources
     */
    public boolean hasResources(Drink drink) {
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
     * Removes every resource from drink
     *
     * @param drink drink to be made
     */
    public void takeResources(Drink drink) {
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

    public Map<String, Integer> getResources() {
        return resources;
    }

    public void setResources(Map<String, Integer> resources) {
        this.resources = resources;
    }
}
