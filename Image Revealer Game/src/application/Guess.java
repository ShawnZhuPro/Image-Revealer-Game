package application;

import java.util.LinkedList;
import java.util.Queue;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Guess {
	private static String userGuess;
	private static String category;
	private static String answer;
	private static VBox guessBox;
	private static Queue<String> guessHistory;
	private static long startTime;
	
	//Button template
	static String buttonTemplate = "-fx-font-family: 'Arial'; -fx-text-fill: 'blue'; -fx-color: 'Orange'; -fx-border-color: black; -fx-font-size: 15; -fx-text-fill: 'blue';";

	public Guess(Pane root, String answer, String category) {
		Guess.answer = answer;
		Guess.guessBox = new VBox(5);
		Guess.guessHistory = new LinkedList<>();
		Guess.category = category;
		initialize(root);
		startTimer();
	}


	private static void initialize(Pane root) {
		// User guess
		TextField guessField = new TextField();
		guessField.setPromptText("Enter your guess here");
		guessField.setLayoutX(500);  
		guessField.setLayoutY(50);   
		root.getChildren().add(guessField);

		// Checks if guess is correct then clears the field then displays guess on screen then redirects to win screen if guess is correct
		guessField.setOnAction(event -> {
			userGuess = guessField.getText();
			addGuess(userGuess);
			isCorrect(category);
			guessField.clear();
		});

		// Initialize guessBox inside a scrollable pane
		ScrollPane scrollPane = new ScrollPane(guessBox);
		scrollPane.setLayoutX(800);
		scrollPane.setLayoutY(50);
		scrollPane.setPrefSize(200, 500); 
		root.getChildren().add(scrollPane);
	}

	private static void startTimer() {
		startTime = System.currentTimeMillis();
	}

	public static int getElapsedTime() {
		return (int) (System.currentTimeMillis() - startTime) / 1000; // convert to seconds
	}


	public static String getUserGuess() {
		return userGuess;
	}

	public static String getAnswer() {
		return answer;
	}

	public static void showAnswer(Pane root) {
		Text answerText = new Text(300, 225, "Answer: " + answer);
		answerText.setStyle("-fx-font-size: 20");
		root.getChildren().add(answerText);
	}

	public static void addGuess(String guess) {
		guessHistory.add(guess);
		updateGuessBox();
	}

	public static void updateGuessBox() {
		guessBox.getChildren().clear();
		int guessNumber = 1;
		for (String guess : guessHistory) {
			guessBox.getChildren().add(new Text(guessNumber + ". " + guess));
			guessNumber++;
		}
	}

	public static boolean isCorrect(String category) {
		if (userGuess.toLowerCase().equals(answer.toLowerCase())) {
			int secondsTaken = getElapsedTime();
			showWinScreen(category, secondsTaken);
			return true;
		}
		else {
			return false;
		}
	}

	private static void showLoseScreen() {
		// Create a new pane for the lose screen
		Pane losePane = new Pane();
		// Add the winPane to the main application scene
		Main.changeRoot(losePane);

		losePane.setStyle("-fx-background: '#e86f66'");
		// Display a message
		Text loseMessage = new Text(300, 100, "Better luck next time!");
		loseMessage.setStyle("-fx-font-size: 24px; -fx-font-family: 'Arial';");
		losePane.getChildren().add(loseMessage);
		//Show leaderboard
		showLeaderboard(losePane, true);
	}
	
	public static void lost() {
		showLoseScreen();
	}
	
	private static void showWinScreen(String category, int secondsTaken) {
		// Create a new pane for the win screen
		Pane winPane = new Pane();
		// Add the winPane to the main application scene
		Main.changeRoot(winPane);

		winPane.setStyle("-fx-background: '#C3F0B4'");
		// Display a message
		Text winMessage = new Text(300, 100, "Congratulations! You've guessed correctly!");
		winMessage.setStyle("-fx-font-size: 24px; -fx-font-family: 'Arial';");
		winPane.getChildren().add(winMessage);

		// Add a text field for username input
		Text instructions = new Text(300,125, "Enter your username:");
		instructions.setStyle("-fx-font-size: 24px; -fx-font-family: 'Arial';");
		winPane.getChildren().add(instructions);
		TextField usernameField = new TextField();
		usernameField.setLayoutX(300);
		usernameField.setLayoutY(150);
		winPane.getChildren().add(usernameField);

		// Add a submit button
		Button submitButton = new Button("Submit");
		submitButton.setStyle(buttonTemplate);
		submitButton.setLayoutX(300);
		submitButton.setLayoutY(200);
		winPane.getChildren().add(submitButton);

		// Handle highscore leaderboard submit button click
		submitButton.setOnAction(event -> {
			String username = usernameField.getText();
			Highscores highscores = new Highscores("highscores.csv");
			highscores.addHighscore(username, category, highscores.calculateScore(ImageToCircleSplitter.getCirclesSplit(), secondsTaken), secondsTaken);
			showLeaderboard(winPane, false);
			submitButton.setDisable(true);  // Can only submit highscore once
		});
	}

	public static void showLeaderboard(Pane root, boolean cheated) {
		Highscores highscores = new Highscores("highscores.csv");

		VBox leaderboardBox = new VBox(5);
		ScrollPane scrollPane = new ScrollPane(leaderboardBox);
		scrollPane.setLayoutX(300);
		scrollPane.setLayoutY(250);
		scrollPane.setPrefSize(600, 400);
		root.getChildren().add(scrollPane);
		
		if(!cheated) {
	        // Display the last added high score separately
	        Text lastScore = new Text(highscores.getLastHighscore());
	        lastScore.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
	        leaderboardBox.getChildren().add(lastScore);
	    }
		
		// Fetch and display high scores in sorted order
		for (String highscore : highscores.getHighscores()) {
			leaderboardBox.getChildren().add(new Text(highscore));
		}

	}

}
