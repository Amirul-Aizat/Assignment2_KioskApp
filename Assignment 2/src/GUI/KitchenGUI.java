package GUI;

/*
 *  Syauqi 
 */

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Dimension;

import javax.swing.JTextField;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.TextArea;

//not readable

@SuppressWarnings("serial")
public class KitchenGUI extends JFrame {


	private TextArea textField_3 = new TextArea();
	private TextArea textField_2 = new TextArea();
	private JTextField textField;
	public KitchenGUI()  {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		setBounds(100, 100, 763, 549);
		this.setPreferredSize(new Dimension(762, 559));
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Kitchen ( Customer Order )");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2.setBounds(220, 0, 307, 33);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Date :");
		lblNewLabel_3.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_3.setBounds(505, 34, 59, 22);
		getContentPane().add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(559, 34, 112, 23);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Take-Away");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel.setBounds(104, 129, 125, 27);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Dine-In");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_1.setBounds(512, 133, 70, 18);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_7 = new JLabel("ORDER");
		lblNewLabel_7.setFont(new Font("Gill Sans MT", Font.BOLD, 17));
		lblNewLabel_7.setBounds(321, 93, 70, 22);
		getContentPane().add(lblNewLabel_7);
		
		
			Calendar cal = new GregorianCalendar();
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int month=cal.get(Calendar.MONTH);
			int year = cal.get(Calendar.YEAR);
			
		
			
			textField.setText(year+"/"+month+"/"+day);
			textField_2.setEditable(false);
			
			
			textField_2.setBounds(10, 156, 359, 328);
			getContentPane().add(textField_2);
			textField_3.setEditable(false);
			
			
			textField_3.setBounds(375, 157, 361, 327);
			getContentPane().add(textField_3);
			
		
	}
	
public void setTextDineIn(String text) {
	Calendar cal = new GregorianCalendar();
	int second = cal.get(Calendar.SECOND);
	int minute = cal.get(Calendar.MINUTE);
	int hour = cal.get(Calendar.HOUR);
	textField_3.setEditable(true);
	String nextText = Integer.toString(hour)+":"+Integer.toString(minute)+":"+Integer.toString(second)+":\n"+text;
	textField_3.setText(nextText+textField_3.getText());
	textField_3.setEditable(false);
	
}
	
public void setTextTakeAway(String text) {
	Calendar cal = new GregorianCalendar();
	int second = cal.get(Calendar.SECOND);
	int minute = cal.get(Calendar.MINUTE);
	int hour = cal.get(Calendar.HOUR);
	textField_2.setEditable(true);
	String nextText = "Time: "+Integer.toString(hour)+":"+Integer.toString(minute)+":"+Integer.toString(second)+":\n"+text+"";
	textField_2.setText(nextText+textField_2.getText()+"\n");
	textField_2.setEditable(false);
	
}




}
