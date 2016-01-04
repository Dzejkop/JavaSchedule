package Inventory;

/**
 * Created by Jakobs on 2015-10-26.
 */
public class LIFOStack extends Stack {

    public LIFOStack() {
        super();
    }

    @Override
    public void add(Item item) {
        items.add(item);
    }

    @Override
    public Item get() {
        Item i = items.get(0);
        items.remove(0);
        return i;
    }
}
