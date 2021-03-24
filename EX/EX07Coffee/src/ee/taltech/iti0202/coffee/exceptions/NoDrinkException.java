package ee.taltech.iti0202.coffee.exceptions;

import ee.taltech.iti0202.coffee.machine.CoffeeMachine;

import java.util.logging.Logger;

public class NoDrinkException extends Exception {
    public enum Reason {
        NOT_ENOUGH_WATER,
        NOT_ENOUGH_RESOURCES,
        TRASH_FULL,
        CAPSULE_ALREADY_IN,
        UNKNOWN_COFFEE
    }

    private final CoffeeMachine machine;
    private final Reason reason;
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Constructor
     *
     * @param machine machine what is broken
     * @param reason  reason why can't make a drink
     */
    public NoDrinkException(CoffeeMachine machine, Reason reason) {
        this.machine = machine;
        this.reason = reason;
        log(reason);
    }

    /**
     * log exceptions
     */
    public void log(Reason reason) {
        LOGGER.info(reason.name());
    }

    public Reason getReason() {
        return reason;
    }
}
