package ee.taltech.iti0202.computerbuilder.computer;

import java.util.List;

public abstract class Computer {
    private UseCase usecase;

    public Computer(UseCase useCase){
        this.usecase = useCase;
    }


}
