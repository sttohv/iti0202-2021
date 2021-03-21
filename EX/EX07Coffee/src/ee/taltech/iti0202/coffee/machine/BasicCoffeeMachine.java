package ee.taltech.iti0202.coffee.machine;

import ee.taltech.iti0202.coffee.water.WaterTank;

public class BasicCoffeeMachine extends CoffeeMachine{
    /**
     * Constructor
     *
     * @param tank where the machine takes its water
     */
    public BasicCoffeeMachine(WaterTank tank) {
        super(tank);
    }
}
