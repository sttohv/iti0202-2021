package ee.taltech.iti0202.coffee.kitchen;

import ee.taltech.iti0202.coffee.drink.Drink;
import ee.taltech.iti0202.coffee.exceptions.NoDrinkException;
import ee.taltech.iti0202.coffee.machine.CoffeeMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Kitchen {
    private List<CoffeeMachine> coffeeMachines;
    private Map<Drink, CoffeeMachine> drinksOrdered;
    private List<Drink> drinksMade;
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    /**
     * Constructor
     *
     * @param drinkOrdered drink ordered
     * @param machine      machine that has to make it
     */
    public Kitchen(Drink drinkOrdered, CoffeeMachine machine){
        coffeeMachines = new ArrayList<>();
        addCoffeeMachine(machine);
        drinksOrdered = new HashMap<>();
        drinksOrdered.put(drinkOrdered, machine);
        drinksMade = new ArrayList<>();


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
     * @throws NoDrinkException throws an exception when drink cannot be made
     */
    public void orderCoffee(Drink drinkOrdered, CoffeeMachine machine) throws NoDrinkException {
        Drink drinkMade = machine.start(drinkOrdered);
        drinksOrdered.put(drinkMade, machine);
    }

    public void makeOrderedDrinks() throws NoDrinkException {
        for (Drink drink: drinksOrdered.keySet()
             ) {
            drinksOrdered.get(drink).start(drink);
            drinksMade.add(drink);

        }
    }

    public List<Drink> getDrinksMade() {
        return drinksMade;
    }

    public List<CoffeeMachine> getCoffeeMachines() {
        return coffeeMachines;
    }

    public Map<Drink, CoffeeMachine> getDrinksOrdered() {
        return drinksOrdered;
    }
}
