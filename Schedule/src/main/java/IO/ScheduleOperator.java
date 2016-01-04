package IO;

import Schedule.ActionContainer;
import Schedule.ActionContainerReader;

/**
 * Created by Jakobs on 2015-10-29.
 */
public class ScheduleOperator {

    ActionContainer container;
    UserInterface userInterface;

    private boolean isRunning = true;

    public ScheduleOperator(ActionContainer actionContainer, UserInterface userInterface) {
        container = actionContainer;
        this.userInterface = userInterface;
    }

    void fillWithDefaultCategories() {
        String[] defaultCategories = {"Normal", "Due tomorrow", "ASAP"};

        for(String cat : defaultCategories) {
            container.addCategory(cat);
        }
    }

    public void start() {
        userInterface.prompt("#############");
        userInterface.prompt("Program Start");
        userInterface.prompt("#############");
        userInterface.prompt("");

        fillWithDefaultCategories();

        while(isRunning) {
            mainMenu();
        }
    }

    void mainMenu() {

        String[] options = {"Display all actions headers.", "Display actions from a category, sorted by priority."
                , "Display unfinished actions.", "Update action.", "Create action.", "Delete action", "Add category."
                , "Show categories.", "Exit."};

        int a = userInterface.chooseOption("Choose: ", options);

        switch (a) {
            case 0: {
                displayAllActionHeaders();
                break;
            }

            case 1: {
                displayActionsFromCategorySortedByPriority();
                break;
            }

            case 2: {
                displayUnfinishedActions();
                break;
            }

            case 3: {
                updateAction();
                break;
            }

            case 4: {
                createAction();
                break;
            }

            case 5: {
                deleteAction();
                break;
            }

            case 6: {
                addCategory();
                break;
            }

            case 7: {
                showCategories();
                break;
            }

            case 8: {
                end();
            }

            default: {
                throw new ThreadDeath();
            }
        }
    }

    void displayAllActionHeaders() {
        container.getAllActions()
                .stream().forEach(a -> new ActionDisplay(userInterface, a).displayHeader());

        userInterface.getString("\nPress any key...");
    }

    void displayUnfinishedActions() {
        new ActionContainerReader(container).getUnfinishedActions()
                .stream().forEach(a -> new ActionDisplay(userInterface, a).displayHeader());

        userInterface.getString("\nPress any key...");
    }

    void displayActionsFromCategorySortedByPriority() {
        String cat = userInterface.getString("Which category: ");

        ActionContainerReader a = new ActionContainerReader(container);

        a.getActionsSortedByPriority(a.getActionsByCategory(cat))
                .stream().forEach(action -> new ActionDisplay(userInterface, action).displayDetail());

        userInterface.getString("\nPress any key...");
    }

    void updateAction() {
        int id = userInterface.chooseInteger("Enter the id of the action you wish to update.");
        float c = userInterface.chooseFloat("Enter a value between 0 and 1 describing the completion of specified action.");

        try {
            new ActionContainerReader(container).findActionById(id).update(c);
        } catch (NullPointerException e) {
            userInterface.prompt("The action could not be found.");
        }
    }

    void deleteAction() {
        int id = userInterface.chooseInteger("Enter the id of the action you wish to delete.");
        container.deleteActionById(id);
    }

    void createAction() {
        container.addAction(new ActionCreationDialogue(userInterface, container).specifyAction());
    }

    void showCategories() {
        userInterface.prompt("\nCategories: \n");
        container.getAllCategories().stream().forEach(c -> userInterface.prompt(c));

        userInterface.getString("\nPress any key...");
    }

    void addCategory() {
        String c = userInterface.getString("Name your new category: ");
        container.addCategory(c);
    }

    void end() {
        userInterface.prompt("###########");
        userInterface.prompt("Program End");
        userInterface.prompt("###########");
        userInterface.prompt("");

        isRunning = false;
    }

}
