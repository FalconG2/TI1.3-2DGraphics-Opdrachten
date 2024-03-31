package Lead;

import LinePrep.Line;
import LinePrep.MathPoint;
import Setups.RaySetup;

import java.awt.geom.Point2D;
import java.util.List;

public class CastingEngine extends RaySetup {
    public CastingEngine (List<Line> lines, List<Line> rays){
        super(lines,rays);
    }
    public void update() {
        if (this.pos == null){
            return;
        }
        this.rays.clear();

        for (Line line : this.lines){
            for(Point2D point : line.getPoints()){
                Point2D mousePos = MathPoint.substract(point, this.pos);
                Line direct = new Line(this.pos,point);

                this.rays.add(direct);
                double offset = 0.001;
                for (int i = 0; i <= 1; i++){
                    Line offsetRay = new Line(this.pos, MathPoint.add(this.pos, MathPoint.setAngle(mousePos, MathPoint.getAngle(mousePos) + offset)));
                    this.rays.add(offsetRay);
                    offset *= -1;
                }
            }
        }
        super.update();
    }
}
