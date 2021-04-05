package ee.taltech.iti0202.delivery;

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
    public void setLocation(Location location){
        this.location = location;
    }

    public Integer getStep() {
        return step;
    }

    public void takeAStep() {
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
        if(packets.contains(packet)){
            packets.remove(packet);
        }

    }

    protected void addPacket(Packet packet) {
        if(!packets.contains(packet)){
            packets.add(packet);
        }

    }
}
