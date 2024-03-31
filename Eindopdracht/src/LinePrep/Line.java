package LinePrep;

import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Line {
    private Point2D startingPos;
    private Point2D endingPos;
    public Line(Point2D startingPos, Point2D endingPos){
        this.startingPos = startingPos;
        this.endingPos = endingPos;
    }
    public Line(double x1, double y1, double x2, double y2){
        this.startingPos = new Point2D.Double(x1,y1);
        this.endingPos = new Point2D.Double(x2,y2);
    }
    public Point2D getStartingPos(){
        return startingPos;
    }
    public Point2D getEndingPos(){
        return endingPos;
    }
    public void setEndingPos(Point2D endingPos){
        this.endingPos = endingPos;
    }
    public Point2D[] getPoints(){
        return new Point2D[]{this.startingPos,this.endingPos};
    }
    public Point2D getDirec(){
        return MathPoint.substract(this.endingPos, this.startingPos);
    }
    public void draw(FXGraphics2D graphics){
        graphics.draw(new Line2D.Double(this.startingPos.getX(), this.startingPos.getY(), this.endingPos.getX(), this.endingPos.getY()));
    }
    public void drawMap(FXGraphics2D graphics){
        graphics.setColor(Color.RED);
        graphics.draw(new Line2D.Double(this.startingPos.getX(), this.startingPos.getY(), this.getEndingPos().getX(), this.getEndingPos().getY()));
    }
}
