package com.blooddoner;

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
 * Servlet implementation class registerform
 */
@WebServlet("/registerform")
public class registerform extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerform() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		 PrintWriter pw = response.getWriter();
		 response.setHeader("Access-Control-Allow-Origin", "*");
		 System.out.println("A1");
		 
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");//loaded jdbc driver , remains same for all , used to establish connection between db and servlet load kartoy
			System.out.println("Connection Establish..!!");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/donner","root","");// established connecition between db and servlet
//			System.out.println(con);
			
				PreparedStatement ps = con.prepareStatement("insert into doner12 (name, email, bgrp, gender, address, age, phone) values(?,?,?,?,?,?,?)");
				String name = request.getParameter("uname");
				String email = request.getParameter("email");
				String phone = request.getParameter("phone");
				String bgrp	 = request.getParameter("bgrp");
				String gender = request.getParameter("gender");
				String address = request.getParameter("address");
				int age = Integer.parseInt(request.getParameter("age"));
				 System.out.println("A2");

			//set these all values in table
				ps.setString(1, name);
				ps.setString(2, email);
				ps.setString(3, bgrp);
				ps.setString(4, gender);
				ps.setString(5, address);
				ps.setInt(6,age);
				ps.setString(7, phone);
				int count = ps.executeUpdate();
				
//				System.out.println("Inserted Successfully"+count);
				if(count>0) {
				System.out.println("Inserted Successfully"); //this will be shown on web page
				response.sendRedirect("http://127.0.0.1:5500/blooddonation.html");
				}
				
			
	}catch(Exception e) {
		System.out.println(e);
		}
	}
}
