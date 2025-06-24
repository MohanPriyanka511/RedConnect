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
 * Servlet implementation class SendDoctorData
 */
@WebServlet("/SendDoctorData")
public class SendDoctorData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendDoctorData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Access-Control-Allow-Origin","*");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PICT_AP","root","");
			PreparedStatement ps = con.prepareStatement("select *from doctors");
			ResultSet rs = ps.executeQuery();
			JSONObject docData = new JSONObject();
			int i = 0; 
			while(rs.next()) {
				JSONObject doctor = new JSONObject();
				doctor.put("name", rs.getString("name"));
				doctor.put("gender", rs.getString("gender"));
				doctor.put("age", rs.getInt("age"));
				doctor.put("contact", rs.getString("contact"));
				doctor.put("email", rs.getString("email"));
				doctor.put("education", rs.getString("education"));
				doctor.put("picname", rs.getString("picname"));
				
				docData.put(""+i, doctor);
				++i;
			}
			response.getWriter().write(docData.toString());
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
