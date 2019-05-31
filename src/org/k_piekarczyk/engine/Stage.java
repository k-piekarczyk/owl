package org.k_piekarczyk.engine;

import javax.swing.*;
import java.awt.*;

public class Stage extends JPanel {
    private int width;
    private int height;

    public Stage(int width, int height) {
        setSize(width, height);
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
    }

}
