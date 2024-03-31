package Setups;

import LinePrep.Line;

import java.awt.geom.Point2D;
import java.util.List;

public class Map extends MapSetup{
    // if these are not final, the map will not be drawn correctly and the rays will glitch over the screen at a certain point
    private final int  WIDTH = 1920;
    private final int HEIGHT = 1080;
    public void initialiseLevel(){
        this.lines.add(new Line(new Point2D.Double(0,0), new Point2D.Double(WIDTH,0)));
        this.lines.add(new Line(new Point2D.Double(WIDTH,0), new Point2D.Double(WIDTH,HEIGHT)));
        this.lines.add(new Line(new Point2D.Double(WIDTH,HEIGHT), new Point2D.Double(0,HEIGHT)));
        this.lines.add(new Line(new Point2D.Double(0, HEIGHT), new Point2D.Double(0,0)));
        this.lines.add(new Line(new Point2D.Double(50,100),new Point2D.Double(250,300)));
        this.lines.add(new Line(new Point2D.Double(490,110), new Point2D.Double(600,460)));
        this.lines.add(new Line(new Point2D.Double(300,190), new Point2D.Double(400,100)));
        this.lines.add(new Line(new Point2D.Double(250,400), new Point2D.Double(350,350)));
        this.lines.add(new Line(new Point2D.Double(1000,800), new Point2D.Double(1000,500)));
        this.lines.add(new Line(new Point2D.Double(1200,733), new Point2D.Double(1500,910)));
        this.lines.add(new Line(new Point2D.Double(1200, 250), new Point2D.Double(1100, 100)));
        this.lines.add(new Line(new Point2D.Double(100, 900), new Point2D.Double(200, 1000)));
        this.lines.add(new Line(new Point2D.Double(1800, 100), new Point2D.Double(1700, 600)));

        // to make sure we can still see the lines that matters i made an extra Array with map elements so i can draw these seperated from all the others
        for (Line line : lines){
            this.mapLines.add(line);
        }
    }
    public List<Line> lines (){
        return this.lines;
    }
}
