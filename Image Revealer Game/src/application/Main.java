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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

	int targetDepth = 7; // Max # of iterations for splitCircle() method
	int maxSize = 1024;   // Max size of the scene
	int state = 0;

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
			Label l = new Label("Visionary Voyage");
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
      
			// Buttons for all categories
			Button historyButton = new Button("Historical Figures");
			historyButton.setLayoutX(150); historyButton.setLayoutY(600);
			Button celebButton = new Button("Celebrities");
			celebButton.setLayoutX(300); celebButton.setLayoutY(600);
			Button animalButton = new Button("Animals");
			animalButton.setLayoutX(400); animalButton.setLayoutY(600);
			Button fruitButton = new Button("Fruits");
			fruitButton.setLayoutX(500); fruitButton.setLayoutY(600);
      
			// History category
			EventHandler<ActionEvent> history_event = new EventHandler<ActionEvent>() { 
				public void handle(ActionEvent e) 
				{ 
					System.out.println("History button selected"); 
					state = 1;
					start(stage);
				} 
			}; 
      
			// Celebrity category
			EventHandler<ActionEvent> celeb_event = new EventHandler<ActionEvent>() { 
				public void handle(ActionEvent e) 
				{ 
					System.out.println("Celebrity button selected"); 
					state = 2;
					start(stage);
				} 
			}; 
      
			// Animal category
			EventHandler<ActionEvent> animal_event = new EventHandler<ActionEvent>() { 
				public void handle(ActionEvent e) 
				{ 
					System.out.println("Animal button selected"); 
					state = 3;
					start(stage);
				} 
			}; 
      
			// Fruit category
			EventHandler<ActionEvent> fruit_event = new EventHandler<ActionEvent>() { 
				public void handle(ActionEvent e) 
				{ 
					System.out.println("Fruit button selected"); 
					state = 4;
					start(stage);
				} 
			}; 
      
			// Initialize actions for buttons
			historyButton.setOnAction(history_event);
			celebButton.setOnAction(celeb_event);
			animalButton.setOnAction(animal_event);
			fruitButton.setOnAction(fruit_event);
      
			// Render buttons on intro screen
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
			new ImageToCircleSplitter("file:./Image Revealer Game/src/Images/Presidents/GW.png", root, maxSize, targetDepth);

        
      stage.setScene(scene);
		}
		stage.setTitle("Guess Who");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}