//import java.io.DataInputStream;
//import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Observable;
import java.util.Observer;


public class MessageCenter{
	
	private InputStream in;
	private OutputStream out;
	
	private MessageReader messageReader;
	
	//such a hack
	private ExternalObservable log;
	private ExternalObservable config;
	private ExternalObservable status;
	
	MessageCenter(InputStream inputStream, OutputStream outputStream){
		in = inputStream;
		out = outputStream;
		
		log = new ExternalObservable();
		config = new ExternalObservable();
		status = new ExternalObservable();
		
		try {
			int numToDiscard = in.available();
			System.out.println("Discarding " + numToDiscard + " bytes in input buffer!");
			in.skip(numToDiscard);
		} catch (IOException e1) { e1.printStackTrace(); }
		
		messageReader = new MessageReader(in);
		(new Thread(messageReader)).start();
		
		try {
			out.write("READY".getBytes("US-ASCII"));
		} catch (Exception e) {
			System.err.println("Error:" + e.getMessage());
		}
		
		System.out.println("Printed Ready!");
		
	}
	
	void addLogMessageListener(Observer o){
		log.addObserver(o);
	}
	
	void addConfigMessageListener(Observer o){
		config.addObserver(o);
	}
	
	void addStatusMessageListener(Observer o){
		status.addObserver(o);
	}
	
	void sendMessage(OutputMessage message){
		byte[] bytes = message.toByteBuffer().array(); //TODO: potentially unsafe, use .hasArray()
		
		try {
			out.write(bytes.length);
			out.write(message.getType().ordinal());
			out.write(bytes);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public class MessageReader implements Runnable{
		InputStream in;
		
		MessageReader(InputStream in){ this.in = in; }
	
		public void run() {
			Message message;
			
			try {
				
				while(true){
					
					System.out.println("Waiting to recieve message!");
					int messageLength = in.read();
					InputMessageType messageType = InputMessageType.values()[in.read()]; //TODO: handle error for this
					byte[] messageBody = new byte[messageLength];
					
					while(in.available() < messageLength){}
					in.read(messageBody);

					switch(messageType){ 
						case STATUS:
							message = new StatusMessage(ByteBuffer.wrap(messageBody));
							status.setChanged();
							status.notifyObservers(message);
							break;
						case CONFIG_INPUT:
							message = new ConfigInputMessage(ByteBuffer.wrap(messageBody));
							config.setChanged();
							config.notifyObservers(message);
							break;
						case LOG:
							message = new LogMessage(ByteBuffer.wrap(messageBody));
							log.setChanged();
							log.notifyObservers(message);
							break;
						default:
							message = null; //error
							break;
					}
					
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
}

class ExternalObservable extends Observable{

	public void setChanged(){
		super.setChanged();
	}

}
