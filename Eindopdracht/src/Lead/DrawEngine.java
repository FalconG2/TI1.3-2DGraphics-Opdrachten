package Lead;

import LinePrep.Line;
import Setups.RenderSetup;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.util.List;

public class DrawEngine extends RenderSetup {
    public DrawEngine(List<Line> rays){
        super(rays);
    }
    public void update(){
        this.drawing.clear();

        int count = 0;
        for (Line ray : rays){
            Line next = this.rays.get((count + 1) % this.rays.size());

            Polygon polygon = new Polygon();
            polygon.addPoint((int) ray.getStartingPos().getX(), (int) ray.getStartingPos().getY());
            polygon.addPoint((int) ray.getEndingPos().getX(), (int) ray.getEndingPos().getY());
            polygon.addPoint((int) next.getEndingPos().getX(), (int) next.getEndingPos().getY());
            polygon.addPoint((int) next.getStartingPos().getX(), (int) next.getStartingPos().getY());

            this.drawing.add(polygon);
            count++;
        }
    }
    public void draw(FXGraphics2D graphics2D){
        graphics2D.setColor(Color.BLACK);
        for (Shape shape : drawing){
            graphics2D.fill(shape);
        }
    }

}
