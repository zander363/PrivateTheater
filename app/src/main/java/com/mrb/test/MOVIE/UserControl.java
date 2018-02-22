package com.mrb.test.MOVIE;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class UserControl {
	
	
	/*
	 * constructor
	 */
	public UserControl(){
		Creat_User_Db();
		Set_User_Db();
	}
	
	/*
	 * establish User Db
	 */
	private void Creat_User_Db() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqldroid.SQLDroidDriver");
			c = DriverManager.getConnection("jdbc:sqldroid:/data/data/com.example.liuxizhen.oopproject/User.db");
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			
				String sql = "CREATE TABLE if not exists user_table (ACC    TEXT , PSS    TEXT , "
						+ " NAME    TEXT      NOT NULL, AGE      INT )";
				stmt.executeUpdate(sql);
				stmt.close();
				c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	/**
	 * 霈��焉son��ser鞈��
	 */
	private void Set_User_Db(){
		String account = "hi4u3ykk04";
		String passward = "0000";
		String name = "ticba";
		int age = 22;
		if(Register(account, passward, name, age)==0) System.out.println("Regist Root successfully!");
	}
	
	/**
	 * Register
	 * 
	 */
	public int Register(String account, String passward, String name, int age){
		Connection c = null;
		Statement stmt = null;
		int return_value=0;
		try {
			Class.forName("org.sqldroid.SQLDroidDriver");
			c = DriverManager.getConnection("jdbc:sqldroid:/data/data/com.example.liuxizhen.oopproject/User.db");
			c.setAutoCommit(false);
			//System.out.println("Opened database successfully");
			stmt = c.createStatement();
			
			ResultSet rs1 = stmt.executeQuery("SELECT *  FROM user_table WHERE ACC='" +account+ "';");
			
			
			if (!rs1.next()) {
				String sq2 = "INSERT INTO user_table (ACC, PSS, NAME, AGE) " + "VALUES ('" + account + "','" + passward + "','"
						+ name + "'," + age + ");";
				stmt.executeUpdate(sq2);
				//System.out.println("add 1");
				return_value = 0;
			}
			else{
				return_value = 1;
			}
			
			
			rs1.close();
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		//System.out.println("Records created successfully");
		return return_value;
	}
	
	/**
	 * Login
	 * 
	 */
	public User Login(String account, String passward){
		Connection c = null;
		Statement stmt = null;
		String acc=null;
		String pss=null;
		String name=null;
		User user = null;
		boolean correct = false;
		int age=0;
		try {
			Class.forName("org.sqldroid.SQLDroidDriver");
			c = DriverManager.getConnection("jdbc:sqldroid:/data/data/com.example.liuxizhen.oopproject/User.db");
			c.setAutoCommit(false);
			//System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs1 = stmt.executeQuery("SELECT *  FROM user_table WHERE ACC='" +account+ "';");
			
			if(rs1.next()){
				acc = rs1.getString("acc");
				pss = rs1.getString("pss");
				name = rs1.getString("name");
				age = rs1.getInt("age");

				if(pss.equals(passward)){
					correct = true;
					System.out.println("Login successfully!");
				}
			}
			rs1.close();
			stmt.close();
			c.commit();
			c.close();
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		//System.out.println("Records created successfully");
		if(correct) user = new User(acc, pss, name ,age);
		return user;
	}
	
	
	
	public static void main(String[] args){
		String account = "hi4u3ykk04";
		String passward = "0000";
		UserControl usc = new UserControl();
		User user, user2;
		user = usc.Login(account, passward);
		int i = usc.Register("hi4u3ykk01", "0001", "michael", 20);
		user2 = usc.Login("hi4u3ykk01", "0001");
		if(user2==null) System.out.println("no user!");

//		String s1=user.getAccount();
//		String s2=user.getPassward();
//		String s3=user.getName();
//		int i1=user.getAge();
//		int i2=user.getCounting();
//		
//		String s = s1+"  "+s2+"  "+s3+"  "+i1+"  "+i2;
//		System.out.println(s);
		
		ArrayList<Integer> arrl = new ArrayList<Integer>();
		ArrayList<Integer> arr2, arr3;
		arrl.add(2);
		arrl.add(3);
		int order_num = user2.addOrder(arrl);
		System.out.println(order_num);
		//int order_num = 8;
		arr2 = user2.getOrdernum();
		arr3 = user2.getTicketnum(arr2.get(0));
		for(int j = 0;j<arr3.size();j++){
			System.out.println(arr3.get(j));
		}
		String str = user2.getTime(arr2.get(2));
		System.out.println(str);
//		arr2 = user.deleteOrder(order_num);
//		if (arr2!=null) {
//			int size = arr2.size();
//			for (int j = 0; j < size; j++) {
//				System.out.println((j + 1) + " is " + arr2.get(j));
//			} 
//		}
		
		//order_num = user2.addOrder(arrl);
//		order_num = 6;
//		System.out.println(order_num);
//		arr2 = user2.deleteOrder(order_num);
//		if (arr2!=null) {
//			int size = arr2.size();
//			for (int j = 0; j < size; j++) {
//				System.out.println((j + 1) + " is " + arr2.get(j));
//			} 
//		}
	}
		
	
	
}
