package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jakobs on 2015-11-25.
 */
public class CategoryButton extends JButton {

    private GUIRelay guiRelay;
    private String category;
    private boolean isSelected = false;

    public CategoryButton(String category, GUIRelay gui) {

        super(category);
        this.guiRelay = gui;
        this.category = category;

        addActionListener(a -> onClick());

        checkColor();
    }

    void onClick() {

        isSelected = !isSelected;

        if(isSelected) guiRelay.categorySelected(category);
        else guiRelay.categoryDeselected(category);

        checkColor();
    }

    void checkColor() {
        if(isSelected) setBackground(Color.GREEN);
        else setBackground(Color.WHITE);
    }
}
