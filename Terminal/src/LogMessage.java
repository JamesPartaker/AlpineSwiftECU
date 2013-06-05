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
		super(data);
	}
	
	protected void fromByteBuffer(ByteBuffer data){
		EGT = data.get() * EGT_MULT_CONST;
		engineSpeed = data.get() * ENG_SPEED_MULT_CONST;
		if(data.get() > 0){ ignition = true; }else{ ignition = false; }
		//...
	}
	
	public int getEGT(){ return EGT; }
	
	public int getEngineSpeed(){ return engineSpeed; }
	
	public boolean getIgnition() { return ignition; }
	
	//...
	
	public InputMessageType getType(){
		return InputMessageType.LOG;
	}	
	
}
