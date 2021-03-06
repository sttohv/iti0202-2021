package ee.taltech.iti0202.computerbuilder.store;

import ee.taltech.iti0202.computerbuilder.Customer;
import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.computer.Type;
import ee.taltech.iti0202.computerbuilder.computer.UseCase;
import ee.taltech.iti0202.computerbuilder.database.Database;
import ee.taltech.iti0202.computerbuilder.exceptions.NotEnoughMoneyException;
import ee.taltech.iti0202.computerbuilder.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductNotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Store {
    private String name;
    private double balance;
    private double profitMargin;
    private Database database;

    /**
     * New store constructor
     *
     * @param name         store name
     * @param balance      store balance
     * @param profitMargin store profit margin
     */
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

    public void Order(double budget, Type type, UseCase useCase){

    }


    /**
     * Purchase component if possible
     *
     * @param id       component id
     * @param customer customer that wants to purchase it
     * @return component to be purchased
     * @throws OutOfStockException      if store doesn't have enough of the component
     * @throws ProductNotFoundException if store doesn't have component
     * @throws NotEnoughMoneyException  if the customer doesn't have enough money
     */
    public Component purchaseComponent(int id, Customer customer) throws OutOfStockException,
            ProductNotFoundException,
            NotEnoughMoneyException {
        //if there is a component with the same id
        if (database.getComponents().containsKey(id)) {
            Component component = database.getComponentById(id);
            double componentRealPrice = component.getPrice() * profitMargin;
            if (customer.getBalance() < componentRealPrice) {
                throw new NotEnoughMoneyException();
            } else {
                database.decreaseComponentStock(component.getId(), 1);
                balance = balance + componentRealPrice;
                customer.setBalance(customer.getBalance() - componentRealPrice);
                customer.addComponents(component);
                return component;
            }
        } else {
            throw new ProductNotFoundException();
        }
    }

    /**
     * Returns components that are in stock
     *
     * @return List of components
     */
    public List<Component> getAvailableComponents() {
        return new ArrayList<>(database.getComponents().values())
                .stream()
                .filter(o -> o.getAmount() > 0)
                .collect(Collectors.toList());
    }

    /**
     * Get components sorted by amount in ascending order
     *
     * @return List of components
     */
    public List<Component> getComponentsSortedByAmount() {
        return new ArrayList<>(database.getComponents().values())
                .stream()
                .sorted(Comparator.comparingInt(Component::getAmount))
                .collect(Collectors.toList());
    }

    /**
     * Return a list of components sorted by name in ascending order.
     *
     * @return List of components
     */
    public List<Component> getComponentsSortedByName() {
        return new ArrayList<>(database.getComponents().values())
                .stream()
                .sorted(Comparator.comparing(Component::getName))
                .collect(Collectors.toList());
    }

    /**
     * Return a list of components sorted by price in ascending order.
     *
     * @return List of components
     */
    public List<Component> getComponentsSortedByPrice() {
        return new ArrayList<>(database.getComponents().values())
                .stream()
                .sorted(Comparator.comparingDouble(Component::getPrice))
                .collect(Collectors.toList());
    }

    /**
     * Return a list of components filtered by type (order does not matter).
     *
     * @param type component type to be filtered by
     * @return found components
     */
    public List<Component> filterByType(Component.Type type) {
        return new ArrayList<>(database.getComponents().values())
                .stream()
                .filter(o -> o.getType().equals(type))
                .collect(Collectors.toList());
    }

    /**
     * Return the total sum of stores inventory value including the profit margin.
     *
     * @return sum of value
     */
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
