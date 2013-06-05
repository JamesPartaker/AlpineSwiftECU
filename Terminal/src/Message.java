import java.util.Date;

public abstract class Message {

	Date date;
	
	Message(){
		date = new Date();
	}
	
	public Date getDate(){
		return date;
	}
	
}
