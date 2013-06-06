import java.nio.ByteBuffer;

enum InputMessageType { STATUS, CONFIG_INPUT, LOG };

public abstract class InputMessage extends Message {

	protected abstract void fromByteBuffer(ByteBuffer data);
	
	public abstract InputMessageType getType();
	
}
