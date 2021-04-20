package ee.taltech.iti0202.computerbuilder.components;

public class Ssd extends Component {
    /**
     * New component constructor
     *
     * @param name              component name
     * @param type              component type
     * @param price             component price
     * @param manufacturer      component manufacturer
     * @param performancePoints component performance points
     * @param powerConsumption  component power consumption
     */
    public Ssd(String name, Type type, double price, String manufacturer, int performancePoints, int powerConsumption) {
        super(name, type, price, manufacturer, performancePoints, powerConsumption);
    }
}
