package application;

import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class Guess {
	private static String userGuess;
	private static String answer;

	public Guess(Pane root, String answer) {
		Guess.answer = answer;
		initialize(root);
	}


	private static void initialize(Pane root) {
		// User guess
        TextField guessField = new TextField();
        guessField.setPromptText("Enter your guess here");
        guessField.setLayoutX(500);  
        guessField.setLayoutY(50);   
        root.getChildren().add(guessField);
        
        // User guess action
        guessField.setOnAction(event -> {
            userGuess = guessField.getText();
            System.out.println(userGuess); 
            guessField.clear(); // Clear the text field after submission
        });
	}
	
	public static String getUserGuess() {
		return userGuess;
	}
	
	public static String getAnswer() {
		return answer;
	}
	
	public static boolean isCorrect() {
		return (userGuess.equals(answer));
	}

}
