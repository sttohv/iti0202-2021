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

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }
}
