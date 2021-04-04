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
        for (int i = 0; i < otherLocations.size(); i++) {
            if(!containsLocation(otherLocations.get(i)) || containsLocation(name)){
                return Optional.empty();
            }
        }
        Location location = new Location(name);
        locations.add(location);
        return Optional.of(location);
    }

    public Optional<Courier> addCourier(String name, String to) {
        if(!containsCourierWithName(name) && containsLocation(to)){
            Courier courier = new Courier(name, new Location(to));
            couriers.add(courier);
            return Optional.of(courier);
        }
        return Optional.empty();
    }

    public boolean giveStrategy(String name, Strategy strategy) {
        return false;
    }

    public void tick() {
    }

    private boolean containsLocation(String location){
        return locations.stream().anyMatch(o -> o.getName().equals(location));
    }

    private boolean containsCourierWithName(String name){
        return couriers.stream().anyMatch(o->o.getName().equals(name));
    }


}
