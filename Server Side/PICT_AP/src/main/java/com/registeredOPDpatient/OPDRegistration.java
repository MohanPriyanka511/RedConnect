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
import java.sql.ResultSet;

/**
 * Servlet implementation class OPDRegistration
 */
@WebServlet("/OPDRegistration")
public class OPDRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OPDRegistration() {
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
		response.setHeader("Access-Control-Allow-Origin","*");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/pict_ap","root","");
			System.out.println("done1");
			
			PreparedStatement previousToken = con.prepareStatement("SELECT token FROM opdregisteredpatient ORDER BY id DESC LIMIT 1;");
			ResultSet rs = previousToken.executeQuery();
			String t = "";
			while(rs.next()) {
				t = rs.getString("token");
				System.out.println(rs.getString("token"));
				t = t.replace("ap", "");
				System.out.println(t);
				
			}
			System.out.println("done2");
			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age"));
			String gender = request.getParameter("gender");
			String department = request.getParameter("department");
			String symptoms = request.getParameter("symptoms");
			String contact = request.getParameter("contact");			
			System.out.println("done3");
			
			PreparedStatement ps = con.prepareStatement("insert into opdregisteredpatient (token, name, age, gender, doctor_assign, department, status, symptoms, contact) values(?,?,?,?,?,?,?,?,?)");
			
			
			if(t.isEmpty()) {
				ps.setString(1,"ap1"); 	// for first entry in table
			}
			else {
				int nt = Integer.parseInt(t);
				nt+=1;
				System.out.println(nt);
				
				String tok = "ap" + nt; 
				
				ps.setString(1,tok);				
			}
			
			ps.setString(2,name.trim());
			ps.setInt(3,age);
			ps.setString(4,gender);
			ps.setString(5,"Dr. P.M.");
			ps.setString(6, department.trim());
			ps.setString(7, "waiting");
			ps.setString(8, symptoms.trim());
			ps.setString(9, contact);
			int c = ps.executeUpdate();
			if(c>0) {
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
		}
	}

}
