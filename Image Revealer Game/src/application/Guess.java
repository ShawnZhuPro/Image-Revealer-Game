package application;

import java.util.LinkedList;
import java.util.Queue;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Guess {
	private static String userGuess;
	private static String answer;
	private static VBox guessBox;
	private static Queue<String> guessHistory;

	public Guess(Pane root, String answer) {
		Guess.answer = answer;
		Guess.guessBox = new VBox(5);
		Guess.guessHistory = new LinkedList<>();
		initialize(root);
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
            System.out.println(userGuess); 
            System.out.println(isCorrect());
            guessField.clear();
        });
        
        // Initialize guessBox inside a scrollable pane
        ScrollPane scrollPane = new ScrollPane(guessBox);
        scrollPane.setLayoutX(800);
        scrollPane.setLayoutY(50);
        scrollPane.setPrefSize(200, 500); 
        root.getChildren().add(scrollPane);
	}
	
	public static String getUserGuess() {
		return userGuess;
	}
	
	public static String getAnswer() {
		return answer;
	}
	
	public static void showAnswer(Pane root) {
        Text answerText = new Text(500, 200, "Answer: " + answer);
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
	
	public static boolean isCorrect() {
		return (userGuess.toLowerCase().equals(answer.toLowerCase()));
	}

}
