package ee.taltech.iti0202.computerstore.store;

import ee.taltech.iti0202.computerstore.Customer;
import ee.taltech.iti0202.computerstore.components.Component;
import ee.taltech.iti0202.computerstore.database.Database;
import ee.taltech.iti0202.computerstore.exceptions.NotEnoughMoneyException;
import ee.taltech.iti0202.computerstore.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerstore.exceptions.ProductAlreadyExistsException;
import ee.taltech.iti0202.computerstore.exceptions.ProductNotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Store {
    private String name;
    private double balance;
    private double profitMargin;
    private Database database;

    public Store(String name, double balance, double profitMargin) {
        if (profitMargin < 1) {
            throw new IllegalArgumentException();
        } else {
            database = Database.getInstance();
            this.name = name;
            this.balance = balance;
            this.profitMargin = profitMargin;
        }
    }

    public Component purchaseComponent(int id, Customer customer) throws OutOfStockException,
            ProductNotFoundException,
            NotEnoughMoneyException {
        //if there is a component with the same id
        if (database.getComponents().containsKey(id)) {
            Component component = database.getComponentById(id);
            //if the customer doesn't enough money
            if (customer.getBalance() < component.getPrice()) {
                throw new NotEnoughMoneyException();
            }
            //if the customer has enough money
            else{
                database.decreaseComponentStock(component.getId(), 1);
                balance = balance + component.getPrice();
                customer.addComponents(component);
                return component;
            }
        } else {
            throw new ProductNotFoundException();
        }
    }

    public List<Component> getAvailableComponents() {
        return new ArrayList<>(database.getComponents().values())
                .stream()
                .filter(o->o.getAmount()>0)
                .collect(Collectors.toList());
    }

    public List<Component> getComponentsSortedByAmount() {
        return new ArrayList<>(database.getComponents().values())
                .stream()
                .sorted(Comparator.comparingInt(Component::getAmount))
                .collect(Collectors.toList());
    }

    public List<Component> getComponentsSortedByName() {
        return new ArrayList<>(database.getComponents().values())
                .stream()
                .sorted(Comparator.comparing(Component::getName))
                .collect(Collectors.toList());
    }

    public List<Component> getComponentsSortedByPrice() {
        return new ArrayList<>(database.getComponents().values())
                .stream()
                .sorted(Comparator.comparingDouble(Component::getPrice))
                .collect(Collectors.toList());
    }

    public List<Component> filterByType(Component.Type type) {
        return new ArrayList<>(database.getComponents().values())
                .stream()
                .sorted(Comparator.comparing(Component::getType))
                .collect(Collectors.toList());
    }

    public double getInventoryValue() {
        return getAvailableComponents()
                .stream()
                .mapToDouble(component -> component.getAmount() * component.getPrice() * profitMargin)
                .sum();
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

    public double getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(double profitMargin) {
        if (profitMargin < 1) {
            throw new IllegalArgumentException();
        } else {
            this.profitMargin = profitMargin;
        }
    }
}