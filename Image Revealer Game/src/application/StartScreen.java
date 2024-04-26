package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class StartScreen extends Application{
	int maxSize = 800;
	@Override
	public void start(Stage screen) throws Exception {
		// TODO Auto-generated method stub
		Pane root = new Pane();
		Scene scene = new Scene(root, maxSize, maxSize);
		Circle bigCircle = new Circle(maxSize / 2, maxSize / 2, maxSize / 4, Color.rgb((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)));
		root.getChildren().add(bigCircle);
	}
	
}
