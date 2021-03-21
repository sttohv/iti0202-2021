package ee.taltech.iti0202.coffee.tests;

import ee.taltech.iti0202.coffee.drink.Drink;
import ee.taltech.iti0202.coffee.exceptions.NoDrinkException;
import ee.taltech.iti0202.coffee.machine.CapsuleCoffeeMachine;
import ee.taltech.iti0202.coffee.machine.CoffeeMachine;
import ee.taltech.iti0202.coffee.water.WaterTank;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CapsuleCoffeeMachineTest {
    private final Drink water = new Drink("water", new HashMap<>());

    @Test
    public void testNoCapsuleEntered() throws NoDrinkException {
        WaterTank tank = new WaterTank(5);
        CoffeeMachine capsuleMachine = new CapsuleCoffeeMachine(tank);

        Map<String, Integer> map = new HashMap<>();
        Drink result;

        map.put("capsuleCoffee", 1);
        Drink capsuleCoffee = new Drink("capsuleCoffee", map);


        result = capsuleMachine.start(capsuleCoffee);

        Assert.assertEquals(water.getName(), result.getName());


    }

    @Test
    public void testCapsuleEmpty() throws NoDrinkException {
        WaterTank tank = new WaterTank(5);
        CapsuleCoffeeMachine capsuleMachine = new CapsuleCoffeeMachine(tank);

        Map<String, Integer> map = new HashMap<>();
        List<Drink> result = new ArrayList<>();

        map.put("capsuleCoffee", 1);
        Drink capsuleCoffee = new Drink("capsuleCoffee", map);
        capsuleMachine.addResource("capsulecoffee", 1);
        capsuleMachine.addCapsuleToMachine("capsuleCoffee");

        for (int i = 0; i < 2; i++) {
            result.add(capsuleMachine.start(capsuleCoffee));
        }

        Assert.assertEquals(water.getName(), result.get(result.size() - 1).getName());
    }

    @Test
    public void testAddCapsuleWhenCapsuleAlreadyIn(){
        WaterTank tank = new WaterTank(5);
        CapsuleCoffeeMachine capsuleMachine = new CapsuleCoffeeMachine(tank);

        capsuleMachine.addResource("Capsulecoffee", 2);

        NoDrinkException.Reason cause = null;
        try {
            capsuleMachine.addCapsuleToMachine("Capsulecoffee");
            capsuleMachine.addCapsuleToMachine("Capsulecoffee");
        } catch (NoDrinkException e) {
            cause = e.getReason();
        }

        Assert.assertEquals(NoDrinkException.Reason.CAPSULE_ALREADY_IN, cause);
    }

    @Test
    public void testAddCapsuleWhenResourcesDoesntHaveCapsule(){
        WaterTank tank = new WaterTank(5);
        CapsuleCoffeeMachine capsuleMachine = new CapsuleCoffeeMachine(tank);

        NoDrinkException.Reason cause = null;

        try {
            capsuleMachine.addCapsuleToMachine("capsulecoffee");
        } catch (NoDrinkException e) {
            cause = e.getReason();
        }

        Assert.assertEquals(NoDrinkException.Reason.NOT_ENOUGH_RESOURCES, cause);
    }

    @Test
    public void testAddCapsuleWithDifferentCase(){
        WaterTank tank = new WaterTank(5);
        CapsuleCoffeeMachine capsuleMachine = new CapsuleCoffeeMachine(tank);

        capsuleMachine.addResource("CapSuleCoFfEe", 2);

        try {
            capsuleMachine.addCapsuleToMachine("capsulecoffee");
            capsuleMachine.removeCapsule();
            capsuleMachine.addCapsuleToMachine("CAPSULECOFFEE");
        } catch (NoDrinkException e) {
            e.printStackTrace();
        }
        Assert.assertEquals("capsulecoffee", capsuleMachine.getCapsuleName());


    }


}