package Schedule;

/**
 * Created by Jakobs on 2015-10-28.
 */
public class Action {

    public String name;
    public String category;
    public float completion;
    public String description;
    public boolean isCompleted;
    public int priority;

    private static int ID = 0;
    private int id;

    public Action(String name, String category, String description, int priority) {
        id = ID++;

        this.name = name;
        this.category = category;
        this.description = description;
        this.priority = priority;

        completion = 0;
        isCompleted = false;
    }

    public int getId() {
        return id;
    }

    public void update(float c) {
        if(c >= 1) {
            completion = 1;
            isCompleted = true;
        } else {
            completion = c;
            isCompleted = false;
        }
    }

}
