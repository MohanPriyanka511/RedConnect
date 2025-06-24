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
 * Servlet implementation class SendOPDQueueData
 */
@WebServlet("/SendOPDQueueData")
public class SendOPDQueueData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendOPDQueueData() {
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
			PreparedStatement ps = con.prepareStatement("select *from opdregisteredpatient");
			ResultSet rs = ps.executeQuery();
			JSONObject array = new JSONObject();
			int i = 0; 
			while(rs.next()) {
				System.out.println(rs.getString("name")+rs.getInt("age"));
				JSONObject obj = new JSONObject();
				obj.put("token", rs.getString("token"));
				obj.put("name", rs.getString("name"));
				obj.put("age", rs.getInt("age"));
				obj.put("gender", rs.getString("gender"));
				obj.put("doctor_assign", rs.getString("doctor_assign"));
				obj.put("department", rs.getString("department"));
				obj.put("status", rs.getString("status"));
				
				array.put(""+i, obj);
				++i;
			}
			
			response.getWriter().write(array.toString());
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
