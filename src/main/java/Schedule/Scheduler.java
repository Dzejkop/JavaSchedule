package Schedule;

import java.util.*;

/**
 * Created by Jakobs on 2015-10-28.
 */
public class Scheduler implements ActionContainer {

    List<Action> actions;
    Set<String> categories;

    public Scheduler() {
        actions = new ArrayList<>();
        categories = new HashSet<>();
    }

    @Override
    public List<Action> getAllActions() {
        return actions;
    }

    @Override
    public List<String> getAllCategories() {
        return new ArrayList<>(categories);
    }

    @Override
    public void addCategory(String cat) {
        categories.add(cat);
    }

    @Override
    public void addAction(Action action) {
        if(!categories.contains(action.category)) return;
        actions.add(action);
    }
}
