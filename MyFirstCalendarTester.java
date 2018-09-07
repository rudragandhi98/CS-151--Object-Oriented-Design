/**
 * This is a test for the CalendarAssignment class.
 * @author Rudra Gandhi
 * @copyright 6/22/18
 */
package hw1;
public class MyFirstCalendarTester
{
	public static void main(String[] args) throws Exception {
		String option = "";
		MyFirstCalendar fc =new MyFirstCalendar();
		System.out.println(MyFirstCalendar.year+" "+ MyFirstCalendar.month+" "+MyFirstCalendar.day+" "+MyFirstCalendar.date );
		while (!"Q".equalsIgnoreCase(option)) {
			System.out
			.println("Select one of the following options:\n[L]oad [V]iew by [C]reate, [G]o to [E]vent list [D]elete [Q]uit\n");
			MyFirstCalendar.showMonth(MyFirstCalendar.year, MyFirstCalendar.month, MyFirstCalendar.date);
			option = MyFirstCalendar.reader.readLine();
			switch (option) {
			case "L":
				MyFirstCalendar.loadCalendar();
				break;
			case "V":
				MyFirstCalendar.viewCalendar();
				break;
			case "C":
				MyFirstCalendar.createEvent();
				break;
			case "G":
				System.out.println("Enter the date : ");
				String s = MyFirstCalendar.reader.readLine();
				MyFirstCalendar.go(s);
				break;
			case "E":
				MyFirstCalendar.showEventList();
				break;
			case "D":
				MyFirstCalendar.deleteEvent();
				break;
			case "Q":
				return;
			default:
				System.out.println("Wrong option please select valid one!.");
				break;
			}
		}
	}
}