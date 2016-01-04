package Schedule;

import java.util.List;

/**
 * Created by Jakobs on 2015-10-28.
 */
public interface ActionContainer {

    List<Action> getAllActions();
    List<String> getAllCategories();

    void addCategory(String cat);
    void deleteCategory(String cat);
    void addAction(Action action);
    void deleteActionById(int id);


}
