package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

	int targetDepth = 7; // Max # of iterations for splitCircle() method
	int maxSize = 800;   // Max size of the scene
	int circlesSplit = 0;
	int state = -1;

	/**
	 *
	 */
	@Override
	public void start(Stage stage) {
		if (state==-1) {
			Group startScreen = new Group();
			Stop[] stops = {new Stop(0, Color.PINK),new Stop(1, Color.ORANGE)};
			LinearGradient g = new LinearGradient(0,0,maxSize,maxSize, false, CycleMethod.REFLECT, stops);
			Scene startScreen1 = new Scene(startScreen, maxSize, maxSize, g);
			Label l = new Label("The Image Revealer Game");
			l.setStyle("-fx-border-color: black; -fx-padding: 10");
			l.setFont(new Font(40.0));
			l.setLayoutX(150);
			l.setLayoutY(200);
			startScreen.getChildren().add(l);
			stage.setTitle("Welcome");
			stage.setScene(startScreen1);
		}
		if (state==0) {
			//Start menu
			/*
			 * Categories:
			 *  -Historical Figures(Presidents)

				-Celebrities

				-Animals

				-Fruits
				
				-7 wonders
			 */
			Group introScreen = new Group();
			Scene introScreen1 = new Scene(introScreen, maxSize, maxSize, Color.BEIGE);
			stage.setTitle("Welcome!");
			Circle circle = new Circle(40,40,40);
			circle.setFill(Color.BLACK);
			Text text = new Text();
			text.setText("Hey there! Pick a category below to get started.");
			text.setX(100); text.setY(100);
			Button historyButton = new Button("Historical Figures");
			historyButton.setLayoutX(150); historyButton.setLayoutY(600);
			Button celebButton = new Button("Celebrities");
			celebButton.setLayoutX(300); celebButton.setLayoutY(600);
			Button animalButton = new Button("Animals");
			animalButton.setLayoutX(400); animalButton.setLayoutY(600);
			Button fruitButton = new Button("Fruits");
			fruitButton.setLayoutX(500); fruitButton.setLayoutY(600);
			EventHandler<ActionEvent> history_event = new EventHandler<ActionEvent>() { 
				public void handle(ActionEvent e) 
				{ 
					System.out.println("History button selected"); 
					state = 1;
					start(stage);
				} 
			}; 
			EventHandler<ActionEvent> celeb_event = new EventHandler<ActionEvent>() { 
				public void handle(ActionEvent e) 
				{ 
					System.out.println("Celebrity button selected"); 
					state = 2;
					start(stage);
				} 
			}; 
			EventHandler<ActionEvent> animal_event = new EventHandler<ActionEvent>() { 
				public void handle(ActionEvent e) 
				{ 
					System.out.println("Animal button selected"); 
					state = 3;
					start(stage);
				} 
			}; 
			EventHandler<ActionEvent> fruit_event = new EventHandler<ActionEvent>() { 
				public void handle(ActionEvent e) 
				{ 
					System.out.println("Fruit button selected"); 
					state = 4;
					start(stage);
				} 
			}; 
			historyButton.setOnAction(history_event);
			celebButton.setOnAction(celeb_event);
			animalButton.setOnAction(animal_event);
			fruitButton.setOnAction(fruit_event);
			introScreen.getChildren().add(text);
			introScreen.getChildren().add(circle);
			introScreen.getChildren().add(celebButton);
			introScreen.getChildren().add(historyButton);
			introScreen.getChildren().add(animalButton);
			introScreen.getChildren().add(fruitButton);
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
