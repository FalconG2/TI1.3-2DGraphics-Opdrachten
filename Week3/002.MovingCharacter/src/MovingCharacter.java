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
    private double posX = 100;
    private double posY = 300;
    private double speed = 50;
    private int walkAnimation = 32;


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
    BufferedImage[] images;
    public void init(){
        try {
            BufferedImage image = ImageIO.read(getClass().getResource("/images/sprite.png"));
            images = new BufferedImage[65];
            for (int i = 0; i < 65; i++){
                images[i] = image.getSubimage(64 * (i%8),64 * (i/8),64,64);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    double forward = 1;
    double backward = 1;
    boolean direction = true;
    //Hij moonwalked nu, kom niet uit het draaien van de images. heb dit geprobeerd met scale, en op YT opgezocht hoe men dat zou moeten doen
    //Dat was code die niet echt goed toegepast kan worden omdat hier gebruik gemaakt  wordt van nesting

    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        if (posX + 100 >= canvas.getWidth()){
            direction = false;
        }
        if (posX <= 0){
            direction = true;
        }
        if (direction){
            posX += forward;
            AffineTransform tx = new AffineTransform();
            tx.translate(posX, posY);
            graphics.drawImage(images[walkAnimation],tx,null);
        }
        if (!direction){
            posX -= backward;
            AffineTransform tx = new AffineTransform();
            tx.translate(posX, posY);
            graphics.drawImage(images[walkAnimation],tx,null);
        }
    }
    int i = 0;

    public void update(double deltaTime)
    {
        this.posX += speed * deltaTime;
        i++;
        if (i % 20 == 0){
            this.walkAnimation++;
        }
        if (walkAnimation > 39){
            this.walkAnimation = 32;
        }
        if (posX + 64 > canvas.getWidth()){
            posX = canvas.getWidth() - 64;
            speed -= speed;
        }
        if (posX < 0){
            posX = 0;
            speed = Math.abs(speed);
        }

    }

    public static void main(String[] args)
    {
        launch(MovingCharacter.class);
    }

}
