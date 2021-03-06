package ee.taltech.iti0202.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Location {
    private List<Packet> packets;
    private String name;
    private List<String> locations;
    private List<Integer> distances;


    public Location(String name) {
        this.name = name;
        packets = new ArrayList<>();
        distances = new ArrayList<>();
        locations = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    Integer getDistanceTo(String name) {
        if (locations.contains(name)) {
            return distances.get(locations.indexOf(name));
        }
        return Integer.MAX_VALUE;

    }

    void addPacket(Packet packet) {
        packets.add(packet);
    }

    Optional<Packet> getPacket(String name) {
        for (Packet packet : packets
        ) {
            if (packet.getName().equals(name)) {
                packets.remove(packet);
                return Optional.of(packet);
            }
        }

        return Optional.empty();
    }
    Optional<Packet> getPacketByName(String name){
        for (Packet packet : packets
        ) {
            if (packet.getName().equals(name)) {

                return Optional.of(packet);
            }
        }

        return Optional.empty();
    }

    public void addDistance(String location, int distance) {
        if (!(location.equals("") && distance == 0)) {
            if (locations.contains(location)) {
                distances.set(locations.indexOf(location), distance);
            } else {
                distances.add(distance);
                locations.add(location);

            }
        }
    }
}
