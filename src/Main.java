import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        new JFXPanel();
        Platform.runLater(Main::launch);
        Platform.runLater(Main::control);
    }

    private static boolean doStep = false, stopped = false;
    private static AnimationTimer at;
    private static Emitter e;


    private static void close() {
        System.exit(0);
    }

    private static void control() {
        Stage stage = new Stage();
        stage.setTitle("Controller");
        stage.setWidth(200);
        stage.setHeight(500);
        stage.setResizable(false);
        stage.setOnCloseRequest(we -> close());
        stage.getIcons().add(new Image("/tie.png"));
        stage.show();

        Group group = new Group();
        Scene scene = new Scene(group);
        stage.setScene(scene);

        VBox vb = new VBox();
        vb.setPrefWidth(200);
        vb.setPrefHeight(500);
        vb.setSpacing(10);
        vb.setAlignment(Pos.CENTER);
        group.getChildren().add(vb);

        Button up = new Button("Up!");
        up.setOnAction((ActionEvent) -> e.upSpeed());
        vb.getChildren().add(up);

        Button down = new Button("Down!");
        down.setOnAction((ActionEvent) -> e.downSpeed());
        vb.getChildren().add(down);

        Button pause = new Button("Pause");
        pause.setOnAction((ActionEvent) -> {
            stopped = true;
            at.stop();
        });
        vb.getChildren().add(pause);

        Button play = new Button("Play");
        play.setOnAction((ActionEvent) -> {
            if(doStep) doStep = false;
            stopped = false;
            at.start();
        });
        vb.getChildren().add(play);

        Button step = new Button("Step");
        step.setOnAction((ActionEvent) -> {
            if (stopped) {
                doStep = true;
                at.start();
            }
        });
        vb.getChildren().add(step);
    }

    private static void launch() {
        int windowWidth = 1000;
        int windowHeight = 500;

        Stage stage = new Stage();
        stage.setTitle("Doppler Effect Visualisation");
        stage.setWidth(windowWidth);
        stage.setHeight(windowHeight);
        stage.setResizable(false);
        stage.setOnCloseRequest(we -> close());
        stage.getIcons().add(new Image("/tie.png"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

        Group group = new Group();
        Scene scene = new Scene(group);
        Canvas canvas = new Canvas();
        canvas.setWidth(windowWidth);
        canvas.setHeight(windowHeight);
        group.getChildren().add(canvas);
        stage.setScene(scene);

        DecimalFormat df = new DecimalFormat("#.00");

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.GRAY);
        gc.setFont(new Font("Lucida Sans", 24));

        e = new Emitter(2, 20, 34, 500, windowWidth);
        e.generateRings();

        at = new AnimationTimer() {
            private long last = 0;
            @Override
            public void handle(long now) {
                if(now-last >= 16_000_000) {
                    gc.fillRect(0, 0, windowWidth, windowHeight);
                    e.draw(gc, windowHeight);
                    e.update();
                    if (doStep) at.stop();
                    last = now;
                }
            }
        };

        at.start();

    }
}
