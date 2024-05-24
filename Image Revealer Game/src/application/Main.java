package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
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
	String dir = "file:./Image Revealer Game/src/";

	/**
	 *
	 */
	@Override
	public void start(Stage stage) {
		// Initialize back button & its action
		//Generic button template
		String buttonTemplate = "-fx-font-family: 'Lucida Calligraphy'; -fx-text-fill: 'blue'; -fx-color: 'Orange'; -fx-border-color: black; -fx-font-size: 15; -fx-text-fill: 'blue';";
		//Generic gradient to fill all the backgrounds with
		Stop[] stops1 = {new Stop(0, Color.PINK),new Stop(1, Color.ORANGE)};
		LinearGradient g1 = new LinearGradient(0,0,maxSize, maxSize, false, CycleMethod.REFLECT, stops1);
		BackgroundFill bg = new BackgroundFill(g1, null, null);
		Background bg1 = new Background(bg);
		Highscores.main(null);
		Button backButton = new Button("Go Back");
		backButton.setStyle(buttonTemplate);
		backButton.setLayoutX(600); backButton.setLayoutY(600);
		EventHandler<ActionEvent> back = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) 
			{ 
				state = 0;
				start(stage);
			} 
		}; 
		backButton.setOnAction(back);
		
        // Initialize skip button
        Button skipButton = new Button("Skip Image");
        skipButton.setStyle(buttonTemplate);
        skipButton.setLayoutX(600);
        skipButton.setLayoutY(150);

		
		
		if (state==-1) {
			Group startScreen = new Group();
			Stop[] stops = {new Stop(0, Color.PINK),new Stop(1, Color.ORANGE)};
			LinearGradient g = new LinearGradient(0,0,800,800, false, CycleMethod.REFLECT, stops);
			Scene startScreen1 = new Scene(startScreen, 800, 800, g);
			Label l = new Label("Visionary Voyage");
			l.setStyle("-fx-font-family: 'Lucida Calligraphy'; -fx-font-size: 60; -fx-border-color: black; -fx-padding: 10; -fx-text-fill: 'blue';");
			l.setLayoutX(100);
			l.setLayoutY(200);
			Button start = new Button();
			start.setText("Start");
			start.setLayoutX(300); start.setLayoutY(600);
			EventHandler<ActionEvent> startButton = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					state = 0;
					start(stage);
				}
			};
			start.setOnAction(startButton);
			startScreen.setStyle(buttonTemplate);
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
			Scene introScreen1 = new Scene(introScreen, maxSize, maxSize);
			introScreen1.setFill(g1);
			stage.setTitle("Welcome!");
			Text text = new Text();
			text.setText("Pick a category below to get started. Can you beat the \nhigh score of {highscore}?");
			text.setFont(new Font(40.0));
			text.setX(50); text.setY(100);
      
			// Buttons for all categories
			Button historyButton = new Button("Presidents");
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
			historyButton.setStyle(buttonTemplate);
			celebButton.setOnAction(celeb_event);
			celebButton.setStyle(buttonTemplate);
			animalButton.setOnAction(animal_event);
			animalButton.setStyle(buttonTemplate);
			animalButton.setLayoutX(animalButton.getLayoutX()+50);
			fruitButton.setOnAction(fruit_event);
			fruitButton.setStyle(buttonTemplate);
			fruitButton.setLayoutX(fruitButton.getLayoutX()+100);
      
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
			String[] pres_locs = {dir + "Presidents/BillClinton-8.png", dir + "Presidents/GeorgeWBush-6.png", 
					dir + "Presidents/GW-1.png", dir + "Presidents/HarryTruman-10.png", dir + "Presidents/JFK-4.png", 
					dir+"Presidents/JoeBiden-5.png",
					dir+"Presidents/Obama-3.png", dir+"Presidents/RichardNixon-9.png", dir+"Presidents/RonaldReagan-7.png"};
			String[] pres_ans = {"Bill Clinton", "George Bush", "George Washington", "Harry Truman", "John F Kennedy", "Joe Biden",
					"Barack Obama", "Richard Nixon", "Ronald Reagan"};
			int random_img = (int)(Math.random()*pres_locs.length);
			Pane root = new Pane();
			root.setBackground(bg1);
			Scene scene = new Scene(root, maxSize, maxSize);
			new ImageToCircleSplitter(pres_locs[random_img], root, maxSize, targetDepth, pres_ans[random_img]);
			backButton.setLayoutY(100);
			backButton.setLayoutX(500);
			root.getChildren().add(backButton);
	        root.getChildren().add(skipButton);
	        // Skips image
	        skipButton.setOnMouseClicked(event -> {
	        	// Go to new image in same category
	        	start(stage);
	        });
			stage.setScene(scene);
		}
		if (state==2) {
			String[] celeb_locs = {dir + "Celebrities/Andrew_Garfield-10.png", dir + "Celebrities/Brad_Pitt-2.jpg", dir + "Celebrities/Chris_Evans-6.png", dir + "Celebrities/Chris_Hemsworth-7.png", dir + "Celebrities/Kevin_Hart-4.png",
					dir+"Celebrities/Lebron_James-5.png", dir+"Celebrities/Matthew_Mccounagey-3.png", dir+"Celebrities/Robert_Downey_Jr-1.jpg",
					dir+"Celebrities/Tom_Holland-8.png", dir+"Celebrities/Zendaya-9.png"};
			String[] pres_ans = {"Andrew Garfield", "Brad Pitt", "Chris Evans", "Chris Hemsworth", "Kevin Hart", "Lebron James",
					"Matthew Mccounagey", "Robert Downey Jr", "Tom Holland", "Zendaya"};
			int random_img = (int)(Math.random()*celeb_locs.length);
			Pane root = new Pane();
			root.setBackground(bg1);
			Scene scene = new Scene(root, maxSize, maxSize);
			new ImageToCircleSplitter(celeb_locs[random_img], root, maxSize, targetDepth, pres_ans[random_img]);
			backButton.setLayoutY(100);
			backButton.setLayoutX(500);
			root.getChildren().add(backButton);
	        root.getChildren().add(skipButton);
	        // Skips image
	        skipButton.setOnMouseClicked(event -> {
	        	// Go to new image in same category
	        	start(stage);
	        });
			stage.setScene(scene);
		}
		if (state==3) {
			String[] animal_locs = {dir+"Animals/Dog-10.jpg", dir+"Animals/Eagle-4.png", dir+"Animals/Fox-9.jpg", dir+"Animals/Lion-3.png",
					dir+"Animals/Panda-1.png", dir+"Animals/Rhino-5.png", dir+"Animals/Shark-8.jpg", dir+"Tiger-2.png",
					dir+"Animals/Vulture-7.jpg", dir+"Animals/Zebra-6.jpg"};
			String[] pres_ans = {"Dog", "Eagle", "Fox", "Lion", "Panda", "Rhino", "Shark", "Tiger", "Vulture", "Zebra"};
			int random_img = (int)(Math.random()*animal_locs.length);
			Pane root = new Pane();
			root.setBackground(bg1);
			root.setBackground(bg1);
			Scene scene = new Scene(root, maxSize, maxSize);
			new ImageToCircleSplitter(animal_locs[random_img], root, maxSize, targetDepth, pres_ans[random_img]);
			backButton.setLayoutY(100);
			backButton.setLayoutX(500);
			root.getChildren().add(backButton);
	        root.getChildren().add(skipButton);
	        // Skips image
	        skipButton.setOnMouseClicked(event -> {
	        	// Go to new image in same category
	        	start(stage);
	        });
			stage.setScene(scene);
		}
		if (state==4) {
			String[] fruit_locs = {dir + "Fruits/Apple-1.png", dir + "Fruits/Banana-2.png", dir + "Fruits/Dragonfruit-6.jpg", dir + "Fruits/Durian-8.jpg", dir + "Fruits/Grape-5.png",
					dir+"Fruits/Jackfruit-9.jpg", dir+"Fruits/Kiwi-4.png", dir+"Fruits/Lychee-10.jpg", dir+"Fruits/Orange-3.png", dir+"Fruits/Passionfruit-7.jpg"};
			String[] pres_ans = {"Apple", "Banana", "Dragonfruit", "Durian", 
					"Grape", "Jackfruit", "Kiwi", "Lychee", "Orange", "Passionfruit"};
			int random_img = (int)(Math.random()*fruit_locs.length);
			Pane root = new Pane();
			root.setBackground(bg1);
			Scene scene = new Scene(root, maxSize, maxSize);
			new ImageToCircleSplitter(fruit_locs[random_img], root, maxSize, targetDepth, pres_ans[random_img]);
			backButton.setLayoutY(100);
			backButton.setLayoutX(500);
			root.getChildren().add(backButton);
	        root.getChildren().add(skipButton);
	        // Skips image
	        skipButton.setOnMouseClicked(event -> {
	        	// Go to new image in same category
	        	start(stage);
	        });
			stage.setScene(scene);
		}
        
		
		stage.setTitle("Guess Who");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
