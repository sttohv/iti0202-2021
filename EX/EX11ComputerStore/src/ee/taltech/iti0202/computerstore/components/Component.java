package ee.taltech.iti0202.computerstore.components;

import java.util.ArrayList;
import java.util.List;

public class Component {
    private int id;
    private String name;
    private Type type;
    private double price;
    private int amount = 1;
    private String manufacturer;
    private int performancePoints;
    private int powerConsumption;
    private static int idCount = -1;

    public enum Type {
        CPU, GPU, RAM, MOTHERBOARD, HDD, SSD, PSU, KEYBOARD, TOUCHPAD, SCREEN, BATTERY, FAN
    }

    public Component(String name, Type type, double price, String manufacturer, int performancePoints, int powerConsumption) {
        this.id = getAndIncrementNextId();
        this.name = name;
        this.type = type;
        this.price = price;
        this.manufacturer = manufacturer;
        this.performancePoints = performancePoints;
        this.powerConsumption = powerConsumption;
    }
    public static int getAndIncrementNextId() {
        idCount++;
        return idCount;
    }
    public static void setIdCount(int newValue){
        idCount = newValue;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}