package ee.taltech.iti0202.tk4.coffee;

public class Coffee {
    private String flavour;

    /**
     * Constructor
     *
     * @param flavour coffee flavour
     */
    public Coffee(String flavour) {
        this.flavour = flavour;
    }

    public String getFlavour() {
        return flavour;
    }
}
