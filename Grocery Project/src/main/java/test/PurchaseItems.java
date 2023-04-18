package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/purchaseitem")
public class PurchaseItems extends HttpServlet{
	
	Connection con;
	public void init() throws ServletException
	{	 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/grocery?user=root&password=sql@123");
			
		} catch (ClassNotFoundException e) 
		{
			
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		//Fetch the value from HTML Page
		String id=req.getParameter("id");
		String qty=req.getParameter("qty");
		//Parse the value
		int itemId=Integer.parseInt(id);
		int itemQty=Integer.parseInt(qty);
		//Create Statement Platform
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		PrintWriter pw=resp.getWriter();
		
		String query="select stock,price from grocery_shop where item_id=?";
		String query1="update grocery_shop set stock=? where item_id=?";
			
	    try {
			pstmt=con.prepareStatement(query);
			//set the value
			pstmt.setInt(1, itemId);
			rs=pstmt.executeQuery();
			if(rs.next()) 
			{
				int dbStock=rs.getInt(1);
				double dbPrice=rs.getDouble(2);
				
				if(itemQty<=dbStock)
				{
					double total = itemQty*dbPrice;
					pw.print("<h1 style=color:violet>TOTAL AMOUNT IS : "+total+"</h1>");
					pstmt=con.prepareStatement(query1);
					//set the value
					pstmt.setInt(1, dbStock-itemQty);
					pstmt.setInt(2, itemId);
					pstmt.executeUpdate();
				}
				else
				{
					pw.print("<h2 style=color:red>ITEM OUT OF STOCK</h2>");
				}
			  }
			else {
				pw.print("<h2 style=color:red>ITEM NOT FOUND</h2>");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	    
		
		
	}

}
