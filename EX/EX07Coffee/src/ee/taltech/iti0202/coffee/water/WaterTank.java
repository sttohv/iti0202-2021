package ee.taltech.iti0202.coffee.water;

import ee.taltech.iti0202.coffee.exceptions.NoDrinkException;

public class WaterTank {
    private int waterLeft;
    private final int waterCapacity;

    /**
     * Makes a water tank, min 5 units
     *
     * @param water how many units of water can the ank hold
     */
    public WaterTank(int water) {
        if (water > 5) {
            waterCapacity = water;
        } else {
            waterCapacity = 5;
        }
        waterLeft = waterCapacity;
    }

    /**
     * Checks if water tank is empty
     *
     * @return result
     */
    public boolean isEmpty() {
        return waterLeft <= 0;
    }

    /**
     * refills the tank to it's max capacity
     */
    public void refillTank() {
        waterLeft = waterCapacity;
    }

    /**
     * If the tank isn't empty then uses water
     */
    public void useWater() {
        if (!isEmpty()) {
            waterLeft--;
        }
        //Excpetion mis ütleb, et vee tank on tühi
    }

    public int getWaterLeft() {
        return waterLeft;
    }
}
