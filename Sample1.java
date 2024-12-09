package com.sres;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class Sample1
 */
@WebServlet("/Sample1")
public class Sample1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sample1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        
		int x=Integer.parseInt(request.getParameter("ch"));
		
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("RUNNED");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3308/empdb","root","");
		if(con!=null) {

			
		System.out.println("connection established");
		
		if(x==1) {
			//insert
			String e_name=request.getParameter("name");
			int e_age=Integer.parseInt(request.getParameter("age"));
			String user=request.getParameter("user");
			String pass=request.getParameter("pass");
			PreparedStatement ps=con.prepareStatement("insert into emp_table(emp_name,emp_age,username,password) values (?,?,?,?)");
			ps.setString(1, e_name);
			ps.setInt(2, e_age);
			ps.setString(3, user);
			ps.setString(4, pass);
			
			int rows=ps.executeUpdate();
			System.out.println("Number of rows affected : "+rows);
			out.println("<p>Insert Operation</p>");
            out.println("<p>The number of rows affected: " + rows + "</p>");
            ps.close();
		}

		if(x==2) {
			// Update operation
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");

            PreparedStatement ps = con.prepareStatement("UPDATE emp_table SET emp_name=? WHERE emp_id=?");
            ps.setString(1, name);  
            ps.setInt(2, id);      

            int rowAffected = ps.executeUpdate();
            out.println("<p>Update Operation</p>");
            out.println("<p>The number of rows affected: " + rowAffected + "</p>");
            ps.close();
			
		}

		if(x==3) {
			 // Delete operation
            int id = Integer.parseInt(request.getParameter("id"));

            PreparedStatement ps = con.prepareStatement("DELETE FROM emp_table WHERE emp_id=?");
            ps.setInt(1, id);  

            int rowAffected = ps.executeUpdate();
            out.println("<p>Deletion Operation</p>");
            out.println("<p>The number of rows affected: " + rowAffected + "</p>");
            ps.close();
		}

		if(x==4) {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM emp_table");
            ResultSet rs = ps.executeQuery();

            out.println("<h3>Student Records</h3>");
            while (rs.next()) {
                out.println("<p>Student ID: " + rs.getInt(1) + "</p>");
                out.println("<p>Student Name: " + rs.getString(2) + "</p>");
                out.println("<p>Student Email: " + rs.getString(3) + "</p>");
                out.println("<p>Student Mobile: " + rs.getString(4) + "</p>");
                out.println("<br>");
            }

            rs.close();
            ps.close();
        }
		}
		
		else {
			System.out.println("connection failed!");
		}
		
	}
	catch(Exception e) {
			System.out.println(e);
		}
	
	}
	
	}

