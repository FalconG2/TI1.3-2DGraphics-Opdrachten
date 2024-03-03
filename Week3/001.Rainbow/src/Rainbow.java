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

        Font font = new Font("Arial",Font.PLAIN,72);

        Shape R = font.createGlyphVector(graphics.getFontRenderContext(),"R").getOutline();
        Shape E = font.createGlyphVector(graphics.getFontRenderContext(),"E").getOutline();
        Shape G = font.createGlyphVector(graphics.getFontRenderContext(),"G").getOutline();
        Shape N = font.createGlyphVector(graphics.getFontRenderContext(),"N").getOutline();
        Shape B = font.createGlyphVector(graphics.getFontRenderContext(),"B").getOutline();
        Shape O = font.createGlyphVector(graphics.getFontRenderContext(),"O").getOutline();

        regenboog.add(R); regenboog.add(E); regenboog.add(G); regenboog.add(E); regenboog.add(N); regenboog.add(B); regenboog.add(O); regenboog.add(O); regenboog.add(G);

        for (Shape shape : regenboog){
            AffineTransform tx = new AffineTransform();
        }
    }


    public static void main(String[] args)
    {
        launch(Rainbow.class);
    }

}
