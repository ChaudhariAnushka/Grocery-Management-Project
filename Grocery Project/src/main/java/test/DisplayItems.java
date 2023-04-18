
package test;

import java.io.*;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/display")
public class DisplayItems extends HttpServlet
{
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Statement stmt = null;
		PrintWriter pw = resp.getWriter();
		ResultSet rs = null;
	
		//Create Query
		String query ="select * from grocery_shop";
		//create statement
		try {
			stmt = con.createStatement();
			//execute the query
			rs=stmt.executeQuery(query);
			//Print Result
			pw.print("                                                       ");
			pw.print("                                                       ");
			pw.print("<h1 style=color:purple>DISPLAY GROCERY ITEM</h1>");
			pw.print("                                                       ");
			pw.print("===========================================================");
			pw.print("                                                       ");
			pw.print("                                                       ");
			pw.print("                                                       ");
			pw.print("<table border='2'>");
			pw.print("<tr>");
			pw.print("<th>ITEM ID</th>");
			pw.print("<th>ITEM NAME</th>");
			pw.print("<th>ITEM STOCK</th>");
			pw.print("<th>ITEM PRICE</th>");
			pw.print("</tr>");
			
			while(rs.next()) {
				
				pw.print("<tr>");
				pw.print("<td>"+rs.getInt(1)+"</td>");
				pw.print("<td>"+rs.getString(2)+"</td>");
				pw.print("<td>"+rs.getInt(3)+"</td>");
				pw.print("<td>"+rs.getDouble(4)+"</td>");
				pw.print("</tr>");
			}
			pw.print("</table>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

}
