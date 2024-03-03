import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.*;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class GradientPaintExercise extends Application {
    private ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("GradientPaint");
        primaryStage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        Point2D center = new Point2D.Double(canvas.getWidth()/2,canvas.getHeight()/2);
        float radius = 50;
        float[] dist = {0.3f,0.8f,1.0f};
        Color[] colors = {Color.red, Color.green, Color.blue};

        RadialGradientPaint p = new RadialGradientPaint(center,radius,dist,colors, MultipleGradientPaint.CycleMethod.REFLECT);

        Rectangle2D rectangle2D = new Rectangle2D.Double(0,0,1920,1080);
        graphics.setPaint(p);
        graphics.draw(rectangle2D);


        //---------------------------------------------------------------------------------------------------------------------------------

        canvas.setOnMouseDragged(MouseEvent -> {
            p.getFocusPoint().setLocation(MouseEvent.getX(), MouseEvent.getY());
        });
        //TODO
    }


    public static void main(String[] args)
    {
        launch(GradientPaintExercise.class);
    }

}
