import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.geom.Line2D;

public class House extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas canvas = new Canvas(1920, 1080);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("House");
        primaryStage.show();
    }


    public void draw(FXGraphics2D graphics) {
        graphics.translate(1920/2,1080/2);
        graphics.scale(1,-1);

        //Muren en vloer
        graphics.draw(new Line2D.Double(-200,-200,200,-200));
        graphics.draw(new Line2D.Double(200,-200,200,100));
        graphics.draw(new Line2D.Double(-200,-200,-200,100));

        //Dak
        graphics.draw(new Line2D.Double(-200,100,0,400));
        graphics.draw(new Line2D.Double(200,100,0,400));

        //Deur
        graphics.draw(new Line2D.Double(-150,-200,-150,0));
        graphics.draw(new Line2D.Double(-50,-200,-50,0));
        graphics.draw(new Line2D.Double(-150,0,-50,0));

        //Raam
        graphics.draw(new Line2D.Double(0,-125,150,-125));
        graphics.draw(new Line2D.Double(0,-25,150,-25));
        graphics.draw(new Line2D.Double(0,-125,0,-25));
        graphics.draw(new Line2D.Double(150,-125,150,-25));
    }



    public static void main(String[] args) {
        launch(House.class);
    }

}
