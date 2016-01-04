package Schedule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Jakobs on 2015-10-28.
 */
public class ActionContainerReader {

    ActionContainer actionContainer;

    public ActionContainerReader(ActionContainer container) {
        this.actionContainer = container;
    }

    public List<Action> getActionsByCategory(String cat) {
        List<Action> l = actionContainer.getAllActions();
        Stream<Action> s = l.stream().filter(e -> e.category.equals(cat));
        return s.collect(Collectors.toList());
    }

    public List<Action> getActionsSortedByPriority(List<Action> actions) {
        actions.sort((a, b) -> b.priority - a.priority);
        return actions;
    }

    public Action findActionByName(String name) {
        for(Action a : actionContainer.getAllActions()) {
            if(a.name.equals(name)) return a;
        }
        return null;
    }

    public Action findActionById(int id) {
        for(Action a : actionContainer.getAllActions()) {
            if(a.getId()==id) return a;
        }
        return null;
    }
}
