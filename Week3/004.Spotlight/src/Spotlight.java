import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class Spotlight extends Application {
    private ResizableCanvas canvas;
    private double mousePosX;
    private double mousePosY;
    private BufferedImage jesko;

    @Override
    public void start(Stage stage) throws Exception
    {

        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

        canvas.setOnMouseMoved(event -> {
            mousePosX = event.getX();
            mousePosY = event.getY();
            draw(g2d);
        });
        stage.setScene(new Scene(mainPane));
        stage.setTitle("Spotlight");
        stage.show();
        draw(g2d);

        try{
            jesko = ImageIO.read(getClass().getResource("/images/jesko.jpg"));
        } catch (Exception e){
            e.printStackTrace();
        }

    }



    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        graphics.draw(new Rectangle2D.Double(mousePosX-250, mousePosY-250, 500, 500));
        graphics.setClip(new Rectangle2D.Double(mousePosX-250, mousePosY-250, 500, 500));

        graphics.drawImage(jesko,null,null);

        graphics.setClip(null);
        graphics.setColor(Color.BLACK);
    }

    public static void main(String[] args)
    {
        launch(Spotlight.class);
    }

}
