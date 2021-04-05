package ee.taltech.iti0202.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Courier {
    private String name;
    private Location location;
    private List<Packet> packets;
    private Strategy strategy;
    private Integer step;

    public Courier(String name, Location location) {
        this.location = location;
        this.name = name;
        step = 0;
        strategy = null;
        packets = new ArrayList<>();
    }

    public Optional<Location> getLocation() {
        if (step == 0) {
            return Optional.of(location);
        } else {
            return Optional.empty();
        }
    }

    public String getName() {
        return name;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Integer getStep() {
        return step;
    }

    public void takeAStep() {
        System.out.println("steps left:" + step);
        this.step = step - 1;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    protected Packet getPacketByName(String name, List<Packet> packets) {
        return packets.stream().filter(o -> o.getName().equals(name)).findFirst().orElse(null);
    }

    public List<Packet> getPackets() {
        return packets;
    }

    protected void removePacket(Packet packet) {
        if (packets.contains(packet)) {
            packets.remove(packet);
        }

    }

    protected void addPacket(Packet packet) {
        if (!packets.contains(packet)) {
            packets.add(packet);
        }
        else{
            System.out.println("mingi jama add packetis");
        }

    }

    Optional<Packet> getPacket(String name) {
        for (Packet packet : packets
        ) {
            if (packet.getName().equals(name)) {

                return Optional.of(packet);
            }
        }
        return Optional.empty();
    }
}
