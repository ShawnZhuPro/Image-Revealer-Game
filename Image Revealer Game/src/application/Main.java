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
	int circlesSplit = 0;

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();//
        Scene scene = new Scene(root, maxSize, maxSize);
        
        // Initialize image
        Image image = new Image("file:./src/Images/koala.png");
        
        // Initialize initial big circle
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        Color avgColor = extractAvg(image, 0, 0, width, height);
        Circle bigCircle = new Circle(maxSize / 2, maxSize / 2, maxSize / 4, avgColor);
        root.getChildren().add(bigCircle);  // Adds bigCircle to the pane named "root" (basically displays the circle)

        // Add mouse event handler for splitting the big starter circle
        // This mouse event occurs when the cursor "enters" a circle
        bigCircle.setOnMouseEntered(event -> {
        	// If the circle being entered isn't already split, we classify it as now a split circle
            if (!bigCircle.getProperties().containsKey("split")) {
                bigCircle.getProperties().put("split", true);
                circlesSplit++;
                splitCircle(image, bigCircle, 0, 0, 0, width, height);
            }
        });

        stage.setScene(scene);
        stage.setTitle("Program");
        stage.show();

    }

    private void splitCircle(Image image, Circle circle, int depth, int x0, int y0, int width, int height) {
    	// Base case
        if (depth >= targetDepth) {
            return;
        }
        
        circlesSplit++;
        System.out.println(circlesSplit);
        double newRadius = circle.getRadius() / 2;
        double x = circle.getCenterX();
        double y = circle.getCenterY();
        
        // Remove the current circle
        circle.setFill(Color.TRANSPARENT);
        
        // X and Y directions for circles
        int[] dx = {-1, 1, -1, 1};
        int[] dy = {-1, -1, 1, 1};

        // Create and add 4 smaller circles
        for (int i = 0; i < 4; i++) {
        	// Logic that positions each circle in its expected spots
        	// Update attributes of each circle
        	int newX = (int)(x + dx[i] * newRadius);
        	int newY = (int)(y + dy[i] * newRadius);
        	int newW = width /2;
        	int newH = height / 2;
        	Color newColor = extractAvg(image, x0 + (i%2) * newW, y0 + (i/2) * newH, newW, newH);
        	// Initialize new circle
        	Circle smallerCircle = new Circle(newX, newY, newRadius, newColor);

        	// Draws the circle on the scene
        	((Pane) circle.getParent()).getChildren().add(smallerCircle);

        	int iMod = i%2;
        	int iDiv = i/2;
            // Mouse event handler for circle splitting
        	smallerCircle.setOnMouseEntered(event -> {
        		if(!smallerCircle.getProperties().containsKey("split")) {
        			smallerCircle.getProperties().put("split", true);
        			splitCircle(image, smallerCircle, depth+1, x0 + (iMod) * newW, y0 + (iDiv) * newH, newW, newH);
        		}
        	});
            
        }
    }
    
    private Color extractAvg(Image image, int x0, int y0, int width, int height) {
    	PixelReader pixelReader = image.getPixelReader();
    	int sumR = 0, sumG = 0, sumB = 0;
    	int count = 0;
    	for (int y = 0; y < y0 + height; y++) {
            for (int x = 0; x < x0 + width; x++) {
                Color c = pixelReader.getColor(x, y);
                sumR += (long) (c.getRed() * 255);
                sumG += (long) (c.getGreen() * 255);
                sumB += (long) (c.getBlue() * 255);
                count++;
            }
        }
    	return Color.rgb((int) (sumR / count), (int) (sumG / count), (int) (sumB / count));
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}
