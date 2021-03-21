package ee.taltech.iti0202.coffee.exceptions;

import ee.taltech.iti0202.coffee.drink.Drink;
import ee.taltech.iti0202.coffee.machine.CoffeeMachine;

public class NoDrinkException extends Exception {
    public enum Reason {
        NOT_ENOUGH_WATER,
        NOT_ENOUGH_RESOURCES,
        TRASH_FULL,
        CAPSULE_ALREADY_IN,
        UNKNOWN_COFFEE
    }

    private CoffeeMachine machine;
    private Reason reason;

    /**
     * Constructor
     *
     * @param machine machine what is broken
     * @param reason  reason why can't make a drink
     */
    public NoDrinkException(CoffeeMachine machine, Reason reason) {
        this.machine = machine;
        this.reason = reason;
    }
}
