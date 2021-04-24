package database;

import java.sql.*;

import kioskapp.order.Order;
import kioskapp.ordereditem.OrderedItem;

public class OrderedItemDatabase {

	public int insertOrderedItem(Order order) throws SQLException {
		DatabaseConnection databaseConnection = new DatabaseConnection();
		int status = 0;
		for (OrderedItem orderedItem: order.getOrderedItems())
		{
			
		
		PreparedStatement ps = databaseConnection.prepareStatement("INSERT INTO ordereditem (ItemProduct,Quantity,SubTotalAmount,`Order`) VALUES (?, ?, ?, ?)");
		
		ps.setInt(1, orderedItem.getItemProduct().getItemProduct());
		ps.setInt(2, orderedItem.getQuantity());
		ps.setFloat(3, orderedItem.getSubTotalAmount());
		ps.setInt(4, order.getOrderId());
		
		status = status * ps.executeUpdate();
		}
		return status;
		
	}
	
	public OrderedItem getOrderedItem(int id) throws SQLException {
		OrderedItem orderedItem = null;
		DatabaseConnection databaseConnection = new DatabaseConnection();
		PreparedStatement ps = databaseConnection.prepareStatement("SELECT * FROM orderedItem WHERE OrderedItemId = ?");
		
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			orderedItem = new OrderedItem();
			
			orderedItem.setOrderedItem(id);
			
			ItemProductDatabase itemProductDatabase = new ItemProductDatabase();
			orderedItem.setItemProduct(itemProductDatabase.getItemProduct(rs.getInt(2)));
			
			orderedItem.setQuantity(rs.getInt(3));
			orderedItem.setSubTotalAmount(rs.getFloat(4));
			
		}
		
		rs.close();
		ps.close();
		return orderedItem;
		
	}
}
