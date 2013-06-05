import java.nio.ByteBuffer;

enum ConfigMessageType { SET, FLASH, READ };

public class ConfigOutputMessage extends OutputMessage {
	
	ConfigMessageType type;
	
	ConfigOutputMessage(ConfigMessageType cmt){
		type = cmt;
	}
	
	public ByteBuffer toByteBuffer() {
		ByteBuffer data;
		
		switch(type){
		case SET:
			data = ByteBuffer.allocate(1); //TODO: change sizes
			break;
		case FLASH:
			data = ByteBuffer.allocate(1);
			break;
		case READ:
			data = ByteBuffer.allocate(1);
			break;
		default:
			data = null;
			break;
		}
		
		return data;
	}

	public OutputMessageType getType() {
		return OutputMessageType.CONFIG_OUTPUT;
	}

}
