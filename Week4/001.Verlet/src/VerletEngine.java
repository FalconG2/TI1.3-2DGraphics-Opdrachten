
import java.awt.*;
import java.awt.geom.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class VerletEngine extends Application {

    private ResizableCanvas canvas;
    private ArrayList<Particle> particles = new ArrayList<>();
    private ArrayList<Constraint> constraints = new ArrayList<>();
    private PositionConstraint mouseConstraint = new PositionConstraint(null);

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
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

        // Mouse Events
        canvas.setOnMouseClicked(e -> mouseClicked(e));
        canvas.setOnMousePressed(e -> mousePressed(e));
        canvas.setOnMouseReleased(e -> mouseReleased(e));
        canvas.setOnMouseDragged(e -> mouseDragged(e));

        stage.setScene(new Scene(mainPane));
        stage.setTitle("Verlet Engine");
        stage.show();
        draw(g2d);
    }

    public void init() {
        for (int i = 0; i < 20; i++) {
            particles.add(new Particle(new Point2D.Double(100 + 50 * i, 100)));
        }

        for (int i = 0; i < 10; i++) {
            constraints.add(new DistanceConstraint(particles.get(i), particles.get(i + 1)));
        }

        constraints.add(new PositionConstraint(particles.get(10)));
        constraints.add(mouseConstraint);
    }

    private void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.BLACK);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        for (Constraint c : constraints) {
            c.draw(graphics);
        }

        for (Particle p : particles) {
            p.draw(graphics);
        }
    }

    private void update(double deltaTime) {
        for (Particle p : particles) {
            p.update((int) canvas.getWidth(), (int) canvas.getHeight());
        }

        for (Constraint c : constraints) {
            c.satisfy();
        }
    }
    // Ik ben niet gekomen uit de doek constraint, heb bij meerdere medestudenten gekeken maar kom er niet aan uit :(.
    // Door dit heb ik geen knoppen toegevoegd aan de save mogelijkheid maar heb alleen de methoden gemaakt.

    private void mouseClicked(MouseEvent e) {
        Point2D mousePosition = new Point2D.Double(e.getX(), e.getY());
        Particle nearest = getNearest(mousePosition);
        Particle newParticle = new Particle(mousePosition);
        particles.add(newParticle);
        constraints.add(new DistanceConstraint(newParticle, nearest));

        if (e.getButton() == MouseButton.SECONDARY) {
            ArrayList<Particle> sorted = new ArrayList<>();
            sorted.addAll(particles);

            //sorteer alle elementen op afstand tot de muiscursor. De toegevoegde particle staat op 0, de nearest op 1, en de derde op 2
            Collections.sort(sorted, new Comparator<Particle>() {
                @Override
                public int compare(Particle o1, Particle o2) {
                    return (int) (o1.getPosition().distance(mousePosition) - o2.getPosition().distance(mousePosition));
                }
            });
            if (e.isShiftDown()){
                constraints.add(new DistanceConstraint(newParticle, sorted.get(2)));
            }
            if (e.isControlDown()){
                constraints.add(new DistanceConstraint(newParticle, sorted.get(1),100));
                constraints.add(new DistanceConstraint(newParticle, sorted.get(2),100));
            }
        } else if (e.getButton() == MouseButton.MIDDLE) {
            if (e.isShiftDown()){

                for (int i = 0; i < 10; i++){
                    for (int j = 0; j < 10; j++){
                        Particle doek = new Particle(new Point2D.Double(100 * i + 50, 100 * j + 50));
                        particles.add(doek);
                        if (j == 0){
                            constraints.add(new PositionConstraint(doek));
                        }
                        if (j > 0){
                            Particle top = particles.get((i * 10) + (j - 1));
                            constraints.add(new RopeConstraint(doek,top));
                        }
                    }
                }
                for (int i = 0; i < 90; i++){
                    constraints.add(new RopeConstraint(particles.get(i), particles.get(i + 10)));
                }
                constraints.add(mouseConstraint);
            } else
            particles.clear();
            constraints.clear();
            init();
        }
        //Set staticConstraint, hoeven niet te checken voor de primary mouse button aangezien dit al gebeurt aan het begin van de methode
        else if (e.isControlDown()){
            constraints.add(new StaticConstraint(newParticle));
        }
        else if (e.isShiftDown()){
            constraints.add(new RopeConstraint(newParticle, nearest));
        }
    }

    private Particle getNearest(Point2D point) {
        Particle nearest = particles.get(0);
        for (Particle p : particles) {
            if (p.getPosition().distance(point) < nearest.getPosition().distance(point)) {
                nearest = p;
            }
        }
        return nearest;
    }
    private void saveFile(){
        File destination = new File("Saves/VerletEngine.ser");
        try {
            FileOutputStream go = new FileOutputStream(destination);
            ObjectOutputStream go2 = new ObjectOutputStream(go);
            go2.writeObject(particles);
            go2.writeObject(constraints);
            go2.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private void loadFile(){
        String path = "Saves/VerletEngine.ser";
        try {
            FileInputStream inputStream = new FileInputStream(path);
            ObjectInputStream objectInput = new ObjectInputStream(inputStream);
            particles = (ArrayList<Particle>) objectInput.readObject();
            constraints = (ArrayList<Constraint>) objectInput.readObject();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void mousePressed(MouseEvent e) {
        Point2D mousePosition = new Point2D.Double(e.getX(), e.getY());
        Particle nearest = getNearest(mousePosition);
        if (nearest.getPosition().distance(mousePosition) < 10) {
            mouseConstraint.setParticle(nearest);
        }
    }

    private void mouseReleased(MouseEvent e) {
        mouseConstraint.setParticle(null);
    }

    private void mouseDragged(MouseEvent e) {
        mouseConstraint.setFixedPosition(new Point2D.Double(e.getX(), e.getY()));
    }

    public static void main(String[] args) {
        launch(VerletEngine.class);
    }

}
