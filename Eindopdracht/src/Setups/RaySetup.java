package Setups;

import Interfaces.Updatable;
import LinePrep.Line;
import LinePrep.MathPoint;

import java.awt.geom.Point2D;
import java.util.List;

abstract public class RaySetup implements Updatable {
    protected List<Line> lines;
    protected List<Line> rays;
    protected Point2D pos;
    protected double angle;

    public RaySetup(List<Line> lines, List<Line> rays) {
        this.lines = lines;
        this.rays = rays;
        this.angle = 0;
    }

    public void update() {
        checkrays();
        sortrays();
    }
    // to check the rays and their collision the following method is implemented to do so.
    public void checkrays() {
        for (Line ray : this.rays) {
            double closest = 1000000;
            Point2D endPoint = ray.getEndingPos();

            for (Line line : lines) {
                Point2D intersect = getIntersection(ray, line);

                if (intersect != null) {
                    double dis = ray.getStartingPos().distance(intersect);

                    if (dis < closest) {
                        closest = dis;
                        endPoint = intersect;
                    }
                }
            }
            ray.setEndingPos(endPoint);
        }
    }
    // this part is from a site i received this information from Jayson
    // the site is: https://ncase.me/sight-and-light/
    public Point2D getIntersection(Line x, Line y){
        if (MathPoint.normalize(x.getDirec()).equals(MathPoint.normalize(y.getDirec()))){
            return null;
        }
        double rayPx = x.getStartingPos().getX();
        double rayDx = x.getDirec().getX();
        double rayPy = x.getStartingPos().getY();
        double rayDy = x.getDirec().getY();

        double segmentPx = y.getStartingPos().getX();
        double segmentDx = y.getDirec().getX();
        double segmentPy = y.getStartingPos().getY();
        double segmentDy = y.getDirec().getY();

        double T2 = (rayDx * (segmentPy - rayPy) + rayDy * (rayPx - segmentPx)) / (segmentDx * rayDy - segmentDy * rayDx);

        double T1 = (segmentPx + segmentDx * T2 - rayPx) / rayDx;
        if (T1 > 0 && (T2 > 0 && T2 < 1)){
            return MathPoint.add(x.getStartingPos(), MathPoint.scale(x.getDirec(),T1));
        }
        return null;
    }
    private void sortrays(){
        rays.sort((r1,r2) -> {
            double relAngle1 = adjustAngle(MathPoint.getAngle(r1.getDirec()) - angle);
            double relAngle2 = adjustAngle(MathPoint.getAngle(r2.getDirec()) - angle);
            return Double.compare(relAngle1,relAngle2);
        });
    }
    private double adjustAngle(double angle){
        while (angle > Math.PI){
            angle -= 2 * Math.PI;
        }
        while (angle < -Math.PI){
            angle += 2 * Math.PI;
        }
        return angle;
    }
    public void setPos(Point2D pos){
        this.pos = pos;
    }

}
