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
        if (otherLocations.size() != distances.size() || otherLocations.size() != locations.size() || containsLocation(name)) {
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
        for (Courier courier : couriers
        ) {
            if (courier.getLocation().isPresent()) {
                Action action = courier.getStrategy().getAction();
                Location locNow = courier.getLocation().get();
                System.out.println("old loc" + locNow.getName());
                //pane pakid maha, KUI ON PAKKE
                if (!courier.getPackets().isEmpty()) {
                    leaveDeposit(courier, action);
                }
                //v??ta uued pakid peale
                if (action.getTake().size() > 0) {
                    takePackage(courier, action);
                }
                //uus asukoht
                Location newLoc = action.getGoTo();
                System.out.println("new loc" + newLoc.getName());
                int newStep = newLoc.getDistanceTo(locNow.getName());
                System.out.println("new step is: " + newStep);
                courier.setStep(newStep);
                courier.setLocation(newLoc);
                courier.takeAStep();


            } else {
                courier.takeAStep();
            }
        }

    }

    private void leaveDeposit(Courier courier, Action action) {
        for (String packet : action.getDeposit()
        ) {

            courier.removePacket(courier.getPacket(packet).get());

        }
    }

    private void takePackage(Courier courier, Action action) {
        for (String packet : action.getTake()
        ) {
            if (courier.getLocation().isPresent()) {

                Location loc = courier.getLocation().get();
                System.out.println(loc.getName());


                Packet packet1 = loc.getPacket(packet).get();
                courier.addPacket(packet1);
                System.out.println(packet1.getName());

            } else {
                System.out.println("courieril pole locationit");
            }
        }
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
