package ee.taltech.iti0202.coffee.machine;

import ee.taltech.iti0202.coffee.drink.Drink;
import ee.taltech.iti0202.coffee.water.WaterTank;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class AutomaticCoffeeMachine extends CoffeeMachine {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    /**
     * Makes a coffee machine that has always enough resources
     *
     * @param tank tank where the machine gets its water
     */
    public AutomaticCoffeeMachine(WaterTank tank) {
        super(tank);
        changeKnownDrinks();
    }

    @Override
    protected void takeResources(Drink drink){}

    @Override
    protected boolean hasResources(Drink drink) {
        return true;
    }

    /**
     * The automatic machine knows how to do black coffee, hot chocolate,
     * cappuccino and latte
     * This method it later called out in the constructor
     */
    private void changeKnownDrinks() {
        log("teached machine how to black coffee, hot chocolate, cappuccino and latte");
        Map<String, Integer> coffeeResources = new HashMap<>();
        coffeeResources.put("beans", 1);
        knownDrinks.add(new Drink("coffee", coffeeResources));

        Map<String, Integer> hotChocoResources = new HashMap<>();
        hotChocoResources.put("cacao", 1);
        hotChocoResources.put("milk", 1);
        knownDrinks.add(new Drink("hot chocolate", hotChocoResources));

        coffeeResources.put("milk", 1);
        knownDrinks.add(new Drink("cappuccino", coffeeResources));

        coffeeResources.put("milk", 2);
        knownDrinks.add(new Drink("latte", coffeeResources));

    }
}
