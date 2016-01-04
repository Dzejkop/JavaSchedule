import Schedule.Action;
import Schedule.ActionContainerReader;
import Schedule.Scheduler;

/**
 * Created by Jakobs on 2015-10-26.
 */
public class Main {

    public static void main(String[] args) {
        SchedulerExercise();
    }

    void LIFOandFIFO() {

    }

    static void SchedulerExercise() {
        Scheduler scheduler = new Scheduler();

        // Test
        ActionContainerReader reader = new ActionContainerReader(scheduler);

        scheduler.addCategory("Normal");
        scheduler.addCategory("Due Tomorrow");
        scheduler.addCategory("Due Yesterday");

        scheduler.addAction(new Action("Task 1", "Normal", "Get all the jews", 2));
        scheduler.addAction(new Action("Operation 'Mundane IT Exercise'", "Due Yesterday", "1. Find Hitler\n2. Kill Hitler\n3. ???\n4. Profit!", 1));
        scheduler.addAction(new Action("Task 3", "Normal", "Get all the jews", 12));
        scheduler.addAction(new Action("Task 4", "Normal", "Get all the jews", 9));

        reader.findActionByName("Task 4").update(0.5f);
        reader.findActionById(1).update(0.75f);

        (scheduler.getAllActions())
                .stream().forEach(Main::displayActionHeader);
    }

    static void displayAction(Action a) {
        System.out.println("###");
        System.out.println("Name: " + a.name);
        System.out.println("Description: ");
        System.out.print(a.description);
        System.out.println();
        System.out.println();
        System.out.println("Priority: " + a.priority);
        System.out.println("Category: " + a.category);
        System.out.println("%: " + a.completion);
        System.out.println();
    }

    static void displayActionHeader(Action a) {
        System.out.println("ID: " + a.getId() + "| " + a.name + " - " + a.completion*100.0f + "%");
    }
}
