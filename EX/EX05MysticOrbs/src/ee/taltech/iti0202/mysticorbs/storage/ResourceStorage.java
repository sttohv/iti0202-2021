package ee.taltech.iti0202.mysticorbs.storage;

import java.util.*;

public class ResourceStorage {
    Map<String, Integer> resources = new HashMap<>();

    public boolean isEmpty() {
        List<Integer> amount = new ArrayList<>(resources.values());
        if (amount.isEmpty() || amount.stream().mapToInt(Integer::intValue).sum() == 0) {
            return true;
        }
        return false;
    }

    public void addResource(String resource, int amount) {

        if ((resource != null || !resource.equals("")) && amount >= 0) {
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

    public int getResourceAmount(String resource) {
        resource = capitalize(resource);
        if (resources.containsKey(resource)) {
            return resources.get(resource);
        }
        return 0;
    }

    public boolean hasEnoughResource(String resource, int amount) {
        resource = capitalize(resource);
        if (amount < 1) {
            return false;
        } else if (resources.get(resource) >= amount) {
            return true;
        }
        return false;
    }

    public boolean takeResource(String resource, int amount) {
        resource = capitalize(resource);
        if (isEmpty() || !hasEnoughResource(resource, amount)) {
            return false;
        } else {
            resources.put(resource, resources.get(resource) - amount);
            return true;
        }
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase(Locale.ROOT);
    }
}
