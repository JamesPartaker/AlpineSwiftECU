import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Observable;
import java.util.Observer;


public class MessageCenter{

	DataInputStream in;
	DataOutputStream out;
	
	Observable log;
	Observable config;
	Observable status;
	
	MessageCenter(InputStream inputStream, OutputStream outputStream){
		in = new DataInputStream(inputStream);
		out = new DataOutputStream(outputStream);
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
		byte[] bytes = message.toByteBuffer().array(); //potentially unsafe, use .hasArray()
		
		try {
			out.writeByte(bytes.length);
			out.writeByte(message.getType().ordinal());
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
			ByteBuffer bytes;
			int length;
			try {
				while(true){
					byte messageLength = (byte)in.read();
					InputMessageType messageType = InputMessageType.values()[in.read()]; //TODO: handle error for this
					byte[] messageBody = new byte[messageLength];
					
					in.read(messageBody, 0, messageLength);
					
					switch(messageType){ 
						case LOG:
							message = new LogMessage(ByteBuffer.wrap(messageBody));
							break;
						case STATUS:
							message = new StatusMessage(ByteBuffer.wrap(messageBody));
							break;
						case CONFIG_INPUT:
							message = new ConfigInputMessage(ByteBuffer.wrap(messageBody));
							break;
						default:
							message = null; //error
							break;
					}
					
					//notify listeners that we have recieved a message
					//I don't THINK there is a problem with calling this from a separate thread, but I 
					//could be wrong.
					log.notifyObservers(message);
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
