import java.nio.ByteBuffer;

enum InputMessageType { STATUS, LOG, CONFIG_INPUT };

public abstract class InputMessage extends Message {

	InputMessage(ByteBuffer data){
		super();
		fromByteBuffer(data);
	}
	
	protected abstract void fromByteBuffer(ByteBuffer data);
	
	public abstract InputMessageType getType();
	
}
