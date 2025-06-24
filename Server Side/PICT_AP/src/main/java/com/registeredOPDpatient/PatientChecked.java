package com.registeredOPDpatient;

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

import org.json.JSONObject;

/**
 * Servlet implementation class PatientChecked
 */
@WebServlet("/PatientChecked")
public class PatientChecked extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientChecked() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		try {
			
			String t = request.getParameter("token");
			System.out.println(t);
			JSONObject array = new JSONObject();
			array.put("1", "Done");
			response.getWriter().write(array.toString());
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/pict_ap","root","");
			PreparedStatement ps = con.prepareStatement("select *from opdregisteredpatient where token = ?");
			ps.setString(1,t);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				PreparedStatement ps2 = con.prepareStatement("insert into checkedpatient (token, name, age, gender, doctor_assign, department, status) values(?,?,?,?,?,?,?)");
				
				ps2.setString(1,rs.getString("token"));
				ps2.setString(2,rs.getString("name"));
				ps2.setInt(3,rs.getInt("age"));
				ps2.setString(4,rs.getString("gender"));
				ps2.setString(5,rs.getString("doctor_assign"));
				ps2.setString(6, rs.getString("department"));
				ps2.setString(7, "Checked");
				
				int c = ps2.executeUpdate();
				if(c>0) {
					System.out.println("Inserted...");
				}
				
//				delete from previous table 
				PreparedStatement ps3 = con.prepareStatement("delete from opdregisteredpatient where token = ?");
				ps3.setString(1,rs.getString("token"));
				c = ps3.executeUpdate();
				if(c>0) {
					System.out.println("Deleted...");
				}
				else {
					System.out.println("Failed...");
				}
				response.sendRedirect("http://127.0.0.1:5500/doctor.html");
				
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
