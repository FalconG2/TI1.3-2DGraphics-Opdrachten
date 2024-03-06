import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Affine;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class Rainbow extends Application {
    private ResizableCanvas canvas;

    @Override
    public void start(Stage stage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        stage.setScene(new Scene(mainPane));
        stage.setTitle("Rainbow");
        stage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.translate(canvas.getWidth()/2,canvas.getHeight()/2);

        ArrayList<Shape> regenboog = new ArrayList<>();

        Font font = new Font("Arial",Font.PLAIN,80);

        Shape R = font.createGlyphVector(graphics.getFontRenderContext(),"R").getOutline();
        Shape E = font.createGlyphVector(graphics.getFontRenderContext(),"E").getOutline();
        Shape G = font.createGlyphVector(graphics.getFontRenderContext(),"G").getOutline();
        Shape N = font.createGlyphVector(graphics.getFontRenderContext(),"N").getOutline();
        Shape B = font.createGlyphVector(graphics.getFontRenderContext(),"B").getOutline();
        Shape O = font.createGlyphVector(graphics.getFontRenderContext(),"O").getOutline();

        regenboog.add(R); regenboog.add(E); regenboog.add(G); regenboog.add(E); regenboog.add(N); regenboog.add(B); regenboog.add(O); regenboog.add(O); regenboog.add(G);

        // sinds we 9 letters in het woord regenboog hebben kunnen we deze mooi verdelen met de radialen, deze loopt van -90 naar 90 wat een gat is van 180 graden.
        // Ik kan dus werken met stappen van 20.

        double x = -90; // wordt gebruikt om in de loop de juiste radials te krijgen.
        float i = (float)0.348; // dit is om de HSB in te stellen. we willen een halve cirkel wat PI is. Door deze door 9 te delen
                          // wat de letters van de regenboog zijn, kunnen we deze in de loop inbouwen door dit als stapgrootte te nemen.

        for (Shape shape : regenboog){
            AffineTransform rotate = new AffineTransform();

            rotate.setToRotation(Math.toRadians(x));
            rotate.translate(0,-150);

            graphics.setColor(Color.getHSBColor(i/3.14f,1,1));

            graphics.fill(rotate.createTransformedShape(shape));
            graphics.draw(rotate.createTransformedShape(shape));

            x += 20;
            i += 0.348;
        }


    }


    public static void main(String[] args)
    {
        launch(Rainbow.class);
    }

}
