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
import java.sql.ResultSet;

import org.json.JSONObject;

/**
 * Servlet implementation class senddata
 */
@WebServlet("/senddata")
public class senddata extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public senddata() {
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
		 response.setContentType("Application/json");
		 
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");//loaded jdbc driver , remains same for all , used to establish connection between db and servlet load kartoy
			System.out.println("Connection Establish..!!");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/donner","root","");// established connecition between db and servlet
//			System.out.println(con);
			
				PreparedStatement ps = con.prepareStatement("select * from doner12");
				ResultSet rs=ps.executeQuery();
				JSONObject Arrayjb=new JSONObject();
				int i = 0;
				while(rs.next())
				{
					JSONObject jb=new JSONObject();
					String name = rs.getString("name");
					String email = rs.getString("email");
					String phone = rs.getString("phone");
					String bgrp	 = rs.getString("bgrp");
					String gender = rs.getString("gender");
					String address = rs.getString("address");
					int age = rs.getInt("age");
					jb.put("name", name);
					jb.put("email", email);
					jb.put("phone ",phone );
					jb.put("bgrp",bgrp);
					jb.put("gender", gender);
					jb.put("address", address);
					jb.put("age", age);
					Arrayjb.put(""+i,jb);
					i++;
				}
				pw.write(Arrayjb.toString());
				System.out.println("A2");
				

				
			
	}catch(Exception e) {
		System.out.println(e);
		}
	}

}
