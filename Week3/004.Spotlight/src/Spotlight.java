import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
    private BufferedImage jesko;

    @Override
    public void start(Stage stage) throws Exception
    {

        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now)
            {
                if (last == -1)
                    last = now;
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();

        stage.setScene(new Scene(mainPane));
        stage.setTitle("Spotlight");
        stage.show();
        draw(g2d);
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        Shape shape = new Rectangle2D.Double(-25,-25,50,50);
        Rectangle2D screen = new Rectangle2D.Double(0,0,canvas.getWidth(),canvas.getHeight());

        canvas.setOnMouseMoved(e -> {
            AffineTransform tx = new AffineTransform();
            tx.translate(e.getX(),e.getY());
            graphics.setClip(tx.createTransformedShape(shape));
            graphics.draw(tx.createTransformedShape(shape));
            graphics.clip(screen);
        });
        graphics.setPaint(new TexturePaint(jesko,screen));



    }

    public void init()
    {
        try {
            jesko = ImageIO.read(getClass().getResource("/images/Jesko.jpg"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(double deltaTime)
    {

    }

    public static void main(String[] args)
    {
        launch(Spotlight.class);
    }

}
