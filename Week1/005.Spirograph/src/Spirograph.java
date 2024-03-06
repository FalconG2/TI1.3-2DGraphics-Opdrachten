import java.awt.*;
import java.awt.geom.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

public class Spirograph extends Application {
    private TextField v1;
    private TextField v2;
    private TextField v3;
    private TextField v4;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas canvas = new Canvas(1920, 1080);
       
        VBox mainBox = new VBox();
        HBox topBar = new HBox();
        mainBox.getChildren().add(topBar);
        mainBox.getChildren().add(new Group(canvas));
        
        topBar.getChildren().add(v1 = new TextField("300"));
        topBar.getChildren().add(v2 = new TextField("1"));
        topBar.getChildren().add(v3 = new TextField("300"));
        topBar.getChildren().add(v4 = new TextField("10"));
                
        v1.textProperty().addListener(e -> draw(new FXGraphics2D(canvas.getGraphicsContext2D())));
        v2.textProperty().addListener(e -> draw(new FXGraphics2D(canvas.getGraphicsContext2D())));
        v3.textProperty().addListener(e -> draw(new FXGraphics2D(canvas.getGraphicsContext2D())));
        v4.textProperty().addListener(e -> draw(new FXGraphics2D(canvas.getGraphicsContext2D())));
        
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(mainBox));
        primaryStage.setTitle("Spirograph");
        primaryStage.show();
    }
    
    
    public void draw(FXGraphics2D graphics) {
        graphics.translate(1920/2,1080/2);
        graphics.scale(1,-1);

        // get doubles
        double a = Double.parseDouble(v1.getText());
        double b = Double.parseDouble(v2.getText());
        double c = Double.parseDouble(v3.getText());
        double d = Double.parseDouble(v4.getText());

        // set settings
        double resolution = 0.001;
        double scale = 0.5;
        double n = 1.0;

        double lastX = 0;
        double lastY = 0;

        // for loop met last y en last x Etc.
        for (double phi = 0.01; phi <= 20; phi += resolution){
            double x = a * Math.cos(b * phi) + c * Math.cos(d * phi);
            double y = a * Math.sin(b * phi) + c * Math.sin(d * phi);
            graphics.draw(new Line2D.Double(x*scale,y*scale,lastX*scale,lastY*scale));
            lastX = x;
            lastY = y;
        }
    }
    
    
    
    public static void main(String[] args) {
        launch(Spirograph.class);
    }

}
