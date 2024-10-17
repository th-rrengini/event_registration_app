package Application;
//Presentation layer
//Main app that loads and starts the Event Registration App's GUI
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class main  extends Application 
{
	@Override
	public void start(Stage stage) throws Exception 
	{
		Parent root = FXMLLoader.load(getClass().getResource("EventRegistration.fxml"));
		Scene scene = new Scene(root); // attach scene graph to scene
		stage.setTitle("Lakehead University Events"); // displayed in window's title bar
		stage.setScene(scene); // attach scene to stage
		stage.show(); // display the stage
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		//create a main object and call its start method
				launch(args);
	}
}


