package ee.taltech.iti0202.coffee.drink;

import java.util.Map;

public class Drink {
    private final String name;
    private final Map<String, Integer> resourcesNeeded;

    /**
     * Constructor
     *
     * @param name      drink name
     * @param resources resources needed to make the drink
     */
    public Drink(String name, Map<String, Integer> resources) {
        this.name = name.toLowerCase();
        this.resourcesNeeded = resources;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getResourcesNeeded() {
        return resourcesNeeded;
    }
}
