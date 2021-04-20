package ee.taltech.iti0202.computerbuilder.factory;

import ee.taltech.iti0202.computerbuilder.computer.*;

public class ComputerFactory {
    public Computer assembleComputer(double budget, UseCase useCase, Type type) throws Exception {
        if (type.equals(Type.Desktop)) {
            return new DesktopComputer(useCase);
        } else if (type.equals(Type.Laptop)) {
            return new LaptopComputer(useCase);
        } else {
            throw new Exception();
        }

    }
}
