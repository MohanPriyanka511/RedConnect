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
 * Servlet implementation class ChangeStatus
 */
@WebServlet("/ChangeStatus")
public class ChangeStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeStatus() {
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
			
			String token = request.getParameter("token");
			
			PreparedStatement ps = con.prepareStatement("update opdregisteredpatient set status=? where token = ?");
			ps.setString(1,"In-progress");
			ps.setString(2,  token);
			int result = ps.executeUpdate();
			if(result > 0) {
				System.out.println("Chnaged");
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
