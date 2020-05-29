package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nn=request.getParameter("name");
		String nn1=request.getParameter("pass");
		String nn2=request.getParameter("email");
		String nn3=request.getParameter("address");
		
		int i=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","rajesh");
			
			PreparedStatement ps=con.prepareStatement("insert into student values(?,?,?,?)");
			ps.setString(1, nn);
			ps.setString(2, nn1);
			ps.setString(3, nn2);
			ps.setString(4, nn3);
			
			i=ps.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.println("<html><body>");
		if(i>0) {
		//out.println("name is "+nn);
		//out.println("<br>password is "+nn1);
		//out.println("<br> email is "+nn2);
		//out.println("<br>address is "+nn3);
			out.println("hello Mr./Mrs. "+nn+"  your registration is successful");
			out.println("your user name is "+nn2);
			out.println("your password  is "+nn1);
			out.println("please <a href=login.html>click here</a> ");
		}
		else {
			out.println("registration fail");
		}
		out.println("</body></html>");
	}

}
