package com.registeredOPDpatient;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONObject;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
			
			String uname = request.getParameter("uname");
			String pass = request.getParameter("pass");
			System.out.println(uname + pass);
			PreparedStatement ps = con.prepareStatement("select *from login where uname = ? and password = ?");
			ps.setString(1, uname);
			ps.setString(2,  pass);
			ResultSet rs = ps.executeQuery();
//			while(rs.next()) {
//				System.out.println("Fateched data");
//				System.out.println(rs.getString("role"));
//			}
			while(rs.next()) {
//				RequestDispatcher rd = request.getRequestDispatcher("http://127.0.0.1:5500/index.html");
//				rd.forward(request, response);
				
				if(rs.getString("role").equals("patient")) {
					System.out.println("Patient Login");
					PreparedStatement p = con.prepareStatement("select * from patient where uname = ? ");
					p.setString(1, uname);
					ResultSet r = p.executeQuery();
					while(r.next()) {
						
						JSONObject patient = new JSONObject();
						patient.put("name", r.getString("name"));
						patient.put("gender", r.getString("gender"));
						patient.put("age", r.getInt("age"));
						patient.put("contact", r.getString("contact"));
						patient.put("email", r.getString("email"));
						patient.put("gender", r.getString("gender"));
						
//						RequestDispatcher rd = request.getRequestDispatcher("http://127.0.0.1:5500/index.html");
//						rd.forward(request, response);
						response.getWriter().print("true");
						response.sendRedirect("http://127.0.0.1:5500/index.html");
					}
					
				}
				if(rs.getString("role").equals("doctor")) {
					System.out.println("Doctor Login");
					PreparedStatement p = con.prepareStatement("select * from doctors where uname = ? ");
					p.setString(1, uname);
					ResultSet r = p.executeQuery();
					while(r.next()) {
						
						JSONObject doctor = new JSONObject();
						doctor.put("name", r.getString("name"));
						doctor.put("gender", r.getString("gender"));
						doctor.put("age", r.getInt("age"));
						doctor.put("contact", r.getString("contact"));
						doctor.put("email", r.getString("email"));
						doctor.put("education", r.getString("education"));
						
						System.out.println("done2");
						pen.write(doctor.toString());
						
						
//						Session
//						HttpSession s =  request.getSession();
						request.setAttribute("name", r.getString("name"));
						
						RequestDispatcher rd = request.getRequestDispatcher("doctor.jsp");
						rd.forward(request, response);
					}
				}
				
			}
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}

}
