package com.mrb.test.MOVIE;
import java.sql.*;

/**
 * Class Ticket
 * Ticket 要存的資料與操作
 *
 */
public class Ticket {
	String day;
	int ticket_ID;
	String moviename;
	String starttime;
	int seatnumber;
	String row;
	String hallname;
	String seat;
	String SeatID;
	public String getDay() {
		return day;
	}

	public int getTicket_ID() {
		return ticket_ID;
	}


	public int getSeatnumber() {
		return seatnumber;
	}

	public String getRow() {
		return row;
	}

	public String getHallname() {
		return hallname;
	}

	public String getSeat() {
		return seat;
	}


	public String getMovieName(){
		return moviename;
	}
	
	public String getStartTime(){
		return starttime;
	}
	
	public String getSeatID(){
		return SeatID;
	}
	
	
	
	public Ticket(int ID){
		ticket_ID = ID;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqldroid.SQLDroidDriver");
			c = DriverManager.getConnection("jdbc:sqldroid:/data/data/com.mrb.test.MOVIE/TicketAuto.db");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM TICKET WHERE ID=" + ID + " AND date(day)=date('now');");
			if (rs.isClosed() == true) {
				System.out.println("Ticket Not Found");
				System.out.println("Operation done successfully");
				c.close();
			}
			while (rs.next()) {
				day = rs.getString("day");
				ticket_ID = rs.getInt("id");
				moviename = rs.getString("moviename");
				starttime = rs.getString("starttime");
				seatnumber = rs.getInt("seatnumber");
				row = rs.getString("row");
				hallname = rs.getString("hallname");
				SeatID = rs.getString("seatid");
				String seatnumber_s;
				if(seatnumber < 10)
					seatnumber_s = "0" + seatnumber;
				else
					seatnumber_s = Integer.toString(seatnumber);
				seat = row + seatnumber_s;
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	
	
	/**
	 * 輸入Ticket 的 ID，刪除Ticket
	 * @param ID
	 */
	public static void Delete(int ID) {
		try {
			if (Ticket.Search(ID) == false)
				throw new MovieException("退票失敗，此電影ID不存在");

			Connection c = null;
			Statement stmt = null;
			// Connection c1 = null;
			Statement stmt1 = null;
			Class.forName("org.sqldroid.SQLDroidDriver");
			c = DriverManager.getConnection("jdbc:sqldroid:/data/data/com.mrb.test.MOVIE/TicketAuto.db");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");
			stmt1 = c.createStatement();
			ResultSet rs = stmt1.executeQuery("SELECT * FROM TICKET WHERE ID=" + ID + " AND STARTTIME > Time(current_timestamp, '20 minutes', 'localtime');");
			//System.out.println(rs.getString(2));
			if (rs.isClosed() == true) {
				c.close();
				throw new MovieException("退票失敗，退票須於開場前20分鐘");
			}

			stmt = c.createStatement();
			stmt.executeUpdate("DELETE FROM TICKET WHERE ID=" + ID + ";");
			c.commit();

			// rs.close();
			stmt.close();
			c.close();

			System.out.println("ID=" + ID + " is being DELETED");


		} catch (MovieException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + "1: " + e.getMessage());
			System.exit(0);
		}
		// return true;
	}

	/**
	 * 顯示所有電影票
	 */
	public static void ShowAll() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqldroid.SQLDroidDriver");
			c = DriverManager.getConnection("jdbc:sqldroid:/data/data/com.mrb.test.MOVIE/TicketAuto.db");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM TICKET;");
			System.out.println("-----------------------------------------------------");
			while (rs.next()) {
				String date = rs.getString("day");
				int id = rs.getInt("id");
				String name = rs.getString("moviename");
				String starttime = rs.getString("starttime");
				int seatnumber = rs.getInt("seatnumber");
				String row = rs.getString("row");
				String hallname = rs.getString("hallname");
				System.out.println("DAY = " + date);
				System.out.println("ID = " + id);
				System.out.println("NAME = " + name);
				System.out.println("STARTTIME = " + starttime);
				System.out.println("HALLNAME = " + hallname);
				//System.out.println("ROW = " + row);
				//System.out.println("SEATNUMBER = " + seatnumber);
				String seatnumber_s;
				if(seatnumber < 10)
					seatnumber_s = "0" + seatnumber;
				else
					seatnumber_s = Integer.toString(seatnumber);
				System.out.println("Seat = " + row + "_" + seatnumber_s);
				System.out.println();

			}
			System.out.println("-----------------------------------------------------");
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// System.out.println("Operation done successfully");
	}

	/**
	 * 利用電影ID查詢電影票
	 * @param ID
	 * @return int ID
	 */
	public static boolean Search(int ID) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqldroid.SQLDroidDriver");
			c = DriverManager.getConnection("jdbc:sqldroid:/data/data/com.mrb.test.MOVIE/TicketAuto.db");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM TICKET WHERE ID=" + ID + " AND date(day)=date('now');");
			if (rs.isClosed() == true) {
				System.out.println("Ticket Not Found");
				System.out.println("Operation done successfully");
				c.close();
				return false;
			}
			while (rs.next()) {
				String date = rs.getString("day");
				int id = rs.getInt("id");
				String name = rs.getString("moviename");
				String starttime = rs.getString("starttime");
				int seatnumber = rs.getInt("seatnumber");
				String row = rs.getString("row");
				String hallname = rs.getString("hallname");
				System.out.println("DAY = " + date);
				System.out.println("ID = " + id);
				System.out.println("NAME = " + name);
				System.out.println("STARTTIME = " + starttime);
				System.out.println("HALLNAME = " + hallname);
				//System.out.println("ROW = " + row);
				//System.out.println("SEATNUMBER = " + seatnumber);
				String seatnumber_s;
				if(seatnumber < 10)
					seatnumber_s = "0" + seatnumber;
				else
					seatnumber_s = Integer.toString(seatnumber);
				System.out.println("Seat = " + row + "_" + seatnumber_s);
				System.out.println();

			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// System.out.println("Operation done successfully");
		return true;
	}

	/**
	 * 創Ticket 的db
	 */
	public static void InitializeDB() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqldroid.SQLDroidDriver");
			c = DriverManager.getConnection("jdbc:sqldroid:/data/data/com.mrb.test.MOVIE/TicketAuto.db");
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE if not exists TICKET " + "(DAY DATE," + " ID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " MOVIENAME           TEXT    NOT NULL, " + " STARTTIME            TIME     NOT NULL, "
					+ " ROW        CHAR(1), " + " SEATNUMBER    INT, HALLNAME   TEXT  NOT NULL ,SEATID TEXT)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// System.out.println("Table created successfully");
	}

	/**
	 * 將電影票加入資料庫
	 * @param MovieName
	 * @param StartTime
	 * @param Row
	 * @param SeatNumber
	 * @param Hallname
	 * @return int 電影id
	 */
	public static int Insert(String MovieName, String StartTime, char Row, int SeatNumber, String Hallname,String SeatID) {
		int id = 0;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqldroid.SQLDroidDriver");
			c = DriverManager.getConnection("jdbc:sqldroid:/data/data/com.mrb.test.MOVIE/TicketAuto.db");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "INSERT INTO TICKET (DAY,MOVIENAME,STARTTIME,ROW,SEATNUMBER,HALLNAME,SEATID) " + "VALUES ( date('now'),  '"
					+ MovieName + "' ,'" + StartTime + "','" + Row + "'," + SeatNumber + ",'" + Hallname +"','"+ SeatID +"');";
			stmt.executeUpdate(sql);

			stmt = c.createStatement();
			// ResultSet rs = stmt.executeQuery( "SELECT MAX(ID) AS
			// DAY,ID,MOVIENAME,STARTTIME,ROW,SEATNUMBER FROM TICKET" );
			ResultSet rs = stmt.executeQuery("SELECT MAX(ID),DAY,ID,MOVIENAME,STARTTIME,ROW,SEATNUMBER,SEATID FROM TICKET");

			//String date = rs.getString("day");
			id = rs.getInt("id");
			String name = rs.getString("moviename");
			String starttime = rs.getString("starttime");
			int seatnumber = rs.getInt("seatnumber");
			String row = rs.getString("row");
			String SEATID=rs.getString("seatid");

			/*System.out.println("DAY = " + date);
			System.out.println("ID = " + id);
			System.out.println("NAME = " + name);
			System.out.println("STARTTIME = " + starttime);
			System.out.println("ROW = " + row);
			System.out.println("SEATNUMBER = " + seatnumber);
			System.out.println();*/
			System.out.println("SEATID = " + SEATID);

			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// System.out.println("Records created successfully");
		return id;
	}
	
	public void show_one_ticket(){
		System.out.println("day : " + day );
		System.out.println("ID : " + ticket_ID );
		System.out.println("moviename : " + moviename );
		System.out.println("starttime : " + starttime );
		System.out.println("seat : " + seat );
		System.out.println("hallname = " + hallname );
		System.out.println("seatID = " + SeatID );

	}

	/**
	 * 測試的main function
	 * @param args
	 */
	/*public static void main(String args[]) {
		 Ticket.InitializeDB();
		String MovieName = "Fast and Furious 9";
		String StartTime = "17:00";
		String Hallname = "123";
		char Row = 'K';
		int SeatNumber = 4;

		// ID is auto increment not need to input
		Ticket.Insert(MovieName, StartTime, Row, SeatNumber, Hallname);
		 Ticket.Insert("THREE IDIOT", "04:53", 'E', 13, "123456789");
		 //Ticket.Delete(56);
		 Ticket.ShowAll();
		//Ticket.Search(49);
		// boolean f=Ticket.Search(1);
		// boolean k=Ticket.Search(10);
		// boolean l=Ticket.Delete(3);
		// boolean l=Ticket.Delete(30);
		// Ticket.ShowAll();
		 

	}*/
}