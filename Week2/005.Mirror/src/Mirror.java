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

public class Mirror extends Application {
    ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Mirror");
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
        graphics.setColor(Color.BLACK);

        graphics.draw(new Line2D.Double(-canvas.getWidth(),0,canvas.getWidth(),0));
        graphics.draw(new Line2D.Double(0,-canvas.getHeight(),0,canvas.getHeight()));

        // Na een beetje zitten spelen met de Scale en de translate heb ik nu een Vierkant waarvan het middelpunt 0,150 is.
        // Ik ben erachter gekomen dat het punt in het vierkant nu linksonderin in het vierkant ligt.
        Rectangle2D square = new Rectangle2D.Double(-50,100,100,100);
        graphics.draw(square);

        for (double x = -canvas.getWidth(); x <= canvas.getWidth(); x++){
            double y = 2.5 * x;
            graphics.draw(new Line2D.Double(0,0,x,y));
        }

    }


    public static void main(String[] args)
    {
        launch(Mirror.class);
    }

}
