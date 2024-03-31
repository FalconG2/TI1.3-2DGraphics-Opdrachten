package LinePrep;

import java.awt.geom.Point2D;

public final class MathPoint {

    public static Point2D add(Point2D point1, Point2D point2){
        return new Point2D.Double(point1.getX() + point2.getX(), point1.getY() + point2.getY());
    }
    public static Point2D substract(Point2D point1, Point2D point2){
        return new Point2D.Double(point1.getX() - point2.getX(), point1.getY() - point2.getY());
    }
    public static Point2D normalize(Point2D point1){
        return new Point2D.Double(point1.getX()/getLength(point1), point1.getY()/getLength(point1));
    }
    public static double getLength(Point2D point){
        return Math.sqrt(Math.pow(point.getX(), 2) + Math.pow(point.getY(), 2));
    }
    public static Point2D scale(Point2D point, double scale){
        return new Point2D.Double(point.getX() * scale, point.getY() * scale);
    }
    public static Point2D setAngle(Point2D point, double angle){
        double length = getLength(point);
        double newPosX = length * Math.cos(angle);
        double newPosY = length * Math.sin(angle);
        return new Point2D.Double(newPosX,newPosY);
    }
    public static double getAngle(Point2D point){
        return Math.atan2(point.getY(),point.getX());
    }

}
