
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Block {
    private double height;
    private double width;
    private double posX;
    private double posY;
    private Color color;
    public Block(Color color, double x, double y, double width, double height){
        this.color = color;
        this.posX = x;
        this.posY = y;
        this.width = width;
        this.height = height;
    }

    //Draw methode voor de blocks
    public void draw(FXGraphics2D g2d){
        g2d.setColor(this.color);
        g2d.fill(new Rectangle2D.Double(this.posX, this.posY, this.width, this.height));
    }
    public double getPosX(){
        return this.posX;
    }
    public double getPosY(){
        return this.posY;
    }

    //Methode om de positie aan te kunnen passen
    public void setNewPos(double x, double y){
        this.posX = x;
        this.posY = y;
    }

    // Methode om te kijken of de klik van de muis in de omgeving van de block was
    public boolean insideArea(double x, double y){
        if (x >= this.posX && x <= this.posX + this.width && y >= this.posY && y <= this.posY + this.height){
            return true;
        } else {
            return false;
        }
    }
}
