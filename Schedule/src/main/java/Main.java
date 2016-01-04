import Schedule.Action;
import Schedule.Scheduler;

/**
 * Created by Jakobs on 2015-10-26.
 */
public class Main {

    public static void main(String[] args) {

        Scheduler scheduler = new Scheduler();

        String[] defaultCategories = {"Normal", "Due tomorrow", "ASAP"};

        for(String cat : defaultCategories) {
            scheduler.addCategory(cat);
        }

        scheduler.addAction(new Action("Lol", "Normal", "Kill all jews", 1));

        new UserGUI(scheduler);
    }
}
