
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Vector2;
import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by johan on 2017-03-08.
 */
public class GameObject {

    private Body body;
    private BufferedImage image;
    private Vector2 offset;
    private double scale;

    public GameObject(String imageFile, Body body, Vector2 offset, double scale) {
        this.body = body;
        this.offset = offset;
        this.scale = scale;
        try {
            image = ImageIO.read(getClass().getResource(imageFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(FXGraphics2D g2d) {
        if (image == null) {
            return;
        }

        AffineTransform tx = new AffineTransform();
        tx.translate(body.getTransform().getTranslationX() * 100, body.getTransform().getTranslationY() * 100);
        tx.rotate(body.getTransform().getRotation());
        tx.scale(scale, -scale);
        tx.translate(offset.x, offset.y);

        tx.translate(-image.getWidth() / 2, -image.getHeight() / 2);
        g2d.drawImage(image, tx, null);

    }
    public void verDraw(FXGraphics2D g2d){
        if (image == null){
            return;
        }

        AffineTransform tx = new AffineTransform();
        tx.translate(body.getTransform().getTranslationX() * 100, body.getTransform().getTranslationY() * 100);
        tx.rotate(Math.toRadians(90));
        tx.rotate(body.getTransform().getRotation());
        tx.translate(offset.x,offset.y);
        tx.scale(0.125,-0.02);
        tx.translate(-image.getWidth()/2,-image.getHeight()/2);
        g2d.drawImage(image,tx,null);
    }
    public void horDraw(FXGraphics2D g2d){
        if (image == null){
            return;
        }

        AffineTransform tx = new AffineTransform();
        tx.translate(body.getTransform().getTranslationX() * 100, body.getTransform().getTranslationY() * 100);
        tx.rotate(Math.toRadians(270));
        tx.rotate(body.getTransform().getRotation());
        tx.translate(offset.x,offset.y);
        tx.scale(-0.0135,0.167);
        tx.translate(-image.getWidth()/2,-image.getHeight()/2);
        g2d.drawImage(image,tx,null);
    }
}
