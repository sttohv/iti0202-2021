package ee.taltech.iti0202.mysticorbs.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ResourceStorage {
    Map<String, Integer> resources = new HashMap<>();

    /**
     * Checks if there aren't any resources left
     *
     * @return if is empty
     */
    public boolean isEmpty() {
        List<Integer> amount = new ArrayList<>(resources.values());
        if (amount.isEmpty() || amount.stream().mapToInt(Integer::intValue).sum() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Adds resources
     *
     * @param resource resource name
     * @param amount   resource ammount
     */
    public void addResource(String resource, int amount) {

        if (!resource.isBlank() && amount >= 0) {
            resource = capitalize(resource);
            //System.out.println(resource);
            if (isEmpty() || !resources.containsKey(resource)) {
                resources.put(resource, amount);
                //System.out.println(resource + amount);
            } else {
                resources.put(resource, resources.get(resource) + amount);
                //System.out.println(resource +  amount);
            }
        }

    }

    /**
     * Gets resource amount
     *
     * @param resource resource name
     * @return returns resource amount
     */
    public int getResourceAmount(String resource) {
        resource = capitalize(resource);
        if (resources.containsKey(resource)) {
            return resources.get(resource);
        }
        return 0;
    }

    /**
     * Checks if there are enough resources
     *
     * @param resource resource name
     * @param amount   resource amount
     * @return if has enough
     */
    public boolean hasEnoughResource(String resource, int amount) {
        resource = capitalize(resource);
        if (amount < 1) {
            return false;
        } else if (resources.get(resource) >= amount) {
            return true;
        }
        return false;
    }

    /**
     * If has enough resources then take away from all resources
     *
     * @param resource resource name
     * @param amount   resource amount
     * @return if can take resource
     */
    public boolean takeResource(String resource, int amount) {
        resource = capitalize(resource);
        if (isEmpty() || !hasEnoughResource(resource, amount)) {
            return false;
        } else {
            resources.put(resource, resources.get(resource) - amount);
            return true;
        }
    }

    /**
     * Makes the String capitalized
     *
     * @param str some string
     * @return string capitalized
     */
    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase(Locale.ROOT);
    }
}
