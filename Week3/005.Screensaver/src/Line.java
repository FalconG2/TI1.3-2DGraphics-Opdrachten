import org.jfree.fx.FXGraphics2D;

public class Line {
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    public Line(int x1, int x2, int y1, int y2){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }
    public int getX1(){
        return this.x1;
    }
    public int getX2(){
        return this.x2;
    }
    public int getY1(){
        return this.y1;
    }
    public int getY2(){
        return this.y2;
    }
    public void draw(FXGraphics2D graphics){
        graphics.drawLine(this.x1, this.y1, this.x2, this.y2);
    }
}
