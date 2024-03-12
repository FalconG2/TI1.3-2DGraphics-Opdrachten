
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Point2D;

public class Block {
    private Shape shape;
    private Point2D position;
    private Color color;
    public Block(Shape shape, Point2D position, Color color){
        this.shape = shape;
        this.position = position;
        this.color = color;
        Area area = new Area(shape);
    }
}
