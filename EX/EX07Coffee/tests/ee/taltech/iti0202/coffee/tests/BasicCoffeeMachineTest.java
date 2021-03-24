package ee.taltech.iti0202.coffee.tests;

import ee.taltech.iti0202.coffee.drink.Drink;
import ee.taltech.iti0202.coffee.exceptions.NoDrinkException;
import ee.taltech.iti0202.coffee.machine.BasicCoffeeMachine;
import ee.taltech.iti0202.coffee.machine.CoffeeMachine;
import ee.taltech.iti0202.coffee.resources.WaterTank;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class BasicCoffeeMachineTest {
    @Test
    public void testMakingDrinkWithoutEnoughResources() {
        WaterTank tank = new WaterTank(5);
        CoffeeMachine basic = new BasicCoffeeMachine(tank);
        NoDrinkException.Reason cause = null;

        Map<String, Integer> resourcesNeeded = new HashMap<>();
        resourcesNeeded.put("something", 1);
        Drink drink = new Drink("something", resourcesNeeded);

        try {
            basic.start(drink);
        } catch (NoDrinkException e) {
            cause = e.getReason();
        }

        Assert.assertEquals(NoDrinkException.Reason.NOT_ENOUGH_RESOURCES, cause);

    }

    @Test
    public void testAddResourcesManyTimes() {
        WaterTank tank = new WaterTank(5);
        CoffeeMachine basic = new BasicCoffeeMachine(tank);


        basic.getStorage().addResource("something", 1);
        basic.getStorage().addResource("something", 1);


        Assert.assertEquals(2, basic.getStorage().getResources().get("something").intValue());
    }

    @Test
    public void testMakeDrink() throws NoDrinkException {
        WaterTank tank = new WaterTank(5);
        CoffeeMachine basic = new BasicCoffeeMachine(tank);

        Map<String, Integer> resourcesNeeded = new HashMap<>();
        resourcesNeeded.put("something", 1);
        Drink drink = new Drink("something", resourcesNeeded);

        basic.getStorage().addResource("something", 1);
        basic.addKnownDrink(drink);
        basic.start(drink);
        Assert.assertEquals("something", drink.getName());
    }


}