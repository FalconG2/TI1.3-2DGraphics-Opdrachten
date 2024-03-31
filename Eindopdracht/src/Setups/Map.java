package Setups;

import LinePrep.Line;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Map extends MapSetup{
    private int width = 10;
    private int height = 10;
    public void initialiseLevel(){
        this.lines.add(new Line(new Point2D.Double(0,0), new Point2D.Double(width,0)));
        this.lines.add(new Line(new Point2D.Double(width,0), new Point2D.Double(width,height)));
        this.lines.add(new Line(new Point2D.Double(width,height), new Point2D.Double(0,height)));
        this.lines.add(new Line(new Point2D.Double(0, height), new Point2D.Double(0,0)));
        this.lines.add(new Line(new Point2D.Double(100,100),new Point2D.Double(200,300)));
        this.lines.add(new Line(new Point2D.Double(460,200), new Point2D.Double(600,300)));
        this.lines.add(new Line(new Point2D.Double(300,90), new Point2D.Double(400,100)));
        this.lines.add(new Line(new Point2D.Double(250,400), new Point2D.Double(350,350)));

        this.mapLines.add(new Line(new Point2D.Double(0,0), new Point2D.Double(width,0)));
        this.mapLines.add(new Line(new Point2D.Double(width,0), new Point2D.Double(width,height)));
        this.mapLines.add(new Line(new Point2D.Double(width,height), new Point2D.Double(0,height)));
        this.mapLines.add(new Line(new Point2D.Double(0, height), new Point2D.Double(0,0)));
        this.mapLines.add(new Line(new Point2D.Double(100,100),new Point2D.Double(200,300)));
        this.mapLines.add(new Line(new Point2D.Double(460,200), new Point2D.Double(600,300)));
        this.mapLines.add(new Line(new Point2D.Double(300,90), new Point2D.Double(400,100)));
        this.mapLines.add(new Line(new Point2D.Double(250,400), new Point2D.Double(350,350)));
    }
    public List<Line> lines (){
        return this.lines;
    }
}
