package Inventory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jakobs on 2015-10-26.
 */
public abstract class Stack {

    public Stack() {
        items = new ArrayList<Item>();
    }

    List<Item> items;

    public abstract Item get();
    public abstract void add(Item item);
}
