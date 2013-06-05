import java.nio.ByteBuffer;



enum ControlMessageType { STARTUP, SHUTDOWN, EMERGENCY_SHUTDOWN, THROTTLE };

public class ControlMessage extends OutputMessage{

	ControlMessageType cm;
	byte throttle;
	
	ControlMessage(ControlMessageType cm) {
		this.cm = cm;
	}
	
	ControlMessage(byte throttle){
		this.cm = ControlMessageType.THROTTLE;
		this.throttle = throttle;
	}
	
	public ByteBuffer toByteBuffer() {
		ByteBuffer byteBuffer;
		
		switch(cm){
		case STARTUP:
		case SHUTDOWN:
		case EMERGENCY_SHUTDOWN:
			byteBuffer = ByteBuffer.allocate(1);
			byteBuffer.put((byte)cm.ordinal());
			break;
		case THROTTLE:
			byteBuffer = ByteBuffer.allocate(2);
			byteBuffer.put((byte)cm.ordinal());
			byteBuffer.put(throttle);
			break;
		default:
			byteBuffer = null; //error
			break;
		}
		
		return byteBuffer;
	}
	
	public OutputMessageType getType(){
		return OutputMessageType.CONTROL;
	}
	
}
