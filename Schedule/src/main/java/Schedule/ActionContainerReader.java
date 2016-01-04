package Schedule;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jakobs on 2015-10-28.
 */
public class ActionContainerReader {

    ActionContainer actionContainer;

    public ActionContainerReader(ActionContainer container) {
        this.actionContainer = container;
    }

    public List<Action> getActionsByCategory(String cat) {
        return actionContainer.getAllActions()
                .stream().filter(a -> a.category.equals(cat))
                .collect(Collectors.toList());
    }

    public List<Action> getUnfinishedActions() {
        return actionContainer.getAllActions()
                .stream().filter(a -> a.completion < 1)
                .collect(Collectors.toList());
    }

    public List<Action> getFinishedActions() {
        return actionContainer.getAllActions()
                .stream().filter(a -> a.completion == 1)
                .collect(Collectors.toList());
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
