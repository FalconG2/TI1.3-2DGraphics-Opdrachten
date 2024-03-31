package Setups;

import Interfaces.Drawable;
import Interfaces.Updatable;
import Lead.RenderEngine;
import LinePrep.Line;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

abstract public class RenderSetup implements Drawable, Updatable {
    protected List<Line> rays;
    protected List<Shape> drawing;
    public RenderSetup(List<Line> rays){
        this.rays = rays;
        this.drawing = new ArrayList<>();
    }
}
