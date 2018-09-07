/**
 * The MyFirstCalendar class carries out functions such as load, view by, go to, and event list to display the calendar. 
 * @author Rudra Gandhi
 * @copyright 6/22/18
 */
package hw1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class MyFirstCalendar {
	public static int year;
	public static int month;
	public static int day;
	public static int date;
	public static ArrayList<EventDetails> eventsList = new ArrayList<EventDetails>();
	public static BufferedReader fileReader;
	public static BufferedWriter filewriter;
	public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	/*
	 * This is the constructor for CalendarAssignment 
	 */
	public MyFirstCalendar(){
		int[] tmp = getDayDate(new Date());
		year = tmp[0];
		month = tmp[1];
		day = tmp[2];
		date = tmp[3];       
	} 

	/*
	 * A method that receives year, month, day of week, and date from the calendar. 
	 * @param Date dateObj This gives the parameter for the date object 
	 * @return Returns an array of integers
	 */
	public static int[] getDayDate(Date dateObj) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateObj);
		int[] tmp = new int[4];
		tmp[0] = cal.get(Calendar.YEAR);
		tmp[1] = cal.get(Calendar.MONTH) + 1;
		tmp[2] = cal.get(Calendar.DAY_OF_WEEK);
		tmp[3] = cal.get(Calendar.DATE);
		return tmp;
	}

	/*
	 * This method creates an event on the calendar by entering, title, date, and start time/end time
	 */
	public static void createEvent() throws Exception {
		if(filewriter == null)
		{
			try
			{
				File file = new File("event.txt");
				filewriter = new BufferedWriter(new FileWriter(file));
			}
			catch(Exception e)
			{
				System.out.println("The First run! File is empty");
			}
		}
		System.out.println("Enter a Title: ");
		String title = reader.readLine();
		filewriter.append(title + "^");
		System.out.println("Enter a date: ");
		String date = reader.readLine();
		filewriter.append("\n"+ date + "^");
		System.out.println("Enter a start time: ");
		String startTime = reader.readLine();
		filewriter.append("\n" + startTime + "^");
		System.out.println("Enter a end time OR "+ "press N if not applicable "+   ": ");
		String endTime = reader.readLine();
		filewriter.append("\n"+ endTime + "^");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date d = sdf.parse(date);
		int[] tmp = getDayDate(d);
		System.out.println("Month is "+tmp[1]);
		String yearLocal = Integer.toString(tmp[0]);
		String monthLocal = getMonthName(tmp[1]);
		String dayLocal = getDayName(tmp[2]);
		String dateLocal = Integer.toString(tmp[3]);
		EventDetails ed=new EventDetails(title, dayLocal, monthLocal, dateLocal, startTime, endTime);
		eventsList.add(ed);
		System.out.println(eventsList.get(0).getTitle()+" "+eventsList.get(0).getMonth()+" "+eventsList.get(0).getDate() );
	}    

	/*
	 * @return Returns the current date information 
	 */
	public static String getCurrentDateInfo() {
		return getDayName(day) + "," + getMonthName(month) + " " + getDate()+ "," + getYear();}

	/*
	 * This method views the calendar according to day or month
	 */
	public static void viewCalendar() throws Exception {
		System.out.println("[D]ay view or [M]view ?");
		String op = reader.readLine();
		String monthLocal = getMonthName(month);
		String dateLocal= Integer.toString(getDate());
		if ("D".equalsIgnoreCase(op)) {
			System.out.println(getCurrentDateInfo());
			dateLocal = Integer.toString(getDate());
			showEvents(monthLocal, dateLocal);
		}
		else if("M".equalsIgnoreCase(op)){
			System.out.println(monthLocal+getYear());
			showMonthView(month, getYear());
		}
		System.out.println("[P]revious or [N]ext or [M]ain menu ?");
		return;
	}    
	
	/*
	 * This method prints the events according to the order of month and date in the eventsList
	 * @param month This parameter is used to print months 
	 * @param date This parameter is used to print dates
	 */
	public static void showEvents(String month, String date) {
		boolean check = true;
		if (month.equalsIgnoreCase("N/A")) {
			check = false;
		}
		System.out.println(check+" "+month+" "+date);
		for (int i = 0; i < eventsList.size(); i++) {
			if (check && eventsList.get(i).getMonth().equals(month) && eventsList.get(i).getDate().equals(date)) {
				System.out.println(eventsList.get(i).getTitle()+" "+eventsList.get(i).getStartTime()+"-"+eventsList.get(i).getEndTime());
			}
		}
	}

	/*
	 * This method prints the events in a list in the order of day, month, date, start time, end time, and title
	 */
	public static void showEventList(){
		for(int i=0;i<eventsList.size();i++){
			System.out.println(eventsList.get(i).getDay()+" "+eventsList.get(i).getMonth()+" "+eventsList.get(i).getDate()+" "+
					eventsList.get(i).getStartTime()+"-"+eventsList.get(i).getEndTime()+" "+eventsList.get(i).getTitle());
		} 
	}

	/*
	 * This method navigates to the event that has been made already in the eventList
	 * @param date This parameter is to go to the date of the event to locate the dates for certain events
	 */
	public static void go(String date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date d = sdf.parse(date);
		System.out.println(date+" "+d);
		int[] tmp = getDayDate(d);
		String monthLocal = getMonthName(tmp[1]);
		String dateLocal = Integer.toString(tmp[3]);
		System.out.println(monthLocal+" "+dateLocal);
		showEvents(monthLocal, dateLocal);
	}

	/*
	 * This method is to delete an event that has been created and
	 * to be removed from the eventList
	 */
	public static void deleteEvent() throws Exception {
		System.out.println("[S]elected or [A]ll ?");
		String op = reader.readLine();
		if ("S".equalsIgnoreCase(op)) {
			System.out.println("Enter date : ");
			String date = reader.readLine();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date d = sdf.parse(date);
			int[] tmp = getDayDate(d);
			String monthLocal = getMonthName(tmp[1]);
			String dateLocal = Integer.toString(tmp[3]);
			for (int i = 0; i < eventsList.size(); i++) {
				if (eventsList.get(i).getMonth().equals(monthLocal) && eventsList.get(i).getDate().equals(dateLocal)) {
					eventsList.remove(i);
				}
			}
		}
		else if("A".equalsIgnoreCase(op)){
			for(int i=0;i<eventsList.size();i++){
				eventsList.remove(i);
			}
		}
		else{
			System.out.println("Invalid option chosen.");
		}
	}

	/*
	 * This method is to load the calendar and to respond to the user when an error occurs
	 */
	public static void loadCalendar() {
		if (fileReader == null) {
			System.out.println("\nThis is first time run!. Nothing is there!\n");
			try {
				File file = new File("event.txt");
				file.createNewFile();
				fileReader = new BufferedReader(new FileReader(file));
				filewriter = new BufferedWriter(new FileWriter(file));
			} catch (Exception e) {
				System.out.println("This is first time run!. Nothing is there!");
			}
		} else {
			eventsList.clear();
			String line = null;
			try {
				while ((line = fileReader.readLine()) != null) {
					System.out.println(line);
				}
				System.out.println("\n\nEvents are loaded successfully!.\n");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * @return Returns day 
	 */
	public static int getDay() {
		return day;
	}

	/*
	 * @return Returns month
	 */
	public static int getMonth() {
		return month;
	}

	/*
	 * @return Returns year
	 */
	public static int getYear() {
		return year;
	}

	/*
	 * @return Returns date 
	 */
	public static int getDate() {
		return date;
	}

	/*
	 * This method is to print the month in the order of year, month, and the current date
	 * @param year This parameter gives the year 
	 * @param month This parameter gives the month
	 * @param todayDate This parameter gives the current date 
	 */
	public static void showMonth(int year, int month, int todayDate) {
		int startDay = getBeginingDay(year, month);
		int numOfDaysInMonth = getNumOfDaysInMonth(year, month);
		showMonthTitle(year, month);
		showMonthBody(startDay, numOfDaysInMonth, todayDate);
	}

	/*
	 * This method displays the month and prints the month in a orderly matter
	 * @param year This parameter gives the year
	 * @param month This parameter gives the month
	 */
	public static void showMonthView(int year, int month) {
		int startDay = getBeginingDay(year, month);
		int numOfDaysInMonth = getNumOfDaysInMonth(year, month);
		System.out.println(getMonthName(month)+getYear());
	}

	/*
	 * This method gets the total number of days since the 1st of January year 1800
	 * @param year This parameter gives the year
	 * @param month This parameter gives the month
	 * @return Returns the total number of days in the month
	 */
	public static long getTotalNumOfDays(int year, int month) {
		long total = 0;
		for (int i = 1800; i < year; i++)
			if (isLeapYear(i))
				total = total + 366;
			else
				total = total + 365;
		for (int i = 1; i < month; i++)
			total = total + getNumOfDaysInMonth(year, i);
		return total;
	}
	
	/*
	 * This method gets the start of the first day in a month
	 * @param year This parameter gives the year
	 * @param month This parameter gives the month
	 * @return Returns the total number of days and adds it to the start day of 1800 
	 * then takes the modulus of 7
	 */
	public static int getBeginingDay(int year, int month) {
		int startDay1800 = 3;
		long totalNumOfDays = getTotalNumOfDays(year, month);
		return (int) ((startDay1800 + totalNumOfDays) % 7);
	}


	/*
	 * This method gets the number of days in a month
	 * @param year This parameter gives the year
	 * @param month This parameter gives the month
	 * @return Returns an integer 0 if the month is incorrect
	 */
	public static int getNumOfDaysInMonth(int year, int month)
	{
		int numOfDaysInMonth = 0;
		switch(month)
		{
		case 1: 
			numOfDaysInMonth = 31;
			break;
		case 2: 
			if(isLeapYear(year))
				numOfDaysInMonth = 29;
			else
				numOfDaysInMonth = 28;
			break;
		case 3: 
			numOfDaysInMonth = 31;
			break;
		case 4:
			numOfDaysInMonth = 30;
			break;
		case 5: 
			numOfDaysInMonth = 31;
			break;
		case 6: 
			numOfDaysInMonth = 30;
			break;
		case 7:
			numOfDaysInMonth = 31;
			break;
		case 8: 
			numOfDaysInMonth = 31;
			break; 
		case 9: 
			numOfDaysInMonth = 30;
			break;
		case 10: 
			numOfDaysInMonth = 31;
			break;
		case 11: 
			numOfDaysInMonth = 30;
			break;
		case 12: 
			numOfDaysInMonth = 31;
			break;

		default: 
			numOfDaysInMonth = 0;
			break;
		}
		return numOfDaysInMonth;
	}

	
	/*
	 * This method determines if it is a leap year
	 * @param year This parameter gives the year
	 * @return Returns false if it is not a leap year
	 */
	public static boolean isLeapYear(int year) {
		if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0)))
			return true;
		return false;
	}

	/*
	 * This method prints the body of the month
	 * @param startDay This parameter gives the startDay of the month
	 * @param numOfDaysInMonth This parameter gives the number of days in the month 
	 * @param todayDate This parameter gives the current date
	 */
	public static void showMonthBody(int startDay, int numOfDaysInMonth, int todayDate) {
		int i = 0;
		for (i = 0; i < startDay; i++)
			System.out.print(" ");
		for (i = 1; i <= numOfDaysInMonth; i++) {
			String tmp = String.valueOf(i);
			String space = "";
			if (i == todayDate) {
				tmp = "[" + tmp;
			}
			if (i < 10) {
				if (tmp.contains("[")) {
					space = " ";
				} else {
					space = " ";
				}
			} else {
				if (tmp.contains("[")) {
					space = " ";
				} else {
					space = " ";
				}
			}
			System.out.print(space + tmp);
			if (i == todayDate) {
				System.out.print("]");
			}
			if ((i + startDay) % 7 == 0)
				System.out.println();
		}
		System.out.println();
	}


	/*
	 * This method prints the month title
	 */
	public static void showMonthTitle(int year, int month) {
		System.out.println(" " + getMonthName(month) + ", " + year);
		System.out.println("-----------------------------------");
		System.out.println(" Sun Mon Tue Wed Thu Fri Sat");
	}

	/*
	 * This method gets the English name for the month
	 * @param month This parameter gives month
	 * @return Returns the months name
	 */
	public static String getMonthName(int month) {
		String monthName = null;
		switch (month) {
		case 1:
			monthName = "January";
			break;
		case 2:
			monthName = "February";
			break;
		case 3:
			monthName = "March";
			break;
		case 4:
			monthName = "April";
			break;
		case 5:
			monthName = "May";
			break;
		case 6:
			monthName = "June";
			break;
		case 7:
			monthName = "July";
			break;
		case 8:
			monthName = "August";
			break;
		case 9:
			monthName = "September";
			break;
		case 10:
			monthName = "October";
			break;
		case 11:
			monthName = "November";
			break;
		case 12:
			monthName = "December";
		}
		return monthName;
	}

	/*
	 * This method gets the English name for days
	 * @param dayLocal This parameter gives the day
	 * @return Returns the name of the day in English
	 */
	public static String getDayName(int dayLocal) {
		String dayName = null;
		switch (dayLocal) {
		case 1:
			dayName = "Sunday";
			break;
		case 2:
			dayName = "Monday";
			break;
		case 3:
			dayName = "Tuesday";
			break;
		case 4:
			dayName = "Wednessday";
			break;
		case 5:
			dayName = "Thursday";
			break;
		case 6:
			dayName = "Friday";
			break;
		case 7:
			dayName = "Saturday";
		}
		return dayName;
	}
}