import java.lang.reflect.Array;
import java.util.*;
import java.util.function.UnaryOperator;

/**
 * Created by Jakobs on 2015-10-27.
 */
public class MyList<T> {

    private static final int SIZE_INCREMENT = 10;

    private Class c;
    private T[] items;
    private int itemCount;
    private int arraySize;

    public MyList(Class<T> c) {
        this.c = c;
        arraySize = SIZE_INCREMENT;
        resize(arraySize);
    }

    public void add(T item) {
        if(itemCount >= arraySize) {
            resize(arraySize + SIZE_INCREMENT);
        }

        items[itemCount] = item;
        itemCount++;
    }

    public T get(int index) {
        return null;
    }

    public void remove(int index) {

    }

    public void remove(T object) {

    }

    public void sort() {

    }

    public int size() {
        return itemCount;
    }

    private void resize(int newSize) {

        final T[] newArray = (T[]) Array.newInstance(c, newSize);

    }

    /**
     * Shifts all the object to far left,
     * resizes the array if necessary
     */
    private void shiftToLeft() {
        for(int i = 0 ; i < itemCount; i++) {
            if(items[i] == null) {
                for(int n = i; n < itemCount; n++) {

                }
            }
        }
    }

}