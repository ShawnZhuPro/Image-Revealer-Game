package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Highscores {
    private String fileName;
    private static ArrayList<String[]> highScores;

    public Highscores(String fileName) {
        this.fileName = fileName;
        Highscores.highScores = new ArrayList<>();
        initialize();
    }

    // Load highscores from the file
    private void initialize() {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                if (data.length == 4 && isNumeric(data[2]) && isNumeric(data[3])) {
                    highScores.add(data);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while loading high scores: " + e.getMessage());
        }
    }
    
    // Check if a string is numeric
    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Add a new highscore and save it to the file
    public void addHighscore(String name, String category, int score, int time) {
        highScores.add(new String[]{name, category, String.valueOf(score), String.valueOf(time)});
        saveHighscores();
    }

    // Save static highscores to the file
    private void saveHighscores() {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            for (String[] entry : highScores) {
                writer.println(String.join(",", entry));
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while saving high scores: " + e.getMessage());
        }
    }
    
    // Get high scores as a list of formatted strings for display, sorted by score
    public ArrayList<String> getHighscores() {
    	// Sorts list
    	highScores.sort((a, b) -> Integer.compare(Integer.parseInt(b[2]), Integer.parseInt(a[2])));
        ArrayList<String> formattedScores = new ArrayList<>();
        for (String[] entry : highScores) {
            formattedScores.add("Name: " + entry[0] + ", Category: " + entry[1] + ", Score: " + entry[2] + ", Time: " + entry[3]);
        }
        return formattedScores;
    }
    
    // Get	the last added high score
    public String getLastHighscore() {
        String[] lastEntry = highScores.get(highScores.size() - 1);
        //Name, Cat, Score, Time
        return "Your Score - Name: " + lastEntry[0] + ", Category: " + lastEntry[1] + ", Score: " + lastEntry[2] + ", Time: " + lastEntry[3];
    }
    public int getHighScore() {
    	String[] lastEntry = highScores.get(highScores.size() - 1);
    	return Integer.parseInt(lastEntry[1]);
    }
    // Update user score details
    public void updateScoreDetails(int circlesSplit, int secondsTaken, String name, String category) {
        int score = calculateScore(circlesSplit, secondsTaken);
        addHighscore(name, category, score, secondsTaken);
    }

    // Scoring algorithm implementation
    public int calculateScore(int circlesSplit, int secondsTaken) {
        return (4096 - circlesSplit) / secondsTaken;
    }

}
