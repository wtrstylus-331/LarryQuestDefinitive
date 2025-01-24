/*
Program name: Collider.java
Date: Jan 24, 2025
Purpose: Visual collision object from rectangle
 */

package org.group.larryquestdefinitive.control;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Collider extends Rectangle {
    // constructor
    public Collider(int width, int height) {
        super(width, height);
    }

    // method to set color
    public void fill(Color color) {
        super.setFill(color);
    }
} // end of Collider class
