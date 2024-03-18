import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.geometry.Point2D;

import javax.imageio.ImageIO;

import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class Screensaver extends Application {
    private ResizableCanvas canvas;
    private Point2D[] points;
    private Point2D[] speed;
    private ArrayList<Line> memory;
    private int startingPoint = 0;
    private int lineCount;
    private double x = 0;
    private double timeDelay = 0;

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
        stage.setTitle("Screensaver");
        stage.show();
        draw(g2d);
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.black);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.setColor(Color.BLUE);

        for (int i = 0; i < 4; i++){
            Point2D point1 = points[i];
            Point2D point2 = points[(i + 1) % 4];
            graphics.drawLine((int)point1.getX(), (int) point1.getY(), (int)point2.getX(), (int)point2.getY());
        }
        for (int i = startingPoint; i < memory.size(); i++){
            graphics.drawLine(memory.get(i).getX1(), memory.get(i).getY1(),memory.get(i).getX2(),memory.get(i).getY2());
        }


    }

    public void init()
    {
        points = new Point2D[4];
        speed = new Point2D[4];
        memory = new ArrayList<>();

        points[0] = new Point2D(150,100);
        points[1] = new Point2D(300,100);
        points[2] = new Point2D(300,250);
        points[3] = new Point2D(150,250);

        speed[0] = new Point2D(-50,-50);
        speed[1] = new Point2D(-50,50);
        speed[2] = new Point2D(50,-50);
        speed[3] = new Point2D(50,50);
    }

    public void update(double deltaTime)
    {
        timeDelay += deltaTime;
        for (int i = 0; i < 4; i++){
            Point2D point1 = points[i];
            Point2D pace = speed[i];
            point1 = point1.add(pace.multiply(deltaTime));
            if (point1.getX() <= 0 || point1.getX() >= canvas.getWidth()){
                pace = new Point2D(-pace.getX(), pace.getY());
                if (point1.getX() <= 0){
                    points[i] = new Point2D(0, points[i].getY());
                }
                if (point1.getY() >= canvas.getWidth()){
                    points[i] = new Point2D(points[i].getX(),0);
                }
            }
            if (point1.getY() <= 0 || point1.getY() >= canvas.getHeight()){
                pace = new Point2D(pace.getX(), -pace.getY());
                if (point1.getY() <= 0){
                    points[i] = new Point2D(points[i].getX(),0);
                }
                if (point1.getY() >= canvas.getHeight()){
                    points[i] = new Point2D(points[i].getX(),canvas.getHeight());
                }
            }
            points[i] = point1;
            speed[i] = pace;
        }
        double frame = 1.0/15;
        while (timeDelay > frame){
            timeDelay -= frame;
            for (int i = 0; i < 4; i++){
                Point2D point1 = points[i];
                Point2D point2 = points[(i + 1) % 4];
                memory.add(new Line((int)point1.getX(), (int)point1.getY(), (int)point2.getX(), (int)point2.getY()));
            }
        }
        while (memory.size() > points.length*lineCount){
            memory.remove(0);
        }

    }

    public static void main(String[] args)
    {
        launch(Screensaver.class);
    }

}
