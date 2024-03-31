package Lead;

import Interfaces.Drawable;
import Interfaces.Updatable;
import LinePrep.Line;
import Setups.RaySetup;
import Setups.RenderSetup;
import org.jfree.fx.FXGraphics2D;

import java.awt.geom.Point2D;
import java.util.List;

public class RenderEngine implements Drawable, Updatable {
    private RaySetup raySetup;
    private RenderSetup renderSetup;

    public RenderEngine(List<Line> lines, List<Line> rays){
        this.raySetup = new CastingEngine(lines, rays);
        this.renderSetup = new DrawEngine(rays);
    }
    public void update(){
        this.raySetup.update();
        this.renderSetup.update();
    }
    public void setPosition(Point2D position){
        this.raySetup.setPos(position);
    }
    public void draw(FXGraphics2D graphics){
        this.renderSetup.draw(graphics);
    }

}
