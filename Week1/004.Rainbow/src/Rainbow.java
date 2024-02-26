import java.awt.*;
import java.awt.geom.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

public class Rainbow extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas canvas = new Canvas(1920, 1080);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("Rainbow");
        primaryStage.show();
    }
    
    
    public void draw(FXGraphics2D graphics) {
        graphics.translate(1920 / 2, 1080 / 2);
        graphics.scale(1, -1);

        double radiusBinnen = 170.0;
        double radiusBuiten = 220.0;
        double step = 0.001;
        double scale = 2;

        for(float i = 0; i <= 3.14; i += step){
            double x1 = radiusBinnen * Math.cos(i);
            double y1 = radiusBinnen * Math.sin(i);
            double x2 = radiusBuiten * Math.cos(i);
            double y2 = radiusBuiten * Math.sin(i);
            graphics.setColor(Color.getHSBColor(i/3.14f,1,1));
            graphics.draw(new Line2D.Double(x1*scale,y1*scale,x2*scale,y2*scale));
        }
    }
    
    
    
    public static void main(String[] args) {
        launch(Rainbow.class);
    }

}
