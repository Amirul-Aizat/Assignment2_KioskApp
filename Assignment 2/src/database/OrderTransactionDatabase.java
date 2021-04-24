package database;

import java.sql.*;

import kioskapp.ordertransaction.OrderTransaction;

public class OrderTransactionDatabase {


	public int insertOrderTransaction(OrderTransaction orderTransaction) throws SQLException {
		
		DatabaseConnection databaseConnection = new DatabaseConnection();
		
		PreparedStatement ps = databaseConnection.prepareStatement("INSERT INTO ordertransaction (TransactionDate, `Order`, AmountCharged, TransactionStatus, Last4Digits, OrderMode )VALUES (LOCALTIMESTAMP(),?, ?, ?, ?, ?)");
		

		ps.setInt(1, orderTransaction.getOrder().getOrderId());
		ps.setFloat(2, orderTransaction.getAmountCharged());
		ps.setInt(3, orderTransaction.isTransactionStatus() ?  1 : 0 );
		ps.setInt(4, orderTransaction.getLast4Digits());
		ps.setString(5, orderTransaction.getOrderMode());
		
		int status = ps.executeUpdate();
		
		return status;
		
	}
	
	
	public OrderTransaction getOrderTransaction(int id) throws SQLException {
		OrderTransaction orderTransaction = null;
		DatabaseConnection databaseConnection = new DatabaseConnection();
		PreparedStatement ps = databaseConnection.prepareStatement("SELECT * FROM orderTransaction WHERE orderTransactionId = ?");
		
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			orderTransaction = new OrderTransaction();
			
			orderTransaction.setOrderTransactionId(id);
			orderTransaction.setTransactioDate(rs.getDate(2));
			orderTransaction.setAmountCharged(rs.getFloat(3));
			orderTransaction.setLast4Digits(rs.getInt(5));
			orderTransaction.setOrderMode(rs.getString(6));
			
			
		}
		
		rs.close();
		ps.close();
		return orderTransaction;
		
	}
	



	public int getNextOrderId() throws SQLException {
		DatabaseConnection databaseConnection = new DatabaseConnection();
		PreparedStatement ps = databaseConnection.prepareStatement("SHOW TABLE STATUS LIKE 'ordertransaction'");
		
		ResultSet rs= ps.executeQuery();
		
		if(rs.next()) {
			return rs.getInt(11);
		}
		else {
			return 1;
		}
	}
}