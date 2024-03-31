import Interfaces.View;
import Lead.MouseFollowDisplay;
import javafx.application.Application;

import javafx.scene.Scene;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/* de eindopdracht die ik heb gekozen is degen van de raycasting. Ik heb hiervoor enorm veel hulp gehad van Jayson aangezien ik deze opdarcht per se
wilde doen, maar dit was inderdaad wel een pittige. Samen met zijn hulp ben ik erg ver gekomen. Zo heeft hij mij links doorgestuurd
die ik heb kunnen gebruiken van het maken van deze eindopdracht.
 */

public class Eindopdracht extends Application {
    private List<View> views;
    public void init(){
        this.views = new ArrayList<>();
        views.add(new MouseFollowDisplay());
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        TabPane pane = new TabPane();
        pane.getTabs().add(new Tab("Mouse", views.get(0).getNode()));
        primaryStage.setScene(new Scene(pane));
        primaryStage.setTitle("Collin van der Pluijm - Eindopdracht");
        primaryStage.show();
    }
}

