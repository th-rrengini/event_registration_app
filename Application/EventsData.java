package Application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.collections.ObservableList;

public class EventsData 
{
	// Data Layer that Handles data from Events Table in Database and passes a list of Event Names to be displayed in the UI Event Choice Box
	// and saves User input data from UI to the Attendees Table in Database
	
	String uname ="root";
	String password = "13041120";
	String url = "jdbc:mysql://localhost:3306/EventRegistration";
	ArrayList<String> EventList = new ArrayList<String>();

	public ArrayList<String> search() throws SQLException //method that returns a list of the names of events
	{
			Connection con = DriverManager.getConnection(url,uname,password);
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM Events");
			while (result.next() == true) 
			{
	            EventList.add(result.getString(2));	// creates a list of only the event names in the Events Table
			}

		   return EventList;
	}

	public ArrayList<Events> bringInfo() throws SQLException // method that returns information about all the events
	{
			Connection con = DriverManager.getConnection(url,uname,password);
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM Events");
			ArrayList<Events> EventArr = new ArrayList<Events>();
			while (result.next() == true) 
			{
			    EventArr.add(new Events(result.getString(2),result.getString(3), result.getString(4), result.getInt(5))); // creates an Array list of the event name, date, time, and age restriction
			}
		   return EventArr;
	}
	
	public void saveData(String AttendeeName, String EventName, String EmailID, String ContactNum, int Age) // method that saves User input data from the UI into the Attendees Table in the EventRegistration Database
	{
		
		try {
			Connection con = DriverManager.getConnection(url,uname,password);
			Statement statement = con.createStatement();
			statement.executeUpdate("INSERT INTO Attendees VALUES ('" + AttendeeName + "','" + EventName + "','" + EmailID + "','" + ContactNum +
					"','" + Age + "');"); // wrties data into the Attendees Table in the EventRegistration Database
			System.out.println("Success");
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}// end class
