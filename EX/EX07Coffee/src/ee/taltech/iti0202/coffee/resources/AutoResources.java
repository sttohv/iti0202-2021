package ee.taltech.iti0202.coffee.resources;

import ee.taltech.iti0202.coffee.drink.Drink;

public class AutoResources extends ResourceStorage{
    /**
     * Constructor
     */
    public AutoResources() {
        super();
    }

    @Override
    public void takeResources(Drink drink) {
    }

    @Override
    public boolean hasResources(Drink drink) {
        return true;
    }

}
