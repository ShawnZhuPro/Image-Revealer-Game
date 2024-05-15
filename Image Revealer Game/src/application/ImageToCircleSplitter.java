package application;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class ImageToCircleSplitter {
	private Image image;
	private Pane root;
	private int targetDepth;
	private int circlesSplit; 
	private int maxSize;
	
	public ImageToCircleSplitter(String imagePath, Pane root, int maxSize, int targetDepth) {
		this.image = new Image(imagePath);
		this.root = root;
		this.targetDepth = targetDepth;
		this.circlesSplit = 0;
		this.maxSize = maxSize;
		initialize();
	}
	
	private void initialize() {

		// Initialize initial big circle
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        Color avgColor = extractAvg(image, 0, 0, width, height);
        Circle bigCircle = new Circle(maxSize / 2, maxSize / 2, maxSize / 4, avgColor);
        TextField b = new TextField("Enter guess here");

        root.getChildren().add(b);
        root.getChildren().add(bigCircle);  // Adds bigCircle to the pane named "root" (basically displays the circle)
        
		

        // Add mouse event handler for splitting the big starter circle
        // This mouse event occurs when the cursor "enters" a circle
        bigCircle.setOnMouseEntered(event -> {
        	// If the circle being entered isn't already split, we classify it as now a split circle
            if (!bigCircle.getProperties().containsKey("split")) {
                bigCircle.getProperties().put("split", true);
                splitCircle(image, bigCircle, 0, 0, 0, width, height);
            }
        });
	}
	
	private void splitCircle(Image image, Circle circle, int depth, int x0, int y0, int width, int height) {
    	// Base case
        if (depth >= targetDepth) {
            return;
        }
        
        // Display circles split on screen
        circlesSplit++;
        Text text = new Text(500, 100, "Circles split: " + getCirclesSplit());
        root.getChildren().add(text);

        System.out.println(circlesSplit);
        
        double newRadius = circle.getRadius() / 2;
        double x = circle.getCenterX();
        double y = circle.getCenterY();

        
        // X and Y directions for circles
        int[] dx = {-1, 1, -1, 1};//
        int[] dy = {-1, -1, 1, 1};

        // Create and add 4 smaller circles
        for (int i = 0; i < 4; i++) {
        	// Logic that positions each circle in its expected spots
        	// Update attributes of each circle
        	int newX = x0 + (i%2) * (width / 2);
        	int newY = y0 + (i/2) * (height / 2);
        	int newW = width /2;
        	int newH = height / 2;
        	Color newColor = extractAvg(image, newX, newY, newW, newH);
        	// Initialize new circle
        	Circle smallerCircle = new Circle(x+dx[i]*newRadius, y+dy[i]*newRadius, newRadius, newColor);

        	// Draws the circle on the scene
        	((Pane) circle.getParent()).getChildren().add(smallerCircle);

            // Mouse event handler for circle splitting
        	smallerCircle.setOnMouseEntered(event -> {
        		if(!smallerCircle.getProperties().containsKey("split")) {
        			smallerCircle.getProperties().put("split", true);
        			splitCircle(image, smallerCircle, depth+1, newX, newY, newW, newH);
        		}
        	});
        	
            
            // Remove the current circle
            circle.setFill(Color.TRANSPARENT);
        }
    }
    
    private Color extractAvg(Image image, int x0, int y0, int width, int height) {
    	PixelReader pixelReader = image.getPixelReader();
    	int sumR = 0, sumG = 0, sumB = 0;
    	int count = 0;
    	
    	// Sampling colors from specific points within each quadrant to represent colors
    	//more accurately and reduce "washing" of colors
    	int[] samplePointsX = {
    			x0 + width / 4,  // Left boundary
    			x0 + width / 2,  // Middle boundary
    			x0 + 3 * width / 4  // Right boundary
    	};
       	int[] samplePointsY = {
    			y0 + height / 4,  // Left boundary
    			y0 + height / 2,  // Middle boundary
    			y0 + 3 * height / 4  // Right boundary
    	};
       	
       	// For every combination of horizontal and vertical sampling points, we compute the average RGB color
    	for (int y : samplePointsY) {
            for (int x : samplePointsX) {
                Color c = pixelReader.getColor(x, y);
                sumR += (long) (c.getRed() * 255);
                sumG += (long) (c.getGreen() * 255);
                sumB += (long) (c.getBlue() * 255);
                count++;
            }
        }
    	return Color.rgb((int) (sumR / count), (int) (sumG / count), (int) (sumB / count));
    }
    
    public int getCirclesSplit() {
    	return this.circlesSplit;
    }
}
