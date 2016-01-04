package Schedule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public void deleteCategory(String cat) { categories.remove(cat); }

    @Override
    public void addAction(Action action) {
        if(!categories.contains(action.category)) return;
        actions.add(action);
    }

    @Override
    public void deleteActionById(int id) {
        actions.removeIf(a -> a.getId() == id);
    }
}
