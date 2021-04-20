package ee.taltech.iti0202.computerbuilder;

import ee.taltech.iti0202.computerbuilder.components.Component;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private double balance;
    private final List<Component> components = new ArrayList<>();

    /**
     * New customer constructor
     *
     * @param name    customer name
     * @param balance customer balance
     */
    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Component> getComponents() {
        return components;
    }

    /**
     * add components to customer
     *
     * @param component component to be added
     */
    public void addComponents(Component component) {
        if (!components.contains(component)) {
            components.add(component);
        }
    }
}
