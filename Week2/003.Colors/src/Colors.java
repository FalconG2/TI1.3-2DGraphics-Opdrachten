import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class Colors extends Application {
    private ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Colors");
        primaryStage.show();
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        ArrayList<Color> colors = new ArrayList<>();
        colors.add(Color.blue); colors.add(Color.black); colors.add(Color.cyan); colors.add(Color.darkGray); colors.add(Color.gray); colors.add(Color.green); colors.add(Color.lightGray); colors.add(Color.magenta); colors.add(Color.orange);
        colors.add(Color.pink); colors.add(Color.red); colors.add(Color.white); colors.add(Color.yellow);

        double x = 0;

        for (Color color : colors){
            graphics.setColor(color);
            Rectangle2D rectangle2D = new Rectangle2D.Double(x,150,150,150);
            graphics.draw(rectangle2D);
            graphics.fill(rectangle2D);
            x += 150;
        }
    }


    public static void main(String[] args)
    {
        launch(Colors.class);
    }

}
