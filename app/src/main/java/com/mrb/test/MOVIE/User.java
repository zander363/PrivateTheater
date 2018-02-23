package com.mrb.test.MOVIE;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;


/**
 * Class User
 * 雿輻����
 *
 */
public class User {
	private String account;
	private String passward;
	private String name;
	private int age;
	private int counting=0; 

	/**
	 * constructor
	 * @param age
	 * @param name
	 */
	public User(String account, String passward, String name, int age){
		this.account = account;
		this.passward = passward;
		this.name=name;
		this.age=age;
		Calcounting();		
	}
	
	private void Calcounting(){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqldroid.SQLDroidDriver");
			c = DriverManager.getConnection("jdbc:sqldroid:/data/data/com.mrb.test.MOVIE/User.db");
			c.setAutoCommit(false);
			//System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT *  FROM user_table WHERE ACC='" +account+ "';");
			ResultSetMetaData rsmd = rs.getMetaData();
			int col_num = rsmd.getColumnCount();
			counting = col_num-4;
			
			rs.close();
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		//System.out.println("Records created successfully");
	}
	
	public int addOrder(ArrayList<Integer> ticketId){
		Connection c = null;
		Statement stmt = null;
		Order order = new Order();
		try {
			Class.forName("org.sqldroid.SQLDroidDriver");
			c = DriverManager.getConnection("jdbc:sqldroid:/data/data/com.mrb.test.MOVIE/User.db");
			c.setAutoCommit(false);
			//System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT *  FROM user_table;");
			ResultSetMetaData rsmd = rs.getMetaData();
			int col_num = rsmd.getColumnCount();
			col_num = col_num - 4;			
			if(col_num-counting==0){
				stmt.executeUpdate("ALTER TABLE user_table ADD order"+(counting+1)+" int");
			}
			int order_num = order.counting;
			String sq2 = "UPDATE user_table SET order"+(counting+1)+"= "+ ++order_num + " WHERE ACC='" +account+ "';";
			stmt.executeUpdate(sq2);
			counting++;
			order.addTicket(ticketId);
			
			rs.close();
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		//System.out.println("Records created successfully");
		return order.counting;
	}
	
	public ArrayList<Integer> deleteOrder(int orderId){
		Connection c = null;
		Statement stmt = null;
		Order order = new Order();
		ArrayList<Integer> arrint=null;
		try {
			Class.forName("org.sqldroid.SQLDroidDriver");
			c = DriverManager.getConnection("jdbc:sqldroid:/data/data/com.mrb.test.MOVIE/User.db");
			c.setAutoCommit(false);
			//System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT *  FROM user_table WHERE ACC='" +account+ "';");
			for (int i = 1; i<=counting;i++){
				//System.out.println("the "+i);
				if(rs.getInt(i+4)==orderId){
					//System.out.println("here");
					String sq2 = "UPDATE user_table SET order"+i+" = "+0+" WHERE ACC='" +account+ "';";
					arrint = order.deleteTicket(orderId);
					stmt.executeUpdate(sq2);
					break;
				}
			}
			System.out.println("delete finish!");
			if (c!=null)rs.close();
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
	
	public ArrayList<Integer> getOrdernum(){
		Connection c = null;
		Statement stmt = null;
		ArrayList<Integer> arrint=new ArrayList<Integer>(0);
		try {
			Class.forName("org.sqldroid.SQLDroidDriver");
			c = DriverManager.getConnection("jdbc:sqldroid:/data/data/com.mrb.test.MOVIE/User.db");
			c.setAutoCommit(false);
			//System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT *  FROM user_table WHERE ACC='" +account+ "';");
			for (int i = 1; i<=counting;i++){
				int number = rs.getInt(i+4);
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
	
	public ArrayList<Integer> getTicketnum(int orderId){
		ArrayList<Integer> arrint = null;
		Order order = new Order();
		arrint = order.getOrder(orderId);
		return arrint;
	}
	
	public String getTime(int orderId){
		Order order = new Order();
		return order.getorderTime(orderId);
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassward() {
		return passward;
	}

	public void setPassward(String passward) {
		this.passward = passward;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getCounting() {
		return counting;
	}

	public void setCounting(int counting) {
		this.counting = counting;
	}
	
	
}
