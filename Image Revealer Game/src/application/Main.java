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
        Pane root = new Pane();
        Scene scene = new Scene(root, maxSize, maxSize);
        
        // Initialize image
        Image image = new Image("file:./src/Images/koala.png");

        // Create initial big circle
        int red = getR(image, 0, 0);
    	int green = getR(image, 0, 0);
    	int blue = getR(image, 0, 0);
        Circle bigCircle = new Circle(maxSize / 2, maxSize / 2, maxSize / 4, Color.rgb(red,  green,  blue));
        root.getChildren().add(bigCircle);  // Adds bigCircle to the pane named "root" (basically displays the circle)

        // Add mouse event handler for splitting the big starter circle
        // This mouse event occurs when the cursor "enters" a circle
        bigCircle.setOnMouseEntered(event -> {
        	// If the circle being entered isn't already split, we classify it as now a split circle
            if (!bigCircle.getProperties().containsKey("split")) {
                bigCircle.getProperties().put("split", true);
                circlesSplit++;
                splitCircle(image, bigCircle, 0);  // The entire algorithm is pretty much based off this
            }
        });

        stage.setScene(scene);
        stage.setTitle("Program");
        stage.show();

    }

    private void splitCircle(Image image, Circle circle, int depth) {
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

        // Create and add 4 smaller circles
        for (int i = 0; i < 4; i++) {
        	Circle smallerCircle = new Circle();
        	// Update attributes of each circle
        	// Logic that positions each circle in its expected spots
        	smallerCircle.setCenterX(x+(i%2==0 ? -1 : 1)*newRadius);
        	smallerCircle.setCenterY(y+(i/2==0 ? -1 : 1)*newRadius);
        	smallerCircle.setRadius(newRadius);
        	int red = getR(image, depth, i);
        	int green = getR(image, depth, i);
        	int blue = getR(image, depth, i);
        	Color color = Color.rgb(red, green, blue);   

        	smallerCircle.setFill(color);
        	
        	// Draws the circle on the scene
        	((Pane) circle.getParent()).getChildren().add(smallerCircle);

            // Mouse event handler for circle splitting
        	smallerCircle.setOnMouseEntered(event -> {
        		if(!smallerCircle.getProperties().containsKey("split")) {
        			smallerCircle.getProperties().put("split", true);
        			splitCircle(image, smallerCircle, depth+1);
        		}
        	});
            
        }
    }
    
    // Gets the average R color value of the image
    private int getR(Image image, int depth, int i) {
        PixelReader pixelReader = image.getPixelReader();
        int height = (int) image.getHeight();
        int width = (int) image.getWidth();
        
        return extractAvg(image, "red", height / (depth+1), width / (depth+1));
    }
    
    private int getG(Image image, int depth, int i) {
        PixelReader pixelReader = image.getPixelReader();
        int height = (int) image.getHeight();
        int width = (int) image.getWidth();
        
        return extractAvg(image, "green", height / (depth+1), width / (depth+1));
    }
    
    private int getB(Image image, int depth, int i) {
        PixelReader pixelReader = image.getPixelReader();
        int height = (int) image.getHeight();
        int width = (int) image.getWidth();
        
        return extractAvg(image, "blue", height / (depth+1), width / (depth+1));
    }
    
    private int extractAvg(Image image, String color, int height, int width) {
    	int sum = 100;
    	int count = 0;
    	PixelReader pixelReader = image.getPixelReader();
    	for (int readY = 0; readY < height; readY++) {
            for (int readX = 0; readX < width; readX++) {
                Color c = pixelReader.getColor(readX, readY);
                if(color.equals("red")) {
            		sum += c.getRed();
            	}
            	else if(color.equals("green")) {
            		sum += c.getGreen();
            	} 
            	else if(color.equals("blue")) {
            		sum += c.getBlue();
            	}
                count++;
            }
        }
    	System.out.println("Sum" + sum);
    	System.out.println("Color" + color);
    	System.out.println("Height" + height);
    	System.out.println("Width" + width);
    	return sum/count;
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}
