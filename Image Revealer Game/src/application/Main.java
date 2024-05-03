package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {
	
	int targetDepth = 7; // Max # of iterations for splitCircle() method
	int maxSize = 1024;   // Max size of the scene

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, maxSize, maxSize);
        
        new ImageToCircleSplitter("file:./src/Images/FDR.png", root, maxSize, targetDepth);
        
        stage.setScene(scene);
        stage.setTitle("ImageToCircleSplitter.java");
        stage.show();

       
    }
    public static void main(String[] args) {
        launch(args);
    }
}
