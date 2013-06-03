import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;

public class SerialConnection{	
	//Connected to
	private SerialPort serialPort;
	
	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;
	/** Default bits per second for COM port. */
	private static final int DATA_RATE = 9600;
	
	SerialConnection(){
		serialPort = null;
	}
	
	public static ArrayList<String> getAvailableSerialPorts(){
		ArrayList<String> names = new ArrayList<String>();
		
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
		CommPortIdentifier currPortId;
		
		while (portEnum.hasMoreElements()) {
			currPortId = (CommPortIdentifier) portEnum.nextElement();
			names.add(currPortId.getName());	
		}
	
		return names;
	}

	public void open(String portName) throws Exception{
		CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier(portName);
		
		if(portId != null){
			// open serial port
			serialPort = (SerialPort) portId.open("DEFAULT_APP_NAME", TIME_OUT);
	
			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);
	
			// open the streams
			BufferedReader in = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			OutputStream out = serialPort.getOutputStream();	
		
			(new Thread(new SerialReader(in))).start();
            (new Thread(new SerialWriter(out))).start();
			
		}else{
			//couldn't find port	
		}
	}
	
	public void close(){
		if(serialPort != null){
			serialPort.close();
		}
	}
	
	public class SerialReader implements Runnable{
		BufferedReader in;
		
		SerialReader(BufferedReader in){
			this.in = in;
		}
		
		public void run() {	
			String line;
			//while not closed
			while((line = in.readLine()) != null){
				//notify listeners	
			}	
		}
	}
	
	public class SerialWriter implements Runnable{
		OutputStream out;
		
		SerialWriter(OutputStream out){
			this.out = out;
		}
		
		public void run() {
			//while not closed
			while(){
				
			}	
		}		
	}

}