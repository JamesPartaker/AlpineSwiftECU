import java.nio.ByteBuffer;


public class ConfigInputMessage extends InputMessage{

	ConfigInputMessage(ByteBuffer data) {
		super();
		fromByteBuffer(data);
	}

	protected void fromByteBuffer(ByteBuffer data) {
		
	}

	public InputMessageType getType(){
		return InputMessageType.CONFIG_INPUT;
	}

	
}

//this way?
class ConfigV1{
	public static final byte version = 1;
	
}