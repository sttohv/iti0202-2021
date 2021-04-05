package ee.taltech.iti0202.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class World {
    private List<Location> locations;
    private List<Courier> couriers;

    public World() {
        locations = new ArrayList<>();
        couriers = new ArrayList<>();

    }

    public Optional<Location> addLocation(String name, List<String> otherLocations, List<Integer> distances) {
        if (otherLocations.size() != distances.size() || otherLocations.size() != locations.size()) {
            return Optional.empty();
        }
        Location location = new Location(name);
        locations.add(location);
        if (locations.isEmpty()) {
            location.addDistance("", 0);
        } else {
            addDistanceEveryLocation(location, otherLocations, distances);
        }
        return Optional.of(location);


    }

    public Optional<Courier> addCourier(String name, String to) {
        if (!containsCourierWithName(name) && containsLocation(to)) {
            Courier courier = new Courier(name, getLocation(to));
            couriers.add(courier);
            return Optional.of(courier);
        }
        return Optional.empty();
    }

    public boolean giveStrategy(String name, Strategy strategy) {
        if (getCourier(name) == null) {
            return false;
        } else {
            getCourier(name).setStrategy(strategy);
            return true;
        }
    }

    public void tick() {
    }

    private boolean containsLocation(String location) {
        return locations.stream().anyMatch(o -> o.getName().equals(location));
    }

    private boolean containsCourierWithName(String name) {
        return couriers.stream().anyMatch(o -> o.getName().equals(name));
    }

    private Courier getCourier(String name) {
        return couriers.stream().filter(o -> o.getName().equals(name)).findFirst().orElse(null);
    }

    private Location getLocation(String location) {
        return locations.stream().filter(o -> o.getName().equals(location)).findFirst().get();
    }

    private void addDistanceEveryLocation(Location loc, List<String> otherLocations, List<Integer> distances) {
        for (int i = 0; i < otherLocations.size(); i++) {
            loc.addDistance(otherLocations.get(i), distances.get(i));
            getLocation(otherLocations.get(i)).addDistance(loc.getName(), distances.get(i));
        }
    }
}
