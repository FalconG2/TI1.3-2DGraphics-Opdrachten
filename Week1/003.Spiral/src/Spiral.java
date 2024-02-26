import java.awt.*;
import java.awt.geom.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

public class Spiral extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas canvas = new Canvas(1920, 1080);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("Spiral");
        primaryStage.show();
    }
    
    
    public void draw(FXGraphics2D graphics) {
        graphics.translate(1920/2,1080/2);
        graphics.scale(1,-1);

        graphics.setColor(Color.RED);
        graphics.draw(new Line2D.Double(-1920,0,1920,0));
        graphics.draw(new Line2D.Double(0,-1080,0,1080));

        graphics.setColor(Color.BLACK);

        double phiStart = 0.01;
        double scale = 50.0;
        double n = 1.0;

        double lastX = n*phiStart*Math.cos(phiStart);
        double lastY = n*phiStart*Math.sin(phiStart);

        for (double phi = 0.01; phi < 20; phi += 0.01){
            double x = n*phi*Math.cos(phi);
            double y = n*phi*Math.sin(phi);
            graphics.draw(new Line2D.Double(x*scale,y*scale,lastX*scale,lastY*scale));
            lastX = x;
            lastY = y;
        }
    }
    
    
    
    public static void main(String[] args) {
        launch(Spiral.class);
    }

}
