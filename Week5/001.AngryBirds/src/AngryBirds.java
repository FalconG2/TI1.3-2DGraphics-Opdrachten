import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.Force;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class AngryBirds extends Application {

    private ResizableCanvas canvas;
    private World world;
    private MousePicker mousePicker;
    private Camera camera;
    private boolean debugSelected = false;
    private boolean charging = false;
    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private ArrayList<GameObject> verBeamsPaint = new ArrayList<>();
    private ArrayList<GameObject> horBeamsPaint = new ArrayList<>();
    private BodyFixture verBeamsFix = new BodyFixture(Geometry.createRectangle(0.1, 1));
    private BodyFixture horBeamsFix = new BodyFixture(Geometry.createRectangle(1,0.1));
    private Body verBeams;
    private Body horBeams;
    private Body bird;
    private double mousePosX;
    private double mousePosY;

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane mainPane = new BorderPane();

        // Add debug button
        javafx.scene.control.CheckBox showDebug = new CheckBox("Show debug");
        showDebug.setOnAction(e -> {
            debugSelected = showDebug.isSelected();
        });
        mainPane.setTop(showDebug);

        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

        camera = new Camera(canvas, g -> draw(g), g2d);
        mousePicker = new MousePicker(canvas);

        canvas.setOnMousePressed(event -> {
            mousePosX = event.getX();
            mousePosY = event.getY();
        });

        canvas.setOnMouseReleased(event -> {
            bird.applyForce(new Vector2((mousePosX - event.getX())*1.2, (mousePosY - event.getY())*1.2));
        });



        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1) {
                    last = now;
                }
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();

        stage.setScene(new Scene(mainPane, 1920, 1080));
        stage.setTitle("Angry Birds");
        stage.show();
        draw(g2d);
    }




    public void init() {
        world = new World();
        world.setGravity(new Vector2(0, -9.8));

        bird = new Body();
        Body background = new Body();
        Body platform = new Body();
        Body sling = new Body();

        BodyFixture bodyFixture = new BodyFixture(Geometry.createRectangle(25.5,0.1));
        BodyFixture slingPole = new BodyFixture(Geometry.createRectangle(0.1,1.5));

        this.verBeamsFix.setFriction(2);
        this.horBeamsFix.setFriction(2);

        platform.getTransform().setTranslation(0,-5.25);
        platform.addFixture(bodyFixture);
        platform.setMass(MassType.INFINITE);

        sling.addFixture(slingPole);
        sling.setMass(MassType.INFINITE);
        sling.getTransform().setTranslation(-8,-4.45);

        bird.addFixture(Geometry.createCircle(0.24));
        bird.getTransform().setTranslation(-8,-3);
        bird.setMass(MassType.NORMAL);
        bird.getFixture(0).setRestitution(0.1);

        world.addBody(platform);
        world.addBody(sling);
        world.addBody(bird);

        gameObjects.add(new GameObject("/AB.jpg",background, new Vector2(0,0),1.0));
        gameObjects.add(new GameObject("/Sling.png",sling,new Vector2(-320,-1000),0.03));
        gameObjects.add(new GameObject("/BIRB.png",bird,new Vector2(0,-100),0.08));

        // De vorm van de toren wordt een piramide, de eerste for i loops worden de verticale beams en de onderste for loops de horizontale.
        for (double x = 2; x <= 7; x += 1) {
            verBeams = new Body();
            verBeams.addFixture(verBeamsFix);
            verBeams.setMass(MassType.NORMAL);
            verBeams.getTransform().setTranslation(x, -4.75);
            world.addBody(verBeams);
            verBeamsPaint.add(new GameObject("/wood.jpg",verBeams,new Vector2(0,0),0.025));
        }

        for (double x = 2.5; x <= 6.5; x++){
            verBeams = new Body();
            verBeams.addFixture(verBeamsFix);
            verBeams.setMass(MassType.NORMAL);
            verBeams.getTransform().setTranslation(x,-3.58);
            world.addBody(verBeams);
            verBeamsPaint.add(new GameObject("/wood.jpg",verBeams,new Vector2(0,0),0.025));
        }
        for (double x = 3.0; x <= 6; x++) {
            verBeams = new Body();
            verBeams.addFixture(verBeamsFix);
            verBeams.setMass(MassType.NORMAL);
            verBeams.getTransform().setTranslation(x,-2.46);
            world.addBody(verBeams);
            verBeamsPaint.add(new GameObject("/wood.jpg",verBeams,new Vector2(0,0),0.025));
        }
        for (double x = 3.5; x <= 5.5; x++){
            verBeams = new Body();
            verBeams.addFixture(verBeamsFix);
            verBeams.setMass(MassType.NORMAL);
            verBeams.getTransform().setTranslation(x,-0.8);
            world.addBody(verBeams);
            verBeamsPaint.add(new GameObject("/wood.jpg",verBeams,new Vector2(0,0),0.025));
        }
        for (double x = 4.0; x <= 5.0; x++){
            verBeams = new Body();
            verBeams.addFixture(verBeamsFix);
            verBeams.setMass(MassType.NORMAL);
            verBeams.getTransform().setTranslation(x,0.7);
            world.addBody(verBeams);
            verBeamsPaint.add(new GameObject("/wood.jpg",verBeams,new Vector2(0,0),0.025));
        }


        // Horizontal layers
        for (double x = 2.5; x <= 6.5; x+= 1){
            horBeams = new Body();
            horBeams.addFixture(horBeamsFix);
            horBeams.setMass(MassType.NORMAL);
            horBeams.getTransform().setTranslation(x,-3.7);
            world.addBody(horBeams);
            horBeamsPaint.add(new GameObject("/wood.jpg",horBeams, new Vector2(0,0),0.025));
        }
        for (double x = 3.0; x <= 6; x ++){
            horBeams = new Body();
            horBeams.addFixture(horBeamsFix);
            horBeams.setMass(MassType.NORMAL);
            horBeams.getTransform().setTranslation(x,-2.66);
            world.addBody(horBeams);
            horBeamsPaint.add(new GameObject("/wood.jpg",horBeams, new Vector2(0,0),0.025));
        }
        for (double x = 3.5; x <= 5.5; x++){
            horBeams = new Body();
            horBeams.addFixture(horBeamsFix);
            horBeams.setMass(MassType.NORMAL);
            horBeams.getTransform().setTranslation(x, -1.6);
            world.addBody(horBeams);
            horBeamsPaint.add(new GameObject("/wood.jpg",horBeams, new Vector2(0,0),0.025));
        }
        for (double x = 4.0; x <= 5.0; x++){
            horBeams = new Body();
            horBeams.addFixture(horBeamsFix);
            horBeams.setMass(MassType.NORMAL);
            horBeams.getTransform().setTranslation(x,0);
            world.addBody(horBeams);
            horBeamsPaint.add(new GameObject("/wood.jpg",horBeams, new Vector2(0,0),0.025));
        }
        horBeams = new Body();
        horBeams.addFixture(horBeamsFix);
        horBeams.setMass(MassType.NORMAL);
        horBeams.getTransform().setTranslation(4.5,1.4);
        world.addBody(horBeams);
        horBeamsPaint.add(new GameObject("/wood.jpg",horBeams, new Vector2(0,0),0.025));
    }

    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.WHITE);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        AffineTransform originalTransform = graphics.getTransform();

        graphics.setTransform(camera.getTransform((int) canvas.getWidth(), (int) canvas.getHeight()));
        graphics.scale(1, -1);

        for (GameObject go : gameObjects) {
            go.draw(graphics);
        }
        for (GameObject go : verBeamsPaint){
            go.verDraw(graphics);
        }
        for (GameObject go : horBeamsPaint){
            go.horDraw(graphics);
        }

        if (!debugSelected) {
            graphics.setColor(Color.blue);
            DebugDraw.draw(graphics, world, 100);
        }

        graphics.setTransform(originalTransform);
    }

    public void update(double deltaTime) {
        mousePicker.update(world, camera.getTransform((int) canvas.getWidth(), (int) canvas.getHeight()), 100);
        world.update(deltaTime);
    }

    public static void main(String[] args) {
        launch(AngryBirds.class);
    }

}
