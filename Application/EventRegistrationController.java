package Application;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class EventRegistrationController // Presentation Layer: User Interface Controller Class 
{

    @FXML
    private Tab RegistrationTab;
	
    @FXML
    private Label AgeLabel;

    @FXML
    private TextField AgeTextField;

    @FXML
    private Label ContactLabel;

    @FXML
    private TextField ContactTextField;

    @FXML
    private Label EmailLabel;

    @FXML
    private TextField EmailTextField;

    @FXML
    private ChoiceBox<String> EventChoiceBox;

    @FXML
    private Label EventLabel;

    @FXML
    private Label NameLabel;

    @FXML
    private TextField NameTextField;

    @FXML
    private Button SubmitButton;
    
    @FXML
    private Button UpdateButton;
    
    @FXML
    private Tab InformationTab;

    
    @FXML
    private TableView<Events> EventsTable;
    
    @FXML
    private TableColumn<Events, String> eventNameColumn;

    @FXML
    private TableColumn<Events, String> dateColumn;

   
    @FXML
    private TableColumn<Events, String> timeColumn;
    
    @FXML
    private TableColumn<Events, Integer> ageResColumn;

    @FXML
    void closeButtonPressed(ActionEvent event) 
    {
    	Platform.exit();
    }

    
    @FXML
    void SubmitButtonClicked(ActionEvent event) 
    {
    	// to check if all fields have been filled before submit button proceeds to save information into the data layer. 
    	if (AgeTextField.getText().isEmpty() || EmailTextField.getText().isEmpty() || ContactTextField.getText().isEmpty() || EventChoiceBox.getValue() == "Select an Event")
    	{
    		Alert alert = new Alert (AlertType.ERROR); // to display an alert that a field has not been filled 
    		alert.setTitle("Incomplete Information!");
    		alert.setHeaderText("Please check all fields and enter appropriate information.");
    		alert.setContentText("Cannot proceed without complete information");
    		alert.showAndWait();
    	}

    	// If all fields are filled, then to proceed 
    	else
    	{
    		if ((Integer.valueOf(AgeTextField.getText())>14 & (Integer.valueOf(AgeTextField.getText())<100))) // check if age entered is appropriate
    		{
    			// save all user input data to Attendees Table in EventRegistration Database when all conditions are met upon Submit button clicked
    			RegistrationLog Reg_Log = new RegistrationLog (NameTextField.getText(), EventChoiceBox.getValue(), EmailTextField.getText(), ContactTextField.getText(), (Integer.parseInt(AgeTextField.getText())));
    			Reg_Log.saveData();
        		// clear all fields after saving present information
        		EventChoiceBox.setValue("Select an Event");    		
        		NameTextField.clear();
        		EmailTextField.clear();
        		ContactTextField.clear();
        		AgeTextField.clear();
        		Alert alert = new Alert (AlertType.INFORMATION); // to display alert that the information has been submitted successfully
        		alert.setTitle("Success!");
        		alert.setHeaderText("You are registered, information submitted successfully.");
        		alert.setContentText("NOTE: Organizers reserve the right to deny entry if underaged, please bring a government issued ID for age verification for age restricted events.");
        		alert.show();
    		}
    		else 
    		{
    			Alert alert = new Alert (AlertType.ERROR); // to display an alert if age entered is less than 14 or more than 100
        		alert.setTitle("Check Age!");
        		alert.setHeaderText("Please check Age and enter correct information.");
        		alert.setContentText("Age is a mandatory field.");
        		alert.showAndWait();
    		}
    	}
    	
    }

    
    public void initialize() throws SQLException
    {
    	ArrayList<String> load = new ArrayList<String>();
        LoadEvents event_list = new LoadEvents();
        load = event_list.load(); //gets list of Event names from the Events Table in EventRegistration Database

    	// to initialize the list of Event names as an observable list for the UI ChoiceBox.
    	ObservableList<String> Events = FXCollections.observableArrayList(load);
    	EventChoiceBox.setItems(Events); 
    	EventChoiceBox.setValue("Select an Event");  // set default values before user chooses.
    	
        ArrayList<Events> upload = new ArrayList <Events>();
        LoadEvents ev_info = new LoadEvents();
        upload = ev_info.LoadInfo(); //gets information about all Events from the Events Table in EventRegistration Database
        // to initialize the Event information as an observable list for the UI TableView in the Information Tab.
        ObservableList<Events> EventInfo = FXCollections.observableArrayList(upload);
      
        // to arrange the information in the Tableview of the Events Information Tab of the UI
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<Events, String>("event_name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Events, String>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Events, String>("time"));
        ageResColumn.setCellValueFactory(new PropertyValueFactory<Events, Integer>("age_res"));
    
        // fill columns with the data 
        EventsTable.setItems(EventInfo);
           
    }
} // end class
