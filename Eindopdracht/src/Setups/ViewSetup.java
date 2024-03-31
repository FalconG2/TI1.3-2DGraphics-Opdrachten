package Setups;

import Interfaces.Drawable;
import Interfaces.View;
import Lead.RenderEngine;
import LinePrep.Line;
import javafx.scene.layout.BorderPane;
import org.jfree.fx.ResizableCanvas;


import java.util.ArrayList;
import java.util.List;

abstract public class ViewSetup implements View, Drawable {
    protected ResizableCanvas canvas;
    protected BorderPane borderPane;
    protected List<Line> lines;
    protected List<Line> mapLines;
    protected List<Line> rays;
    protected RenderEngine renderingEngine;
    public ViewSetup(){
        this.borderPane = new BorderPane();
        this.canvas = new ResizableCanvas(this::draw, borderPane);
        this.lines = new ArrayList<>();
        this.rays = new ArrayList<>();
        this.mapLines = new ArrayList<>();
        init();
        initEngine();
    }
    abstract public void init();
    abstract public void initEngine();


}
