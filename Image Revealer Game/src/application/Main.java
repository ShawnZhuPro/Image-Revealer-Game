package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {
	
	int targetDepth = 7; // Max # of iterations for splitCircle() method
	int maxSize = 800;   // Max size of the scene

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, maxSize, maxSize);

        // Create initial big circle
        Circle bigCircle = new Circle(maxSize / 2, maxSize / 2, maxSize / 4, Color.rgb((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)));
        root.getChildren().add(bigCircle);  // Adds bigCircle to the pane named "root" (basically displays the circle)

        // Add mouse event handler for splitting the big starter circle
        // This mouse event occurs when the cursor "enters" a circle
        bigCircle.setOnMouseEntered(event -> {
        	// If the circle being entered isn't already split, we classify it as now a split circle
            if (!bigCircle.getProperties().containsKey("split")) {
                bigCircle.getProperties().put("split", true);
                splitCircle(bigCircle, 0);  // The entire algorithm is pretty much based off this
            }
        });

        stage.setScene(scene);
        stage.setTitle("Program");
        stage.show();
    }

    // TODO* Finish this method
    private void splitCircle(Circle circle, int depth) {
    	// Base case
        if (depth >= targetDepth) {
            return;
        }

        double newRadius = circle.getRadius() / 2;
        double x = circle.getCenterX();
        double y = circle.getCenterY();
        Color color = (Color) circle.getFill();

        // Remove the current circle
        circle.setFill(Color.TRANSPARENT);

        // Create and add 4 smaller circles
        for (int i = 0; i < 4; i++) {

            // Add mouse event handler for circle splitting
            
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
