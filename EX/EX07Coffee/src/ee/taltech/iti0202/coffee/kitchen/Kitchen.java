package ee.taltech.iti0202.coffee.kitchen;

import ee.taltech.iti0202.coffee.drink.Drink;
import ee.taltech.iti0202.coffee.exceptions.NoDrinkException;
import ee.taltech.iti0202.coffee.machine.CoffeeMachine;

import java.util.ArrayList;
import java.util.List;

public class Kitchen {
    private List<CoffeeMachine> coffeeMachines;
    private List<Drink> drinksMade;


    /**
     * Constructor
     *
     * @param drinkOrdered drink ordered
     * @param machine      machine that has to make it
     * @throws NoDrinkException if drink cannot be made then throws this error
     */
    public Kitchen(Drink drinkOrdered, CoffeeMachine machine) throws NoDrinkException {
        coffeeMachines = new ArrayList<>();
        drinksMade = new ArrayList<>();
        coffeeMachines.add(machine);
        drinksMade.add(machine.start(drinkOrdered));
    }

    /**
     * Adds coffee machine to the kitchen
     *
     * @param machine machine you want to add
     */
    public void addCoffeeMachine(CoffeeMachine machine) {
        if (!coffeeMachines.contains(machine)) {
            coffeeMachines.add(machine);
        }
    }

    /**
     * Order coffee
     *
     * @param drinkOrdered drink ordered
     * @param machine      machine that makes the drink
     * @return drink made (can be different to the one ordered)
     * @throws NoDrinkException throws an exception when drink cannot be made
     */
    public Drink orderCoffee(Drink drinkOrdered, CoffeeMachine machine) throws NoDrinkException {
        Drink drinkMade = machine.start(drinkOrdered);
        drinksMade.add(drinkMade);
        return drinkMade;
    }
}
