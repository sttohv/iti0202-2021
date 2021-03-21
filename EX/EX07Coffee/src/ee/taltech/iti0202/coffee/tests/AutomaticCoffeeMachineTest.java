package ee.taltech.iti0202.coffee.tests;

import ee.taltech.iti0202.coffee.drink.Drink;
import ee.taltech.iti0202.coffee.exceptions.NoDrinkException;
import ee.taltech.iti0202.coffee.machine.AutomaticCoffeeMachine;
import ee.taltech.iti0202.coffee.machine.CoffeeMachine;
import ee.taltech.iti0202.coffee.water.WaterTank;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AutomaticCoffeeMachineTest {

    @Test
    public void testKnownDrinks() {
        WaterTank tank = new WaterTank(5);
        CoffeeMachine automatic = new AutomaticCoffeeMachine(tank);
        Assert.assertEquals(4, automatic.getKnownDrinks().size());
    }

    @Test
    public void testNoResourcesNeeded() throws NoDrinkException {
        WaterTank tank = new WaterTank(5);
        CoffeeMachine automatic = new AutomaticCoffeeMachine(tank);
        Map<String, Integer> map = new HashMap<>();
        List<Drink> madeCoffees = new ArrayList<>();
        map.put("beans", 1);
        for (int i = 0; i < 3; i++) {
            madeCoffees.add(automatic.start(automatic.getKnownDrinks().get(0)));
        }
        Assert.assertEquals(3, madeCoffees.size());
    }

    @Test
    public void testAutoNoWaterLeft() {
        WaterTank tank = new WaterTank(5);
        CoffeeMachine automatic = new AutomaticCoffeeMachine(tank);
        NoDrinkException.Reason cause = null;

        for (int i = 0; i < 6; i++) {
            try {
                automatic.start(automatic.getKnownDrinks().get(0));
                automatic.emptyTrash();
            } catch (NoDrinkException e) {
                cause = e.getReason();
            }
        }
        Assert.assertEquals(cause, NoDrinkException.Reason.NOT_ENOUGH_WATER);

    }

    @Test
    public void testAutoTrashFull() {
        WaterTank tank = new WaterTank(5);
        CoffeeMachine automatic = new AutomaticCoffeeMachine(tank);
        List<Drink> madeCoffees = new ArrayList<>();
        NoDrinkException.Reason cause = null;
        for (int i = 0; i < 6; i++) {
            try {
                madeCoffees.add(automatic.start(automatic.getKnownDrinks().get(0)));
            } catch (NoDrinkException e) {
                cause = e.getReason();
            }
        }
        Assert.assertEquals(cause, NoDrinkException.Reason.TRASH_FULL);
    }

    @Test
    public void testAddAndUseKnowDrink() throws NoDrinkException {
        WaterTank tank = new WaterTank(5);
        CoffeeMachine automatic = new AutomaticCoffeeMachine(tank);
        Map<String, Integer> newDrinkResources = new HashMap<>();
        newDrinkResources.put("stardust", 1);
        List<Drink> madeCoffees = new ArrayList<>();
        Drink newDrink = new Drink("new", newDrinkResources);
        automatic.addKnownDrink(newDrink);
        madeCoffees.add(automatic.start(newDrink));
        Assert.assertTrue(madeCoffees.contains(newDrink));
    }

    @Test
    public void testStartUnknownDrink() {
        WaterTank tank = new WaterTank(5);
        CoffeeMachine automatic = new AutomaticCoffeeMachine(tank);
        Map<String, Integer> newDrinkResources = new HashMap<>();
        newDrinkResources.put("stardust", 1);
        List<Drink> madeCoffees = new ArrayList<>();
        Drink newDrink = new Drink("new", newDrinkResources);
        NoDrinkException.Reason cause = null;
        try {
            madeCoffees.add(automatic.start(newDrink));
        } catch (NoDrinkException e) {
            cause = e.getReason();
        }
        Assert.assertEquals(NoDrinkException.Reason.UNKNOWN_COFFEE, cause);

    }
}