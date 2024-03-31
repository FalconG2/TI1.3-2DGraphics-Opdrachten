package Lead;

import LinePrep.Line;
import Setups.Map;
import Setups.MapSetup;
import Setups.ViewSetup;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.List;

public class MouseFollowDisplay extends ViewSetup {
    public void init(){
        Map map = new Map();
        this.lines.addAll(map.getLines());
        this.mapLines.addAll(map.getMapLines());
    }
    public void initEngine(){
        this.renderingEngine = new RenderEngine(this.lines, this.rays);
        this.renderingEngine.update();
    }

    @Override
    public Node getNode() {
        this.borderPane.setCenter(this.canvas);
        this.canvas.setOnMouseMoved(this::mouseMoved);
        draw(new FXGraphics2D(this.canvas.getGraphicsContext2D()));
        return this.borderPane;
    }

    @Override
    public String getName() {
        return null;
    }
    private void mouseMoved(MouseEvent mouseEvent) {
        this.renderingEngine.setPosition(new Point2D.Double(mouseEvent.getX(), mouseEvent.getY()));
        this.renderingEngine.update();
        draw(new FXGraphics2D(this.canvas.getGraphicsContext2D()));
    }
    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0,0,(int)canvas.getWidth(), (int)canvas.getHeight());

        this.renderingEngine.draw(graphics);

        for (Line line : lines){
            line.draw(graphics);
        }
        for (Line line : mapLines){
            line.drawMap(graphics);
        }
    }

}
