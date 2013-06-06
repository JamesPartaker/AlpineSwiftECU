import java.util.Observable;
import java.util.Observer;


public class Console implements Observer {

	Console(MessageCenter mc){
		mc.addConfigMessageListener(this);
		mc.addLogMessageListener(this);
		mc.addStatusMessageListener(this);
	}
	
	public void update(Observable observable, Object obj) {
		System.out.println(obj.toString());
	}
	
	public static void main(String[] args){
		
		try{
			SerialConnection sc = new SerialConnection();
			sc.open("/dev/tty.usbmodem411");
			
			//TODO: remove the need for this with a handshake
			try{
				Thread.sleep(2000);
			}catch(Exception e){}
			
			MessageCenter mc = new MessageCenter(sc.getInputStream(), sc.getOutputStream());
			
			@SuppressWarnings("unused")
			Console console = new Console(mc);
			
			
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
	}

}


