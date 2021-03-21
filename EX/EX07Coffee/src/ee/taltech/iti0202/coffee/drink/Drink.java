package ee.taltech.iti0202.coffee.drink;

import java.util.Map;
import java.util.logging.Logger;

public class Drink {
    private final String name;
    private final Map<String, Integer> resourcesNeeded;
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Constructor
     *
     * @param name      drink name
     * @param resources resources needed to make the drink
     */
    public Drink(String name, Map<String, Integer> resources) {
        this.name = name.toLowerCase();
        this.resourcesNeeded = resources;
        log(this.name);

    }

    /**
     * logged creating a drink
     */
    public void log(String drinkName) {
        LOGGER.info("created " + drinkName + " object");
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getResourcesNeeded() {
        return resourcesNeeded;
    }
}
