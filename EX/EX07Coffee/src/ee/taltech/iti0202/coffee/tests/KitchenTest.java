package ee.taltech.iti0202.coffee.tests;

import ee.taltech.iti0202.coffee.drink.Drink;
import ee.taltech.iti0202.coffee.exceptions.NoDrinkException;
import ee.taltech.iti0202.coffee.kitchen.Kitchen;
import ee.taltech.iti0202.coffee.machine.AutomaticCoffeeMachineBuilder;
import ee.taltech.iti0202.coffee.machine.CapsuleCoffeeMachineBuilder;
import ee.taltech.iti0202.coffee.machine.CoffeeMachine;
import ee.taltech.iti0202.coffee.water.WaterTank;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class KitchenTest {

    @Test
    public void makeKitchenWithCapsuleMachineBuilder() throws NoDrinkException {
        WaterTank tank = new WaterTank(5);
        CoffeeMachine capsule = new CapsuleCoffeeMachineBuilder().setTank(tank).createCapsuleCoffeeMachine();
        List<CoffeeMachine> list = new ArrayList<>();
        list.add(capsule);

        Drink water = new Drink("water", new HashMap<>());
        Kitchen kitchen = new Kitchen(water, capsule);
        kitchen.orderCoffee(water, capsule);

        Assert.assertEquals(capsule, kitchen.getDrinksOrdered().get(water));
        Assert.assertEquals(list.get(0), kitchen.getCoffeeMachines().get(0));
    }

    @Test
    public void testMakeOrderedDrinks() throws NoDrinkException {
        WaterTank tank = new WaterTank(5);
        CoffeeMachine automatic = new AutomaticCoffeeMachineBuilder().setTank(tank).createAutomaticCoffeeMachine();

        CoffeeMachine capsule = new CapsuleCoffeeMachineBuilder().setTank(tank).createCapsuleCoffeeMachine();

        Drink water = new Drink("water", new HashMap<>());
        Kitchen kitchen = new Kitchen(water, automatic);

        capsule.addKnownDrink(water);
        automatic.addKnownDrink(water);

        kitchen.orderCoffee(water, capsule);
        kitchen.makeOrderedDrinks();

        Assert.assertEquals(2, kitchen.getDrinksMade().size());
    }


}