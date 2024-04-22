package application;
	
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
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
//        Scene scene = new Scene(new Group(imageView), image.getWidth(), image.getHeight());
//	    stage.setTitle("Displaying Image");
//	    stage.setScene(scene);
//	    stage.show();
        
        splitCircle(stage, 300, 135, 100, 1);
	}
	
	// Method to calculate the average of all pixel colors
	public int average() {
		return 0;
	}
	
	// Recursively split circles
	// *TODO: figure out how to create multiple groups in a single stage (if that's feasible)
	public void splitCircle(Stage stage, int centerX, int centerY, int radius, int count) {
		
		
		for(int i = 0; i < count; i++) {
			//Drawing a Circle with properties
		    Circle circle = new Circle(centerX, centerY, radius); 
		}

	         

		   
	    //Creating a Group object  
		Group root = new Group(circle); 
		     
		//Creating a scene object 
		Scene scene = new Scene(root, 600, 300);  
		 
		//Setting title to the Stage 
		stage.setTitle("Drawing a Circle"); 
		     
		//Adding scene to the stage 
		stage.setScene(scene); 
		 
		// Set stage width/height to the dimensions we want
		stage.setWidth(800);
		stage.setHeight(600);
		     
		//Displaying the contents of the stage 
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
