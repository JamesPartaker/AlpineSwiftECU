import java.nio.ByteBuffer;


//single datapoint
public class LogMessage extends InputMessage{

	private static final int EGT_MULT_CONST = 50;
	private static final int ENG_SPEED_MULT_CONST = 1000;
	
	private int EGT;
	private int engineSpeed;
	private boolean ignition;
	private int force;
	private int throttle;
	private int pressure;
	private int fuelVoltage;
	private boolean fuelSolenoid;
	
	LogMessage(ByteBuffer data) {
		super();
		fromByteBuffer(data);
	}
	
	protected void fromByteBuffer(ByteBuffer data){
		EGT = (data.get() & 0xff) * EGT_MULT_CONST;
		engineSpeed = (data.get() & 0xff) * ENG_SPEED_MULT_CONST;
		ignition = (data.get() & 0xff) > 0  ? true : false; 
		//...
	}
	
	public int getEGT(){ return EGT; }
	
	public int getEngineSpeed(){ return engineSpeed; }
	
	public boolean getIgnition() { return ignition; }
	
	//...
	
	public InputMessageType getType(){
		return InputMessageType.LOG;
	}	
	
	public String toString(){
		return super.toString() + 
				" - EGT:" + EGT + "¼C" +
				" EngineSpeed:" + engineSpeed + "RPM" +
				" Ignition:" + (ignition ? "On" : "Off");
	}
	
}
