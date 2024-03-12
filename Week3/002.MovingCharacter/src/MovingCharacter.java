import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class MovingCharacter extends Application {
    private ResizableCanvas canvas;


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
                update((now - last) / 100000000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();


        stage.setScene(new Scene(mainPane));
        stage.setTitle("Moving Character");
        stage.show();
        draw(g2d);
    }
    //Ik heb in de voorbeeldopgaven gekeken naar wat handig is. Ik heb besloten om een array te maken voor het lopen en voor het springen.
    //Hiervoor is het handiger als ik van de afbeelding rijen kies die ik wil gebruiken, in plaats van een array met 65 afbeeldingen.
    //Voor het lopen lijkt mij rij 5 het best om te gebruiken. Dit zijn locaties 32 tot 39 in de array.
    BufferedImage[] images;
    public void init(){
        try {
            BufferedImage image = ImageIO.read(getClass().getResource("/images/sprite.png"));
            images = new BufferedImage[65];
            for (int i = 0; i < 65; i++){
                images[i] = image.getSubimage(64 * i,0,64,64);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    int part = 32;

    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        AffineTransform transform = new AffineTransform();
        transform.translate(positionX, 200);

        if (part == 39){
            part = 32;
        }
        graphics.drawImage(images[part],transform,null);
        part++;
    }

    double positionX = 0;
    public void update(double deltaTime)
    {
        positionX+=4;
        if (positionX > 1920){
            positionX = 0;
        }
        if (positionX < 0){
            positionX = 1920;
        }
        try {
            Thread.sleep(1000);
        } catch (Exception e1){
        e1.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        launch(MovingCharacter.class);
    }

}
