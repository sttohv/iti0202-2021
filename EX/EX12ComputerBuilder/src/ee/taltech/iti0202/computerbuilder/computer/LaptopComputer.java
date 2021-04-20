package ee.taltech.iti0202.computerbuilder.computer;

import java.util.ArrayList;
import java.util.List;

public class LaptopComputer extends Computer {
    public LaptopComputer(UseCase useCase) {
        super(useCase);
    }

    public static List<LaptopComputer> getAllLaptops(UseCase useCase){
        return new ArrayList<>();
    }

    public static LaptopComputer getTheBestFittedLaptop(){return null;}
}
