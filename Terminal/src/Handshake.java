import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class Handshake {

	InputStream in;
	OutputStream out;
	
	private static final String REQUEST = "marco";
	private static final String RESPONSE = "polo";
	
	private static final int REQUEST_PERIOD = 50; //milliseconds
	
	Handshake(InputStream inputStream, OutputStream outputStream){
		in = inputStream;
		out = outputStream;
	}
	
	void doHandshake(){
		
		try {
		
			
			byte[] requestBytes = REQUEST.getBytes("US-ASCII");
			
			while(!isConnected){
				out.write(requestBytes);
				
				
			}
			
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
		
		
	}
	
	boolean isConnectionAccepted(){
		
		return false;
	}
	
}
