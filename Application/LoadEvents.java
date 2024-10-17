package Application;

import java.sql.SQLException;
import java.util.ArrayList;

public class LoadEvents // Middle Layer - loads Data from Events Table in Database to the UI
{
	static ArrayList<String> loadList = new ArrayList<String>();

	public ArrayList<String> load() throws SQLException // method returns list of event names for Events ChoiceBox
	{
		EventsData ev = new EventsData();
		loadList = ev.search(); // uses search method of EventsData (Diddle layer)
		return loadList;
	}

	static ArrayList<Events> loadInfo = new ArrayList<Events>(); 

	public ArrayList<Events> LoadInfo() throws SQLException // method returns list of all event information (name, date, time, age restriction) for Information Tab
	{
		EventsData ev = new EventsData();
		loadInfo = ev.bringInfo(); // uses bringInfo method of EventsData (Data layer)
		return loadInfo;
	}
	
} // end class
