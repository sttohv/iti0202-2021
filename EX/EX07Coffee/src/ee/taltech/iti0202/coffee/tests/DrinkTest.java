package ee.taltech.iti0202.coffee.tests;

import ee.taltech.iti0202.coffee.drink.Drink;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class DrinkTest {
    @Test
    public void testMakeDrink() {
        Map<String, Integer> resources = new HashMap<>();
        resources.put("beans", 1);
        Drink drink = new Drink("coffee", resources);
        Assert.assertEquals("coffee", drink.getName());
        Assert.assertEquals(1, drink.getResourcesNeeded().get("beans").intValue());
    }

}