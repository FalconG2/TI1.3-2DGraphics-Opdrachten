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

public class YingYang extends Application {
    private ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Ying Yang");
        primaryStage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }


    public void draw(FXGraphics2D graphics)
    {
        //TODO
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.translate(1920/2,1080/2);

        Area a = new Area(new Ellipse2D.Double(0,0,300,300));
        Area b = new Area(new Ellipse2D.Double(0,0,300,300));
        Area c = new Area(new Ellipse2D.Double(130,50,35,35));
        Area d = new Area(new Ellipse2D.Double(130,225,35,35));

        GeneralPath split = new GeneralPath();
        split.moveTo(150,0);
        split.curveTo(400,150,-100,150,150,300);

        graphics.draw(a);
        graphics.draw(d);
        graphics.draw(c);
        graphics.draw(split);
    }


    public static void main(String[] args)
    {
        launch(YingYang.class);
    }

}
