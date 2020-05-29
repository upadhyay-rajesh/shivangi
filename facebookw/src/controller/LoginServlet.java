package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nn1=request.getParameter("pass");
		String nn2=request.getParameter("email");
		
		ResultSet i=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","rajesh");
			
			PreparedStatement ps=con.prepareStatement("select * from student where email=? and password=?");
			
			ps.setString(1, nn2);
			ps.setString(2, nn1);
			
			
			i=ps.executeQuery();
		
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.println("<html><body>");
		if(i.next()) {
			out.println("welcome "+nn2);
			
			out.println("please <a href=login.html>click here</a> ");
		}
		else {
			out.println("invalid id and password ");
			out.println("please <a href=login.html>login again</a> ");
		}
		out.println("</body></html>");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
