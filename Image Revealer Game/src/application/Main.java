package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

	int targetDepth = 7; // Max # of iterations for splitCircle() method
	int maxSize = 800;   // Max size of the scene
	int circlesSplit = 0;
	int state = 0;

	@Override
	public void start(Stage stage) {
		if (state==0) {
			Group introScreen = new Group();
			Scene introScreen1 = new Scene(introScreen, 800, 800, Color.BEIGE);
			stage.setTitle("Welcome!");
			Circle circle = new Circle(40,40,40);
			circle.setFill(Color.BLACK);
			Text text = new Text();
			text.setText("Hey there!");
			text.setX(100); text.setY(100);
			Button historyButton = new Button("History");
			historyButton.setLayoutX(200); historyButton.setLayoutY(200);
			Button englishButton = new Button("English");
			englishButton.setLayoutX(400); englishButton.setLayoutY(200);
			EventHandler<ActionEvent> history_event = new EventHandler<ActionEvent>() { 
	            public void handle(ActionEvent e) 
	            { 
	                System.out.println("History button selected"); 
	                state = 1;
	                start(stage);
	            } 
	        }; 
	        EventHandler<ActionEvent> english_event = new EventHandler<ActionEvent>() { 
	            public void handle(ActionEvent e) 
	            { 
	                System.out.println("English button selected"); 
	                state = 2;
	                start(stage);
	            } 
	        }; 
	        historyButton.setOnAction(history_event);
	        englishButton.setOnAction(english_event);
			introScreen.getChildren().add(text);
			introScreen.getChildren().add(circle);
			introScreen.getChildren().add(englishButton);
			introScreen.getChildren().add(historyButton);
			stage.setScene(introScreen1);
		}
		if (state==1) {
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
					circlesSplit++;
					splitCircle(bigCircle, 0);  // The entire algorithm is pretty much based off this
				}
			});

			stage.setScene(scene);
		}
		stage.setTitle("Guess Who");
		stage.show();
	}

	// TODO* Debug this method
	private void splitCircle(Circle circle, int depth) {
		// Base case
		if (depth >= targetDepth) {
			return;
		}

		circlesSplit++;
		System.out.println(circlesSplit);
		double newRadius = circle.getRadius() / 2;
		double x = circle.getCenterX();
		double y = circle.getCenterY();
		Color color = (Color) circle.getFill();

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
			smallerCircle.setFill(color);

			// Draws the circle on the scene
			((Pane) circle.getParent()).getChildren().add(smallerCircle);

			// Mouse event handler for circle splitting
			smallerCircle.setOnMouseEntered(event -> {
				if(!smallerCircle.getProperties().containsKey("split")) {
					smallerCircle.getProperties().put("split", true);
					splitCircle(smallerCircle, depth+1);
				}
			});

		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
