package Server;

//RUN THIS FIRST

/*
 *  Ahmad Firdaus
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

import GUI.OrderServerGUI;
import kioskapp.order.Order;
import kioskapp.ordereditem.OrderedItem;
import kioskapp.ordertransaction.OrderTransaction;

import database.*;

public class MainOrderServer {

	@SuppressWarnings({ "unchecked", "resource" })
	public static void main( String [] args) throws IOException, ClassNotFoundException, SQLException {
		

		OrderServerGUI orderServerGUI = new OrderServerGUI();
		orderServerGUI.setVisible(true);
		
		int PORT1 = 3215;
		ServerSocket serverSocket = new ServerSocket(PORT1);
		
		Socket clientSocket = serverSocket.accept();
		orderServerGUI.addText("Receive connectino from client 1");// the server gui update thing.all of the addText is for this purpose
		Socket clientSocket2 = serverSocket.accept();
		orderServerGUI.addText("Receive connection from client 2");
		
		int orderReferenceNumber = 1;// declare the orderreference number. this number will reset if the server reset
		
		while(true) {// keep the server runn forever
			
			// for client 1 (kioskk)
			DataInputStream dataIn = new DataInputStream(clientSocket.getInputStream());
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
			ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
			
			// for client 2 (kitchen)
			ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(clientSocket2.getOutputStream());
			DataOutputStream dataOut2 = new DataOutputStream(clientSocket2.getOutputStream());
			
			/////////////////////////////////////////////////credit card validation
			
			String creditCardNumber = dataIn.readUTF();// used for 2 purpose. to check whethe the user cancel or not and to get credit card
			
			
			if(!creditCardNumber.equalsIgnoreCase( "0")) {// if the client send 0 the it mean the client press the cancel button. if not, then continue
				orderServerGUI.addText("receive the credit card number");
				dataOut2.writeBoolean(true);// send to the kitchen that we continue
				dataOut2.flush();
			
				// connect to the credit card server to validate the credit card
				int PORT = 3322;
				String ADDRESS = "localhost";
				Socket socket = new Socket(ADDRESS, PORT);
			
				// set up the data to send and receive object
				DataInputStream cardDataIn = new DataInputStream(socket.getInputStream());
				DataOutputStream cardDataOut = new DataOutputStream(socket.getOutputStream());
				
				// continue with plan
				orderServerGUI.addText("sending the credit card number to credit card server to validate it");
				cardDataOut.writeUTF(creditCardNumber);
				cardDataOut.flush();
				
				boolean status = cardDataIn.readBoolean();
				orderServerGUI.addText("receive back the validity");
				
				socket.close();// close socket to credit card server after done
			
				////////////////////////////////////////////////receive orderItems and orderTransaction from client
			
				ArrayList<OrderedItem> orderedItems = new ArrayList<OrderedItem>();
				orderedItems = (ArrayList<OrderedItem>) objectInputStream.readObject();
				orderServerGUI.addText("Receive the list of ordered item from client 1");
				
				OrderTransaction orderTransaction = new OrderTransaction();
				orderTransaction = (OrderTransaction) objectInputStream.readObject();
				orderServerGUI.addText("receive the transaction data from client 1");
			
				Order order = new Order();
				
				// get ready to go to database
				orderServerGUI.addText("settle up the database");
				order.setOrderedItems(orderedItems);
				order.setTotalAmount(orderTransaction.getAmountCharged());
				order.setOrderReferenceNumber(orderReferenceNumber++);
			
				OrderDatabase orderDatabase = new OrderDatabase();
				OrderedItemDatabase  orderedItemDatabase = new OrderedItemDatabase();
				OrderTransactionDatabase orderTransactionDatabase = new OrderTransactionDatabase();
			
				// can be null
				order.setOrderId(orderDatabase.getNextOrderId());
			
				orderTransaction.setOrder(order);
				orderTransaction.setOrderTransactionId(orderTransactionDatabase.getNextOrderId());// because we use this
				orderTransaction.setTransactionStatus(status);
				
				// we use myphpadmin for timestamp but... just set it.
				long millis=System.currentTimeMillis();  
				java.sql.Date date=new java.sql.Date(millis); 
				orderTransaction.setTransactioDate(date);
				
				/////////////////////////////////////////////////put everything into database
				// put order first because orderedItem and orderTransaction require order id data which is their foreign key.
				orderDatabase.insertOrder(order);
				orderedItemDatabase.insertOrderedItem(order);
				orderTransactionDatabase.insertOrderTransaction(orderTransaction);
				
				/////////////////////////////////////////////////send back to client for receipt
				// whethe the transaction is valid or not. still sent it to client. for the receipt
				orderServerGUI.addText("send the receipt to client 1");
				objectOutputStream.writeObject(order);
				objectOutputStream.reset();
				objectOutputStream.writeObject(orderTransaction);
				objectOutputStream.reset();
				
			
				// but dont send to kitchen if it not valid.
				if(status) {
					orderServerGUI.addText("send the order to kitchen(client 2)");
					objectOutputStream2.writeObject(order);
					objectOutputStream2.reset();
					objectOutputStream2.writeObject(orderTransaction);
					objectOutputStream2.reset();
					
				}
				else {
					objectOutputStream2.writeObject(null);// send null object to tell kitchen that the payment is invalid
				}
			}
			else {// from the if credit card line 57
					orderServerGUI.addText("user canceled order");
					dataOut2.writeBoolean(false);// tell the kitchen that the kioskk cancel so they will reset the socket too.
					dataOut2.flush();
			}
		
			// close socket
			clientSocket.close();
			clientSocket2.close();
			
			// accept socket2 first(kitchen) for some reason.
			clientSocket2 = serverSocket.accept();
			clientSocket = serverSocket.accept();
		}
	}
}
