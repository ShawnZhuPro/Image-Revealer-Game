package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Highscores {
    private static String fileName;
    private static ArrayList<String[]> highScores;

    public Highscores(String fileName) {
        this.fileName = fileName;
        this.highScores = new ArrayList<>();
        initialize();
    }

    // Load highscores from the file
    private static void initialize() {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                highScores.add(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while loading high scores: " + e.getMessage());
        }
    }

    // Add a new highscore and save it to the file
    public static void addHighScore(String name, String category, int score) {
        highScores.add(new String[]{name, category, String.valueOf(score)});
        Highscores.saveHighScores();
    }

    // Save static highscores to the file
    private static void saveHighScores() {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            for (String[] entry : highScores) {
                writer.println(String.join(",", entry));
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while saving high scores: " + e.getMessage());
        }
    }

    // Display high scores
    public static void displayHighScores() {
        for (String[] entry : highScores) {
            System.out.println("Name: " + entry[0] + ", Category: " + entry[1] + ", Score: " + entry[2]);
        }
    }

    // Update user score details
    public static void updateScoreDetails(int circlesSplit, int secondsTaken, String name, String category) {
        int score = Highscores.calculateScore(circlesSplit, secondsTaken);
        addHighScore(name, category, score);
    }

    // Scoring algorithm implementation
    private static int calculateScore(int circlesSplit, int secondsTaken) {
        return (4096 - circlesSplit) / secondsTaken;
    }

//    // Main method to demonstrate functionality (for testing purposes)
//    public static void main(String[] args) {
//        Highscores highscores = new Highscores("highscores.csv");
//
//        // Simulate user details
//        highscores.updateScoreDetails(500, 120, "User1", "Category1");
//
//        highscores.displayHighScores();
//    }
}
