package database;

import java.sql.*;
import kioskapp.itemproduct.ItemProduct;

public class ItemProductDatabase {

	public int insertItemProduct(ItemProduct itemProduct) throws SQLException {
		
		DatabaseConnection databaseConnection = new DatabaseConnection();
		
		PreparedStatement ps = databaseConnection.prepareStatement("INSERT INTO itemproduct (name, price) VALUES ( ?, ?)");
		
		ps.setString	(1, itemProduct.getName());
		ps.setDouble	(2, itemProduct.getPrice());
		
		int status = ps.executeUpdate();
		
		return status;
		
	}
	
	public ItemProduct getItemProduct(int id) throws SQLException {
		
		ItemProduct itemProduct = null;
		DatabaseConnection databaseConnection = new DatabaseConnection();
		PreparedStatement ps = databaseConnection.prepareStatement("SELECT * FROM itemproduct WHERE ItemProduct = ?");
		
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			itemProduct = new ItemProduct();
			
			itemProduct.setItemProduct(id);
			itemProduct.setName(rs.getString(2));
			itemProduct.setPrice(rs.getFloat(3));
			
		}
		
		rs.close();
		ps.close();
		return itemProduct;
	
		
	}
}
