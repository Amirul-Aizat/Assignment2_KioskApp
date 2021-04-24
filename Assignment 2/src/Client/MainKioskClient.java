package Client;

//RUN THIS THIRD

/*
 *  Ahmad Firdaus
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;

import GUI.ApplicationFrame;
import WriteFile.WriteFile;
import kioskapp.order.Order;
import kioskapp.ordereditem.OrderedItem;
import kioskapp.ordertransaction.OrderTransaction;

public class MainKioskClient {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException, SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub

		int PORT = 3215;
		String ADDRESS = "localhost";
		String stop = "0";// value that will trigget the server to stop taking input and restart the socket
		
		while(true) {
			// each time the order done or fail or canceled. new frame will be created to reset everything
			ApplicationFrame applicationFrame = new ApplicationFrame();
			applicationFrame.setVisible(true);
			
			Socket socket = new Socket(ADDRESS, PORT);
			socket.setKeepAlive(true);
			
			DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
			DataInputStream dataIn = new DataInputStream(socket.getInputStream());
			
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
			
			// if the user not cancel order at first cancel button
			if(applicationFrame.getChoose() != -1) {
				// if the user not cancel order at second cancel button
				if(applicationFrame.getChoose() != -1) {
					// if user not cancel order at third cancel button
					if(applicationFrame.getChoose() != -1) {
						
						// to check the credit card number
						String creditCardNumber = applicationFrame.getCreditCardNumber();
						dataOut.writeUTF(creditCardNumber);
						dataOut.flush();
						
						// get the ordered items and order transaction
						ArrayList<OrderedItem> orderedItems = new ArrayList<OrderedItem>();
						orderedItems = applicationFrame.getOrderedItems();
						
						OrderTransaction orderTransaction = new OrderTransaction();
						orderTransaction = applicationFrame.getOrderTransaction();
						
						// send to server
						objectOutputStream.writeObject(orderedItems);
						objectOutputStream.reset();
						objectOutputStream.writeObject(orderTransaction);
						objectOutputStream.reset();
						
						// get the order and new order transaction from the server
						Order order = new Order();
						order = (Order) objectInputStream.readObject();
						orderTransaction = (OrderTransaction) objectInputStream.readObject();
						
						
						// receipt part
						ReceiptTemplate receiptTemplate = new ReceiptTemplate();// create the object of receipt
						String receipt = receiptTemplate.Receipt(order, orderTransaction);// assign the value to the receipt
						WriteFile data = new WriteFile("Receipt"+order.getOrderId()+".txt", false);// setup the name and if we want it to append is same file name
						data.writeToFile(receipt);// write it into txt,
						
					}	
					else {
						dataOut.writeUTF(stop);// send to the server to not receive anything
					}
				}else {
					dataOut.writeUTF(stop);// send to the server to not receive anything
				}
			}else {
				dataOut.writeUTF(stop);// send to the server to not receive anything
			}
			
			//close everything
			objectOutputStream.close();
			objectInputStream.close();
			dataOut.close();
			dataIn.close();
			socket.close();
			
			// dispose the frame because we create new one at start of the loop
			applicationFrame.dispose();
			
		}
	}
}
