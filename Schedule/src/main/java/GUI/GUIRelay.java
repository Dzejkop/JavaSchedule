package GUI;

import Schedule.Action;

/**
 * Created by Jakobs on 2015-11-25.
 */
public interface GUIRelay {

    void categorySelected(String cat);
    void categoryDeselected(String cat);
    void actionSelected(int actionId);
    void actionDeselected(int actionId);

    void addCategory(String cat);
    void addAction(Action action);

}
