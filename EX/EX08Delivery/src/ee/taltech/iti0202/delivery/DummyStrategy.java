package ee.taltech.iti0202.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DummyStrategy implements Strategy{
    private List<Action> actions;
    private int index;
    public DummyStrategy(List<Action> actions) {
        this.actions = actions;
        index = actions.size();
    }

    @Override
    public Action getAction() {
        index--;
        return actions.get(index);
    }
}