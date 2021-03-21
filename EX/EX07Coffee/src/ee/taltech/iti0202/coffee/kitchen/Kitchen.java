package ee.taltech.iti0202.coffee.kitchen;

import ee.taltech.iti0202.coffee.drink.Drink;
import ee.taltech.iti0202.coffee.exceptions.NoDrinkException;
import ee.taltech.iti0202.coffee.machine.CoffeeMachine;

import java.util.ArrayList;
import java.util.List;

public class Kitchen {
    private List<CoffeeMachine> coffeeMachines;
    private List<Drink> drinksMade;


    public Kitchen(Drink drinkOrdered, CoffeeMachine machine) throws NoDrinkException {
        coffeeMachines = new ArrayList<>();
        drinksMade = new ArrayList<>();
        coffeeMachines.add(machine);
        drinksMade.add(machine.start(drinkOrdered));
    }

    public void addCoffeeMachine(CoffeeMachine machine) {
        if (!coffeeMachines.contains(machine)) {
            coffeeMachines.add(machine);
        }
    }

    public Drink orderCoffee(Drink drinkOrdered, CoffeeMachine machine) throws NoDrinkException {
        Drink drinkMade = machine.start(drinkOrdered);
        drinksMade.add(drinkMade);
        return drinkMade;
    }
}
