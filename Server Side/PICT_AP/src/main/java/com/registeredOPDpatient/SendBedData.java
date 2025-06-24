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
 * Servlet implementation class SendBedData
 */
@WebServlet("/SendBedData")
public class SendBedData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendBedData() {
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
			PreparedStatement ps = con.prepareStatement("select *from bed");
			ResultSet rs = ps.executeQuery();
			JSONObject bedData = new JSONObject();
			int i = 0; 
			while(rs.next()) {
				JSONObject bed = new JSONObject();
				bed.put("name", rs.getString("bedname"));
				bed.put("count", rs.getInt("count"));
				
				bedData.put(""+i, bed);
				++i;
			}
			response.getWriter().write(bedData.toString());
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
