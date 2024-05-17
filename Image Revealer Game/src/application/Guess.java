package application;

import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

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
        
        // Checks if guess is correct then clears the field then displays guess on screen then redirects to win screen if guess is correct
        guessField.setOnAction(event -> {
            userGuess = guessField.getText();
            System.out.println(userGuess); 
            System.out.println(isCorrect());
            guessField.clear(); 
            
        });
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
	
	public static boolean isCorrect() {
		return (userGuess.toLowerCase().equals(answer.toLowerCase()));
	}

}
