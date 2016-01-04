package Inventory;

/**
 * Created by Jakobs on 2015-10-26.
 */
public class FIFOStack extends Stack {

    public FIFOStack() {
        super();
    }

    @Override
    public void add(Item item) {
        items.add(item);
    }

    @Override
    public Item get() {
        Item i = items.get(items.size()-1);
        items.remove(items.size()-1);
        return i;
    }
}
