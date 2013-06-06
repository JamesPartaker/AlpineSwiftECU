import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class Message {

	private Calendar cal;
	
	Message(){
		cal = Calendar.getInstance();
	}
	
	public Calendar getCalendar(){
		return cal;
	}
	
	public String toString(){
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		return dateFormat.format(cal.getTime());
	}
	
}
