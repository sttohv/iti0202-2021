package ee.taltech.iti0202.coffee.tests;

import ee.taltech.iti0202.coffee.resources.WaterTank;
import org.junit.Assert;
import org.junit.Test;

public class WaterTankTests {

    @Test
    public void testCreateEmptyTankNotEmpty() {
        //minimum capacity always 5
        WaterTank emptyTank = new WaterTank(0);
        WaterTank minusTank = new WaterTank(-5);
        Assert.assertEquals(emptyTank.getWaterLeft(), minusTank.getWaterLeft());

    }

    @Test
    public void testRefillTankFull() {
        WaterTank tank = new WaterTank(5);
        tank.refillTank();
        Assert.assertEquals(5, tank.getWaterLeft());
    }

    @Test
    public void testRefillTankEmpty() {
        WaterTank tank = new WaterTank(5);
        for (int i = 0; i < 5; i++) {
            tank.useWater();
        }
        tank.refillTank();
        Assert.assertEquals(5, tank.getWaterLeft());
    }

    @Test
    public void testRefillTankHalfFull() {
        WaterTank tank = new WaterTank(8);
        for (int i = 0; i < 5; i++) {
            tank.useWater();
        }
        tank.refillTank();
        Assert.assertEquals(8, tank.getWaterLeft());
    }

    @Test
    public void useWaterIfEmpty() {
        WaterTank tank = new WaterTank(5);
        for (int i = 0; i < 7; i++) {
            tank.useWater();
        }
        Assert.assertEquals(0, tank.getWaterLeft());

    }

    @Test
    public void useWaterHalfEmpty() {
        WaterTank tank = new WaterTank(5);
        for (int i = 0; i < 3; i++) {
            tank.useWater();
        }
        Assert.assertEquals(2, tank.getWaterLeft());
    }
}