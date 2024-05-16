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
	int state = -1;

	/**
	 *
	 */
	@Override
	public void start(Stage stage) {
		Button backButton = new Button("Go Back");
		backButton.setLayoutY(900);
		backButton.setLayoutX(500);
		EventHandler<ActionEvent> back = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) 
			{ 
				System.out.println("History button selected"); 
				state = 0;
				start(stage);
			} 
		}; 
		backButton.setOnAction(back);
		if (state==-1) {
			Group startScreen = new Group();
			Stop[] stops = {new Stop(0, Color.PINK),new Stop(1, Color.ORANGE)};
			LinearGradient g = new LinearGradient(0,0,800,800, false, CycleMethod.REFLECT, stops);
			Scene startScreen1 = new Scene(startScreen, 800, 800, g);
			Label l = new Label("Visionary Voyage");
			l.setStyle("-fx-font-family: 'Lucida Calligraphy'; -fx-border-color: black; -fx-padding: 10; -fx-text-fill: 'blue';");
			l.setFont(new Font(40.0));
			l.setLayoutX(150);
			l.setLayoutY(200);
			Button start = new Button();
			start.setText("Start");
			EventHandler<ActionEvent> startButton = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					state = 0;
					start(stage);
				}
			};
			start.setOnAction(startButton);
			startScreen.getChildren().add(start);
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
			introScreen.getChildren().add(celebButton);
			introScreen.getChildren().add(historyButton);
			introScreen.getChildren().add(animalButton);
			introScreen.getChildren().add(fruitButton);
			introScreen.getChildren().add(backButton);
			stage.setScene(introScreen1);
		}
		
		if (state==1) {
			String[] pres_locs = {"file:./Image Revealer Game/src/Images/Presidents/GW-1.png", "file:./Image Revealer Game/src/Images/Presidents/JA-2.png", "file:./Image Revealer Game/src/Images/Presidents/JM-4.png", "file:./Image Revealer Game/src/Images/Presidents/JM-5.png", "file:./Image Revealer Game/src/Images/Presidents/TJ-3.png"};
			int random_img = (int)(Math.random()*pres_locs.length);
			Pane root = new Pane();
			Scene scene = new Scene(root, maxSize, maxSize);
			new ImageToCircleSplitter(pres_locs[random_img], root, maxSize, targetDepth);
			root.getChildren().add(backButton);
			stage.setScene(scene);
		}
		if (state==2) {
			String[] celeb_locs = {"file:./Image Revealer Game/src/Images/Celebrities/Brad_Pitt-3.jpg", "file:./Image Revealer Game/src/Images/Celebrities/Kevin_Hart-4.png", "file:./Image Revealer Game/src/Images/Celebrities/Lebron_James-5.png", "file:./Image Revealer Game/src/Images/Celebrities/Matthew_Mccounagey-2.png", "file:./Image Revealer Game/src/Images/Celebrities/Robert_Downey_Jr-1.jpg"};
			int random_img2 = (int)(Math.random()*celeb_locs.length);
			Pane root2 = new Pane();
			Scene scene2 = new Scene(root2, maxSize, maxSize);
			new ImageToCircleSplitter(celeb_locs[random_img2], root2, maxSize, targetDepth);
			root2.getChildren().add(backButton);
			stage.setScene(scene2);
		}
		if (state==3) {
			String[] animal_locs = {"file:./Image Revealer Game/src/Images/Animals/Eagle-4.png", "file:./Image Revealer Game/src/Images/Animal/Lion-3.png", "file:./Image Revealer Game/src/Images/Animals/Panda-1.png", "file:./Image Revealer Game/src/Images/Animals/Rhino-5.png", "file:./Image Revealer Game/src/Images/Animals/Tiger-2.png"};
			int random_img3 = (int)(Math.random()*animal_locs.length);
			Pane root3 = new Pane();
			Scene scene3 = new Scene(root3, maxSize, maxSize);
			new ImageToCircleSplitter(animal_locs[random_img3], root3, maxSize, targetDepth);
			root3.getChildren().add(backButton);
			stage.setScene(scene3);
		}
		if (state==4) {
			String[] fruit_locs = {"file:./Image Revealer Game/src/Images/Fruits/Apple-1.png", "file:./Image Revealer Game/src/Images/Fruits/Banana-2.png", "file:./Image Revealer Game/src/Images/Fruits/Grape-5.png", "file:./Image Revealer Game/src/Images/Fruits/Kiwi-4.png", "file:./Image Revealer Game/src/Images/Fruits/Orange-3.png"};
			int random_img4 = (int)(Math.random()*fruit_locs.length);
			Pane root4 = new Pane();
			Scene scene4 = new Scene(root4, maxSize, maxSize);
			new ImageToCircleSplitter(fruit_locs[random_img4], root4, maxSize, targetDepth);
			root4.getChildren().add(backButton);
			stage.setScene(scene4);
		}
        
		
		stage.setTitle("Guess Who");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
