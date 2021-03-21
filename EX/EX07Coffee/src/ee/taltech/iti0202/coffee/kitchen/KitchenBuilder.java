package ee.taltech.iti0202.coffee.kitchen;

import ee.taltech.iti0202.coffee.drink.Drink;
import ee.taltech.iti0202.coffee.exceptions.NoDrinkException;
import ee.taltech.iti0202.coffee.machine.CoffeeMachine;

import java.util.Collections;
import java.util.List;

public class KitchenBuilder {
    private Drink drinkOrdered;
    private CoffeeMachine machine;

    public KitchenBuilder addDrinkOrdered(Drink drinkOrdered) {
        this.drinkOrdered = drinkOrdered;
        return this;
    }

    public KitchenBuilder addMachine(CoffeeMachine machine) {
        this.machine = machine;
        return this;
    }

    public Kitchen createKitchen() throws NoDrinkException {
        return new Kitchen(drinkOrdered, machine);
    }
}