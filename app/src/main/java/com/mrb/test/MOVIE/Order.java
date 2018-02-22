package com.mrb.test.MOVIE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Order {
	public int counting = 0;

	public void addTicket(ArrayList<Integer> ticketId){
		Connection c = null;
		Statement stmt = null;
		counting++;
		try {
			Class.forName("org.sqldroid.SQLDroidDriver");
			c = DriverManager.getConnection("jdbc:sqldroid:/data/data/com.example.liuxizhen.oopproject/Order.db");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");
			stmt = c.createStatement();
			int arrsize = ticketId.size();
			String str;
			
			for(int i = 1; i<=4 ; i++){
				if(i>arrsize){
					ticketId.add(0);
				}
			}
			String time = getTime();
			str = "INSERT INTO order_table (NUM, TIME, TK1, TK2, TK3, TK4) " + "VALUES ("+counting+", '"+time+"', "+ticketId.get(0)
			+", "+ticketId.get(1)+", "+ticketId.get(2)+", "+ticketId.get(3)+");";
			stmt.executeUpdate(str);
			
			stmt.close();
			c.commit();
			c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
	}
	public ArrayList<Integer> deleteTicket(int orderId){
		Connection c = null;
		Statement stmt = null;
		ArrayList<Integer> arrint= new ArrayList<Integer>(0);
		try {
			Class.forName("org.sqldroid.SQLDroidDriver");
			c = DriverManager.getConnection("jdbc:sqldroid:/data/data/com.example.liuxizhen.oopproject/Order.db");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");
			stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT *  FROM order_table WHERE NUM = "+orderId+";");
			if(rs.next()){
				for(int i = 1;i<=4;i++){
					int j;
					int t_i = rs.getInt(i+1);
					//System.out.println("there");
					if((j = t_i)!=0) arrint.add(j);
				}
			}
			String sql = "DELETE FROM order_table WHERE NUM = "+orderId+";";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return arrint;
	}
	
	public ArrayList<Integer> getOrder(int orderId){
		Connection c = null;
		Statement stmt = null;
		ArrayList<Integer> arrint=new ArrayList<Integer>(0);
		try {
			Class.forName("org.sqldroid.SQLDroidDriver");
			c = DriverManager.getConnection("jdbc:sqldroid:/data/data/com.example.liuxizhen.oopproject/Order.db");
			c.setAutoCommit(false);
			//System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT *  FROM order_table WHERE NUM='" +orderId+ "';");
			for (int i = 1; i<=4;i++){
				int number = rs.getInt(i+2);
				if(number!=0){
					arrint.add(number);
				}
			}
			rs.close();
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		//System.out.println("Records created successfully");
		return arrint;
	}
	
	public Order(){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqldroid.SQLDroidDriver");
			c = DriverManager.getConnection("jdbc:sqldroid:/data/data/com.example.liuxizhen.oopproject/Order.db");
			// System.out.println("Opened database successfully");
			stmt = c.createStatement();
			
			
			
			String sql = "CREATE TABLE if not exists order_table (NUM    INT, TIME    TEXT, TK1    INT, TK2    INT, TK3    INT, TK4    INT)";
			stmt.executeUpdate(sql);
			
			ResultSet rs = stmt.executeQuery("SELECT *  FROM order_table;");
			
			String time = getTime();
			if (!rs.next()) {
				
				sql = "INSERT INTO order_table (NUM, TIME, TK1, TK2, TK3, TK4) " + "VALUES (" + 1 + ", '"+time+"', " + 0 + ", " + 0 + ", " + 0 + ", " + 0 + ");";
				stmt.executeUpdate(sql);
				counting++;
			}
			
			rs.close();
			stmt.close();
			c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		Calcounting();
	}
	
	private void Calcounting(){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqldroid.SQLDroidDriver");
			c = DriverManager.getConnection("jdbc:sqldroid:/data/data/com.example.liuxizhen.oopproject/Order.db");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT *  FROM order_table;");
			while(rs.next()){
				counting = rs.getInt("num");
			}
			
			rs.close();
			stmt.close();
			c.commit();
			c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	public String getorderTime(int orderId){
		Connection c = null;
		Statement stmt = null;
		String str = null;
		try {
			Class.forName("org.sqldroid.SQLDroidDriver");
			c = DriverManager.getConnection("jdbc:sqldroid:/data/data/com.example.liuxizhen.oopproject/Order.db");
			c.setAutoCommit(false);
			//System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT *  FROM order_table WHERE NUM='" +orderId+ "';");
			str = rs.getString(2);
			rs.close();
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		//System.out.println("Records created successfully");
		return str;
	}
	
	private String getTime(){
		Calendar cl=Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		String str = sdf.format(cl.getTime());
		return str;
	}
}
