import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class StatusMessage extends InputMessage{

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
		super();
		fromByteBuffer(data);
	}
	
	protected void fromByteBuffer(ByteBuffer data){
		byte[] stringData = new byte[data.remaining()];
		data.get(stringData);
		String s;
		try {
			s = new String(stringData, "US-ASCII");

			//could use some other data structure to store all this data, but parallel arrays seem alright for now
			for(int i=0;i<statusCodes.length;i++){
				if(s.equalsIgnoreCase(statusCodes[i])){
					index = i;
					break;
				}
			}
			if(index == -1){
				//error, status code not found
				System.out.println("Error: Status code not found");
			}
			
		} catch (UnsupportedEncodingException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	
	}
	
	public String getStatusCode(){
		if(index >= 0 && index < statusCodes.length){
			return statusCodes[index];
		}else{
			return "EMPTY";
		}
	}
	
	public String getMessage(){
		if(index >= 0 && index < messages.length){
			return messages[index];
		}else{
			return "EMPTY";
		}
	}
	
	public Color getColor(){
		if(index >= 0 && index < colors.length){
			return colors[index];
		}else{
			return Color.WHITE;
		}
	}

	public InputMessageType getType() {
		return InputMessageType.STATUS;
	}
	
	public String toString(){
		return super.toString() + " - " + getStatusCode() + " - " + getMessage();
	}
	
}
