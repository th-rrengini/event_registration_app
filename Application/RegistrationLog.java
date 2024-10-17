package Application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistrationLog // Middle Layer - handles User input data of those who successfully register for an event
{
	private final String name;
	private final String event;
	private final String email;
	private final String contact_num;
	private final int age;
	public RegistrationLog(String name, String event, String email, String contact_num, int age) // constructor 
	{
		this.name = name;
		this.event = event;
		this.email = email;
		this.contact_num = contact_num;
		this.age = age;
	}
	
	// getter methods
	public String getName() 
	{
		return name;
	}
	public String getEvent() 
	{
		return event;
	}
	public String getEmail() 
	{
		return email;
	}
	public String getContact_num()
	{
		return contact_num;
	}
	public int getAge() 
	{
		return age;
	}
	
	public void saveData() // method to save User input data from UI to database by communicating through a database layer (EventsData)
	{
		EventsData data = new EventsData();
		data.saveData(getName(),getEvent(), getEmail() ,getContact_num(),getAge());	// uses saveData method of middle layer class EventsData
	}
	
} // end class
