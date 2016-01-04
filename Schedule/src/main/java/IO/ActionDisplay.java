package IO;

import Schedule.Action;

/**
 * Created by Jakobs on 2015-10-29.
 */
public class ActionDisplay {

    Action action;
    UserInterface userInterface;

    public ActionDisplay(UserInterface userInterface, Action action) {
        this.action = action;
        this.userInterface = userInterface;
    }

    public void displayHeader() {
        userInterface.prompt(action.getId() + " | " + action.name + " Cat: " + action.category + " - " + action.completion*100.0f + " %");
    }

    public void displayDetail() {
        userInterface.prompt("ID: " + action.getId());
        userInterface.prompt(action.name);

        userInterface.prompt("Description: " + action.description);
        userInterface.prompt("Category: " + action.category);
        userInterface.prompt("Priority: " + action.priority);
        userInterface.prompt(action.completion*100.0f + "% complete.");
        userInterface.prompt("");
    }
}
