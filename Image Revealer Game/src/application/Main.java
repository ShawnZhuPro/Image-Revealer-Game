package application;
	
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class Main extends Application {
	Image image;
	
	@Override
	public void start(Stage stage) {
		image = new Image("file:./src/Images/koala.png");
		ImageView imageView = new ImageView();
		
		// Set the dimensions of the ImageView
        imageView.setFitWidth(800);  // Set the desired width
        imageView.setFitHeight(600); // Set the desired height
        imageView.setPreserveRatio(true); // Maintain aspect ratio
        imageView.setImage(image);

	    //Setting the Scene object
		// Set the size of the scene to match the image dimensions
        Scene scene = new Scene(new Group(imageView), image.getWidth(), image.getHeight());
	    stage.setTitle("Displaying Image");
	    stage.setScene(scene);
	    stage.show();
		


	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
