package ee.taltech.iti0202.computerstore.database;

import ee.taltech.iti0202.computerstore.components.Component;
import ee.taltech.iti0202.computerstore.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerstore.exceptions.ProductAlreadyExistsException;
import ee.taltech.iti0202.computerstore.exceptions.ProductNotFoundException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public final class Database {
    private static Database database = null;
    private final Map<Integer, Component> components;

    /**
     * Constructor
     */
    private Database() {
        components = new HashMap<>();
    }

    /**
     * get database
     *
     * @return database
     */
    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    /**
     * adds a new component to database
     *
     * @param component component to be added
     * @throws ProductAlreadyExistsException if there is a component with the same id then throws this error
     */
    public void saveComponent(Component component) throws ProductAlreadyExistsException {
        if (containsComponent(component.getId())) {
            throw new ProductAlreadyExistsException();
        } else {
            components.put(component.getId(), component);
        }
    }

    /**
     * Deletes an already existing component out of the database
     *
     * @param id deleted component id
     * @throws ProductNotFoundException if database doesn't have the component then throws this error
     */
    public void deleteComponent(int id) throws ProductNotFoundException {
        if (containsComponent(id)) {
            components.remove(id);
        } else {
            throw new ProductNotFoundException();
        }

    }

    /**
     * increase the amount of components in database
     *
     * @param id     component id
     * @param amount amount to be increased
     * @throws ProductNotFoundException thrown when there isn't a product with such id
     */
    public void increaseComponentStock(int id, int amount) throws ProductNotFoundException {
        if (amount > 0) {
            if (!containsComponent(id)) {
                throw new ProductNotFoundException();
            } else {
                Component component = getComponentById(id);
                component.setAmount(component.getAmount() + amount);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * decrease the amount of components in database
     *
     * @param id     component id
     * @param amount amount to be decreased
     * @throws OutOfStockException      thrown when there isn't enough resources to be reduced
     * @throws ProductNotFoundException thrown when there isn't a product with such id
     */
    public void decreaseComponentStock(int id, int amount) throws OutOfStockException, ProductNotFoundException {
        if (amount > 0) {
            if (containsComponent(id)) {
                Component component = getComponentById(id);
                if (component.getAmount() >= amount) {
                    component.setAmount(component.getAmount() - amount);
                } else {
                    throw new OutOfStockException();
                }
            } else {
                throw new ProductNotFoundException();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Map<Integer, Component> getComponents() {
        return components;
    }

    /**
     * resets the entire database
     */
    public void resetEntireDatabase() {
        Component.setIdCount(-1);
        components.clear();
    }

    /**
     * @param location
     */
    public void saveToFile(String location) {
        Gson gson = new Gson();
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(location))) {
            String jsonString = gson.toJson(database);
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param location
     */
    public void loadFromFile(String location) {
        Gson gson = new Gson();
        Path path = Path.of(location);
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                lines.add(line);
            }
            database = gson.fromJson(lines.get(0), Database.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * check if components contains id
     *
     * @param id component id
     * @return boolean
     */
    private boolean containsComponent(int id) {
        return database.components.containsKey(id);
    }

    /**
     * gets component by id
     *
     * @param id component id
     * @return component
     */
    public Component getComponentById(int id) {
        return components.get(id);
    }
}
