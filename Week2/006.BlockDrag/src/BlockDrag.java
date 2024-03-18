import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class BlockDrag extends Application {
    ResizableCanvas canvas;
    private ArrayList<Block> blocks = new ArrayList<>();
    Block attachedBlock = null;
    double calibrationX,calibrationY;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Block Dragging");
        primaryStage.show();

        canvas.setOnMousePressed(e -> mousePressed(e));
        canvas.setOnMouseReleased(e -> mouseReleased(e));
        canvas.setOnMouseDragged(e -> mouseDragged(e));

        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }
    public void init(){
        blocks.add(new Block(Color.red,0,0,100,100));
        blocks.add(new Block(Color.black, 100,100,100,100));
        blocks.add(new Block(Color.cyan, 250,250,100,100));
        blocks.add(new Block(Color.yellow, 300,300,100,100));
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        for (Block block : blocks){
            block.draw(graphics);
        }
    }


    public static void main(String[] args)
    {
        launch(BlockDrag.class);
    }

    private void mousePressed(MouseEvent e)
    {
        double mousePosX = e.getX();
        double mousePosY = e.getY();
        for (Block block : blocks){
            if (block.insideArea(mousePosX, mousePosY)){
                attachedBlock = block;
                calibrationX = mousePosX - block.getPosX();
                calibrationY = mousePosY - block.getPosY();
                break;
            }
        }
    }

    private void mouseReleased(MouseEvent e)
    {
        attachedBlock = null;
    }

    private void mouseDragged(MouseEvent e)
    {
        if (attachedBlock != null){
            double mousePosX = e.getX();
            double mousePosY = e.getY();
            attachedBlock.setNewPos(mousePosX - calibrationX, mousePosY - calibrationY);
            draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        }
    }

}
