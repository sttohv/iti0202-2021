package ee.taltech.iti0202.coffee.water;

import java.util.logging.Logger;

public class WaterTank {
    private int waterLeft;
    private final int waterCapacity;
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Makes a water tank, min 5 units
     *
     * @param water how many units of water can the tank hold
     */
    public WaterTank(int water) {
        if (water > 5) {
            waterCapacity = water;
        } else {
            waterCapacity = 5;
        }
        waterLeft = waterCapacity;
        log("created a water tank that can hold " + waterCapacity + " units");
    }

    /**
     * log info
     * @param message message to be logged
     */
    public void log(String message) {
        LOGGER.info(message);
    }

    /**
     * Checks if water tank is empty
     *
     * @return result
     */
    public boolean isEmpty() {
        log("checked if water tank was empty - " + (waterLeft <= 0));
        return waterLeft <= 0;
    }

    /**
     * refills the tank to it's max capacity
     */
    public void refillTank() {
        log("refilled the water tank");
        waterLeft = waterCapacity;
    }

    /**
     * If the tank isn't empty then uses water
     */
    public void useWater() {
        if (!isEmpty()) {
            log("used water from tank");
            waterLeft--;
        }
    }

    /**
     * get water left
     *
     * @return amount of water left
     */
    public int getWaterLeft() {
        return waterLeft;
    }
}
