package ee.taltech.iti0202.coffee.machine;

import ee.taltech.iti0202.coffee.resources.WaterTank;

public class CapsuleCoffeeMachineBuilder {
    private WaterTank tank;

    /**
     * set water tank
     *
     * @param tank tank to be set
     * @return builder
     */
    public CapsuleCoffeeMachineBuilder setTank(WaterTank tank) {
        this.tank = tank;
        return this;
    }

    /**
     * creates a machine that has water tank set to it
     *
     * @return new coffee machine
     */
    public CapsuleCoffeeMachine createCapsuleCoffeeMachine() {
        return new CapsuleCoffeeMachine(tank);
    }
}