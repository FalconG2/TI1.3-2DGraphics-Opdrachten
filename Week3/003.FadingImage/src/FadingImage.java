import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class FadingImage extends Application {
    private ResizableCanvas canvas;
    BufferedImage jesko;
    BufferedImage fadingJesko;
    private float f = 0;

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        init();
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
        new AnimationTimer() {
            long last = -1;
            @Override
            public void handle(long now) {
		if(last == -1)
                    last = now;
		update((now - last) / 1000000000.0);
		last = now;
		draw(g2d);
            }
        }.start();

        try {
            jesko = ImageIO.read(getClass().getResource("/images/Jesko.jpg"));
            fadingJesko = ImageIO.read(getClass().getResource("/images/fadingJesko.jpg"));
        } catch (Exception e){
            e.printStackTrace();
        }
        
        stage.setScene(new Scene(mainPane));
        stage.setTitle("Fading image");
        stage.show();
        draw(g2d);
    }
    
    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int)canvas.getWidth(), (int)canvas.getHeight());

        graphics.setComposite(AlphaComposite.getInstance(3,1.0f));
        graphics.drawImage(jesko,AffineTransform.getTranslateInstance(0,0),null);

        graphics.setComposite(AlphaComposite.getInstance(3,f));
        graphics.drawImage(fadingJesko,AffineTransform.getTranslateInstance(0,0),null);
    }
    
    boolean fading = true;
    public void update(double deltaTime) {
        deltaTime = 0.0025;
        if (fading){
            f += deltaTime;
            if (f >= 1.0f){
                fading = false;
            }
        }
        if (!fading){
            f -= deltaTime;
            if (f <= 0.0025f){
                fading = true;
            }
        }

    }

    public static void main(String[] args) {
        launch(FadingImage.class);
    }

}
