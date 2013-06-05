import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;


public class StatusMessage extends InputMessage{

	private static final int STATUS_LENGTH = 8;
	
	private static final String[] statusCodes = {
		"OVRSPEED",
		"FLAMEOUT"
	};
	
	private static final String[] messages = {
		"Message 1",
		"Message 2"
	};
	
	private static final Color[] colors = {
		new Color(1.0f, 0.4f, 0.2f),
		new Color(0.1f, 0.7f, 0.3f),
	};
	
	private int index = -1;

	StatusMessage(ByteBuffer data){
		super(data);
	}
	
	protected void fromByteBuffer(ByteBuffer data){
		byte[] stringData = new byte[STATUS_LENGTH];
		data.get(stringData);
		String s;
		try {
			s = new String(stringData, "US-ASCII");

			//could use some other data structure to store all this data, but parallel arrays seem alright for now
			for(int i=0;i<statusCodes.length;i++){
				if(s.equals(statusCodes[i])){
					index = i;
					break;
				}
			}
			if(index == -1){
				//error, status code not found
			}
			
		} catch (UnsupportedEncodingException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	
	}
	
	public String getStatusCode(){
		return statusCodes[index];
	}
	
	public String getMessage(){
		return messages[index];
	}
	
	public Color getColor(){
		return colors[index];
	}

	public InputMessageType getType() {
		return InputMessageType.STATUS;
	}
	
}
