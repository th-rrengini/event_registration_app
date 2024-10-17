package Application;

import java.util.ArrayList;

public class Events // Middle Layer class Events creates Event objects which store data about an Event 
{
	private final String event_name;
	private String date;
	private String time;
	private int age_res;
	ArrayList<String> EventList = new ArrayList<String>();

	public Events(String event_name, String date, String time, int age_res) // constructor
	{
		super();
		this.event_name = event_name;
		this.date = date;
		this.time = time;
		this.age_res = age_res;
	}
	
	//getter methods to return data
	public String getEvent_name() 
	{
		return event_name;
	}
	
	public String getDate() 
	{
		return date;
	}
	public String getTime() 
	{
		return time;
	}
	public int getAge_res() 
	{
		return age_res;
	}

} // end class
