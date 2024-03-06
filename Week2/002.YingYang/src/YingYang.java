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
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.translate(canvas.getWidth()/2,canvas.getHeight()/2);
        graphics.scale(1,-1);

        // Main area is elipse
        graphics.setColor(Color.BLACK);
        graphics.draw(new Ellipse2D.Double(0,0,450,450));

        // 3 delen general path curveTo werkt t zelfde als cubiccurve, de x3 en de y3 zijn de eindcoords.
        GeneralPath path = new GeneralPath();
        path.moveTo(225,450);
        path.curveTo(390,430,390,245,225,225);
        path.curveTo(60,205,60,20,225,0);
        path.curveTo(525,15,525,435,225,450);
        path.closePath();
        graphics.fill(path);
        graphics.draw(path);

        // 2 normale cirkels in de main cirkel
        graphics.fill(new Ellipse2D.Double(200,320,50,50));
        graphics.setColor(Color.WHITE);
        graphics.fill(new Ellipse2D.Double(200,90,50,50));
    }


    public static void main(String[] args)
    {
        launch(YingYang.class);
    }

}
