import java.nio.ByteBuffer;

enum OutputMessageType { CONTROL, CONFIG_OUTPUT };

public abstract class OutputMessage extends Message {

	public abstract ByteBuffer toByteBuffer();
	
	public abstract OutputMessageType getType();
	
}
