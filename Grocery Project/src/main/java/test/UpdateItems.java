package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/updateitem")
public class UpdateItems extends HttpServlet
{
	//Declare Connection
	Connection con;
	public void init() throws ServletException{
		 
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Fetch the value from HTML page
		String id=req.getParameter("itemid");
		int item_id=Integer.parseInt(id);
		String item=req.getParameter("item");
		
		String stock=req.getParameter("stock");
		int stock1=Integer.parseInt(stock);
		
		String price=req.getParameter("price");
		double price1=Double.parseDouble(price);
		
		//Declare the resources (Create Statement Platform)
		PreparedStatement pstmt= null;    
		String query="update grocery_shop set item_name=?,stock=?, price=? where item_id=?";
		try {
			pstmt=con.prepareStatement(query);
			
			pstmt.setString(1, item);
			pstmt.setInt(2, stock1);
			pstmt.setDouble(3, price1);
			pstmt.setInt(4, item_id);
			int count=pstmt.executeUpdate();
			//Print the Result
			PrintWriter pw=resp.getWriter();
			pw.print("<h1>"+count+" RECORD UPDATED SUCCESSFULLY! </h1>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
