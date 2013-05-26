import java.awt.EventQueue;


public class AlpineSwiftTerminal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try{
					AlpineSwiftInterface asInterface = new AlpineSwiftInterface();
					asInterface.setVisible(true);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		
	}

}
