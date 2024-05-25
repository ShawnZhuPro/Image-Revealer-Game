package application;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class ImageToCircleSplitter {
	private Image image;
	private Pane root;
	private int targetDepth;
	private static int circlesSplit; 
	private int maxSize;
	private Text splitCountText;

	public ImageToCircleSplitter(String imagePath, Pane root, int maxSize, int targetDepth, String answer, String category) {
		this.image = new Image(imagePath);
		this.root = root;
		this.targetDepth = targetDepth;
		ImageToCircleSplitter.circlesSplit = 0;
		this.maxSize = maxSize;
		initialize(answer, category);
	}

	private void initialize(String answer, String category) {
		//Generic button template
		String buttonTemplate = "-fx-font-family: 'Lucida Calligraphy'; -fx-text-fill: 'blue'; -fx-color: 'Orange'; -fx-border-color: black; -fx-font-size: 15; -fx-text-fill: 'blue';";
		// Initialize initial big circle
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		Color avgColor = extractAvg(image, 0, 0, width, height);
		Circle bigCircle = new Circle(maxSize / 2, maxSize / 2, maxSize / 4, avgColor);
		root.getChildren().add(bigCircle);  // Adds bigCircle to the pane named "root" (basically displays the circle)

		// Initialize the Text object for circle splits
		splitCountText = new Text(500, 100, "Circles split: 0");
		root.getChildren().add(splitCountText);

		// Initialize static Guess class
		new Guess(root, answer, category);

		// Initialize answer button
		Button answerButton = new Button("Show Answer");
		answerButton.setStyle(buttonTemplate);
		answerButton.setLayoutX(600);
		answerButton.setLayoutY(100);
		root.getChildren().add(answerButton);

		// Initialize continue button (after answer is shown)
		Button continueButton = new Button("Continue");
		continueButton.setStyle(buttonTemplate);
		continueButton.setLayoutX(600);
		continueButton.setLayoutY(200);

		// Add mouse event handler for splitting the big starter circle
		// This mouse event occurs when the cursor "enters" a circle
		bigCircle.setOnMouseEntered(event -> {
			// If the circle being entered isn't already split, we classify it as now a split circle
			if (!bigCircle.getProperties().containsKey("split")) {
				bigCircle.getProperties().put("split", true);
				splitCircle(image, bigCircle, 0, 0, 0, width, height);
			}
		});

		// Shows answer then continue button to highscores
		answerButton.setOnMouseClicked(event -> {
			circlesSplit = 4096;
			Guess.showAnswer(root);
			root.getChildren().add(continueButton);
		});

		// Continue button action
		continueButton.setOnMouseClicked(event -> {
			// Redirects to highscores screen, but user can't enter info to save highscore
		    Pane winPane = new Pane();
		    Main.changeRoot(winPane);
			Guess.showLeaderboard(winPane, true);
		});
		
		// Add a text to display elapsed time
	    Text elapsedTimeText = new Text("Time Elapsed: 0 seconds");
	    elapsedTimeText.setLayoutX(300);
	    elapsedTimeText.setLayoutY(250);
	    root.getChildren().add(elapsedTimeText);

	    // Start a timeline to update elapsed time text every second
	    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
	        elapsedTimeText.setText("Time Elapsed: " + Guess.getElapsedTime() + " seconds");
	    }));
	    timeline.setCycleCount(Animation.INDEFINITE);
	    timeline.play();

	}

	private void splitCircle(Image image, Circle circle, int depth, int x0, int y0, int width, int height) {
		// Base case
		if (depth >= targetDepth) {
			return;
		}

		// Display circles split on screen
		circlesSplit++;
		splitCountText.setText("Circles split: " + getCirclesSplit());

		double newRadius = circle.getRadius() / 2;
		double x = circle.getCenterX();
		double y = circle.getCenterY();


		// X and Y directions for circles
		int[] dx = {-1, 1, -1, 1};
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

	public static int getCirclesSplit() {
		return ImageToCircleSplitter.circlesSplit;
	}
}