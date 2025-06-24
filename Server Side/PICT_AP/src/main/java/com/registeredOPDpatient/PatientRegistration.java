package com.registeredOPDpatient;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class PatientRegistration
 */
@WebServlet("/PatientRegistration")
public class PatientRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter pen = response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/pict_ap","root","");
			String name = request.getParameter("name");
			String contact = request.getParameter("contact");
			String gender = request.getParameter("gender");
			String age = request.getParameter("age");
			String email = request.getParameter("email");
			String uname = request.getParameter("uname");
			String pass = request.getParameter("pass");
			PreparedStatement ps = con.prepareStatement("insert into patient(name, contact, gender, age, email, uname) values(?,?,?,?,?,?)");
			PreparedStatement ps2 = con.prepareStatement("insert into login(uname, password, role) values(?,?,?)");
			ps.setString(1,name);
			ps.setString(2,contact); 
			ps.setString(3,gender);
			ps.setString(4,age);
			ps.setString(5,email); 
			ps.setString(6,uname);
			
			ps2.setString(1,uname);
			ps2.setString(2,pass); 
			ps2.setString(3,"patient");
			int c = ps.executeUpdate();
			int c1 = ps2.executeUpdate();
			if(c>0 && c1 > 0) {
				System.out.println("Inserted...");
				pen.write("<!DOCTYPE HTML>");
				pen.write("<HTML>");
				pen.write("<body style='background-color:aliceblue'>");
				pen.write("<div style='text-align:center; box-sshadow:0 0 2px green;'>");
				pen.write("<h1>Successfully Done...</h1>");
				pen.write("<h3><button style='background-color:green;'><a href='http://127.0.0.1:5500/index.html' style='text-decoration:none; font-size:25px; color: white'>Go to Home Page</a></button></h3>");
				pen.write("</div>");
				pen.write("</body>");
				pen.write("</html>");	
			}
			else {
				System.out.println("Failed...");
				
			}
			
		}catch(Exception e) {
			System.out.println(e);
			
			String error = e.toString();
			pen.write("<!DOCTYPE HTML>");
			pen.write("<HTML>");
			pen.write("<body style='background-color:aliceblue'>");
			pen.write("<div style='text-align:center; box-sshadow:0 0 2px green;'>");
			pen.write("<h1>Failed...</h1>");
			pen.write("<h1>"+error+"</h1>");
			pen.write("<h3><button style='background-color:green;'><a href='http://127.0.0.1:5500/patientregistrationform.html' style='text-decoration:none; font-size:25px; color: white'>Try Again</a></button></h3>");
			pen.write("</div>");
			pen.write("</body>");
			pen.write("</html>");
		}
	}

}
