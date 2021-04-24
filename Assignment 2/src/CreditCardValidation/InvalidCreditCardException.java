package CreditCardValidation;

/*
 *  Ahmad Firdaus 
 */

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class InvalidCreditCardException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCreditCardException() {
		super("Invalid credit card.");
		
		// create a new frame for the Joption pane to make the message appear in the front the kiosk app gui
		JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    frame.setAlwaysOnTop(true);// make it always on top
	    frame.setSize(500, 500);
	    frame.setLocation(500, 500);
	    
	    //show error dialog
		JOptionPane.showMessageDialog(frame, "the transaction is invalid. Please order again", "Opps!", JOptionPane.ERROR_MESSAGE);
	
	}
	
	// if want to customize the error dialog
	public InvalidCreditCardException(String message) {
		super(message);
		JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    frame.setAlwaysOnTop(true);
	    frame.setSize(500, 500);
	    frame.setLocation(500, 500);
		JOptionPane.showMessageDialog(frame, message, "Opps!", JOptionPane.ERROR_MESSAGE);
		
	}
}
