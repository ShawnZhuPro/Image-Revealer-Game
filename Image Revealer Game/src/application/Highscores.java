package application;

import java.util.Scanner;
import java.io.File;

public class Highscores {
	private static String fileName;
	private static String name;
	private static int score;
	private static String category;
	private static String highscoreName;
	private static int highscore;
	private static String highscoreCategory;
	private static int circlesSplit;
	private static int secondsTaken;
	
	public Highscores(String fileName, String name, int score, String category) {
		Highscores.fileName = fileName;
		Highscores.name = name;
		Highscores.score = score;
		Highscores.category = category;
		initialize();
	}
	
	// Store highscores.csv data into hashmap
	private static void initialize() {
		while(true) {
			try {
				Scanner scanner = new Scanner(new File(fileName));
				
				while(scanner.hasNextLine()) {
					
				}
			}
		}
	}
	
	public void updateHighscoreName(String name) {
		Highscores.highscoreName = name;
	}
	
	public void updateHighScore(int score) {
		Highscores.highscore = score;
	}
	
	public void updateHighScoreCategory(String category) {
		Highscores.highscoreCategory = category;
	}
	
	// Scoring algorithm implementation
	public static double getScore() {
		return (4096 - circlesSplit) / (secondsTaken);
	}
}
