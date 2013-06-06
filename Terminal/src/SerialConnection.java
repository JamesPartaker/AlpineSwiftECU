import java.io.BufferedReader;
import java.io.InputStream;
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

	private InputStream in;
	
	private OutputStream out;
	
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

	//deal with exceptions, error handling
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
			in = serialPort.getInputStream();
			out = serialPort.getOutputStream();	
			
			System.out.println("Connected successfully!");
			
		}else{
			//couldn't find port	
			System.err.println("Serial Connection Error");
		}
	}
	
	public void close(){
		if(serialPort != null){
			serialPort.close();
		}
	}
	
	public OutputStream getOutputStream(){
		return out;
	}
	
	public InputStream getInputStream(){
		return in;
	}

}
