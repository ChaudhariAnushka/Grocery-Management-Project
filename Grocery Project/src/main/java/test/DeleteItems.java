package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/deleteitem")
public class DeleteItems extends HttpServlet
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
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Fetch the data from HTML Page
		String id=req.getParameter("itemid");
		//parse the value
		int item_id=Integer.parseInt(id);
		
		//declare the resources
		PreparedStatement pstmt=null;
		//Write sql qurey
		String query="delete from grocery_shop where item_id=?";
		try {
			pstmt=con.prepareStatement(query);
			//set the value
			pstmt.setInt(1, item_id);
			int count=pstmt.executeUpdate();
			//print the result
			PrintWriter pw=resp.getWriter();
			pw.print("<h1>"+count+" RECORD DELETED SUCCESSFULLY!</h1>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
