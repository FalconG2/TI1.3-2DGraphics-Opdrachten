import java.awt.*;
import java.awt.geom.*;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class Moon extends Application {
    private ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Moon");
        primaryStage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        // pad voor de moon. Heb eerst op internet uitgezocht hoe nou die beziercurve in zijn werking ging
        GeneralPath moon = new GeneralPath();
        moon.moveTo(1000,300);
        moon.curveTo(1700,150,1700,1150,900,1000);
        moon.moveTo(1000,300);
        moon.curveTo(1400,350,1400,950,900,1000);
        moon.moveTo(1000,300);
        moon.closePath();

        graphics.setColor(Color.black);
        graphics.draw(moon);

        Area a = new Area(new Ellipse2D.Double(0,0,200,200));
        Area b = new Area(new Ellipse2D.Double(70,0,200,200));

        Area moonShape = new Area(b);
        moonShape.subtract(a);
        graphics.fill(moonShape);

        graphics.draw(moonShape);
    }


    public static void main(String[] args)
    {
        launch(Moon.class);
    }

}
