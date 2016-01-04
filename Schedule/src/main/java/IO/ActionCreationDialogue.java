package IO;

import Schedule.Action;
import Schedule.ActionContainer;

import java.util.List;

/**
 * Created by Jakobs on 2015-10-29.
 */
public class ActionCreationDialogue {

    UserInterface userInterface;
    ActionContainer actionContainer;

    public ActionCreationDialogue(UserInterface userInterface, ActionContainer actionContainer) {
        this.userInterface = userInterface;
        this.actionContainer = actionContainer;
    }

    public Action specifyAction() {

        String name;
        String category;
        String description;
        int priority;

        // Choose name
        name = userInterface.getString("Input the name of your action: ");

        // Choose category

        List<String> options = actionContainer.getAllCategories();
        options.add("Or create a new one.");

        // Display options
        int option = userInterface.chooseOption("Choose one of the following categories: "
                , options.toArray(new String[options.size()]));

        if(option == options.size()-1) {
            // New category
            category = userInterface.getString("Enter the name of your category: ");

            // Add to set
            actionContainer.addCategory(category);

        } else {
            // Old one
            category = options.get(option);
        }

        // Enter description
        description = userInterface.getString("Enter a description: ");

        // Priority
        priority = userInterface.chooseInteger("Enter priority (Any integer): ");

        return new Action(name, category, description, priority);
    }

}
