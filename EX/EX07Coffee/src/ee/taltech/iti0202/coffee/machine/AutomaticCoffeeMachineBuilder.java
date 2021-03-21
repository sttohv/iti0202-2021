package ee.taltech.iti0202.coffee.machine;

import ee.taltech.iti0202.coffee.water.WaterTank;

public class AutomaticCoffeeMachineBuilder {
    private WaterTank tank;

    /**
     * set water tank
     *
     * @param tank tank to be set
     * @return returns builder
     */
    public AutomaticCoffeeMachineBuilder setTank(WaterTank tank) {
        this.tank = tank;
        return this;
    }

    /**
     * Creates a new machine that has a water tank set to it
     *
     * @return new machine
     */
    public AutomaticCoffeeMachine createAutomaticCoffeeMachine() {
        return new AutomaticCoffeeMachine(tank);
    }
}