package Client;

//RUN THIS FOURTH

/*
 *  Ahmad Firdaus
 */

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import GUI.KitchenGUI;
import kioskapp.order.Order;
import kioskapp.ordereditem.OrderedItem;
import kioskapp.ordertransaction.OrderTransaction;

public class KitchenClient {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{

		KitchenGUI kitchenGUI = new KitchenGUI();
		kitchenGUI.setVisible(true);	
		
		int PORT = 3215;
		String ADDRESS = "localhost";
		
		while(true){
			
			Socket socket = new Socket(ADDRESS, PORT);
			socket.setKeepAlive(true);
			
			DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());//get Input
			
			ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());// for object to receive
			if(dataInputStream.readBoolean()) {// if kiosk not click the cancel order
			
				Order order = new Order();
				order = (Order) objectIn.readObject();// receive the object from server and cast it to order
				
				if(order != null) {// if the payment is valid
					
					OrderTransaction orderTransaction = (OrderTransaction) objectIn.readObject();
				
					//convert the string text to a suitable format for the gui.
					String orderList = "Order referrence number: "+order.getOrderReferenceNumber()+"\n";
					for (OrderedItem orderedItem: order.getOrderedItems()) {
						orderList += orderedItem.getItemProduct().getName()+": "+orderedItem.getQuantity()+"\n";
					}
			
					//write the text inside the text area
					if(orderTransaction.getOrderMode().equalsIgnoreCase("Eat In")){
					kitchenGUI.setTextDineIn(orderList);
					}
					else {
						kitchenGUI.setTextTakeAway(orderList);
					}
				}
			}
		
			socket.close();
	
		}
	}
}