package ee.taltech.iti0202.delivery;

import java.util.ArrayList;
import java.util.List;

public class Action {

    private Location goTo;
    private List<String> deposit;
    private List<String> take;

    public Action(Location location) {
        this.goTo = location;
        deposit = new ArrayList<>();
        take = new ArrayList<>();
    }

    public List<String> getDeposit() {
        return deposit;
    }

    public List<String> getTake() {
        return take;
    }

    public Location getGoTo() {
        return goTo;
    }

    public void addDeposit(String packetName) {
        deposit.add(packetName);
    }

    public void addTake(String packetName) {
        take.add(packetName);
    }
}
