/**
 * The EventDetails class will store all of the information regarding events.
 * @author Rudra Gandhi
 * @copyright 6/22/18
 */
package hw1;
import java.util.Comparator;
public class EventDetails implements Comparator <EventDetails>
{
	public String title;
	public String day;
	public String month;
	public String date;
	public String startTime;
	public String endTime;

	/*
	 * This constructor initializes the instance variables
	 * @param title This is the first parameter to the constructor
	 * @param day This is the second parameter to the constructor
	 * @param month This is the third parameter to the constructor
	 * @param date This is the fourth parameter to the constructor
	 * @param startTime This is the fifth parameter to the constructor
	 * @param endTime This is the sixth parameter to the constructor
	 */
	public EventDetails(String title, String day, String month, String date, String startTime, String endTime){
		this.title=title;
		this.day=day;
		this.month=month;
		this.date=date;
		this.startTime=startTime;
		this.endTime=endTime;
	}

	/*
	 * @return Returns the title from the String 
	 */
	public String getTitle(){
		return title;
	}

	/*
	 * @return Returns the day from the String
	 */
	public String getDay(){
		return day;
	}

	/*
	 * @return Returns the month from the String 
	 */
	public String getMonth(){
		return month;
	}

	/*
	 * @return Returns the date from the String 
	 */
	public String getDate(){
		return date;
	}

	/*
	 * @return Returns the startTime from the String 
	 */
	public String getStartTime(){
		return startTime;
	}

	/*
	 * @return Returns the endTime from the String 
	 */
	public String getEndTime(){
		return endTime;
	}

	/*
	 * A method that compares the event one details from the second event,
	 * compares the date of both events, and the start time and end time of the events
	 * @param Object of EventDetails 
	 * @param The second object of EventDetails 
	 * @return Returns an integer in accordance to compareTo
	 */
	public int compare(EventDetails e1, EventDetails e2){
		if (e1.getMonth().compareTo(e2.getMonth())!=0){
			return e1.getMonth().compareTo(e2.getMonth());
		}
		else{
			if (e1.getDate().compareTo(e2.getDate())!=0){
				return e1.getDate().compareTo(e2.getDate());
			}
			else{
				if(e1.getStartTime().compareTo(e2.getEndTime())!=0){
					return e1.getStartTime().compareTo(e2.getEndTime());
				}
			}
		}
		return 0;
	}
}