package com.mrb.test.Class.MOVIE;

import java.io.IOException;
import java.util.ArrayList;

import com.mrb.test.Class.HALL.*;

public class MovieControl {
	private static String CurrentMovieName;
	private static Time CurrentSession;
	private static int Amount;
	private static ArrayList<String> SeatIDList;
	private static boolean Rowbook = false;
	private static boolean Regionbook = false;
	private static String currentRegion;
	private static String currentRow;
	private static ArrayList<Integer> CancelTicket = new ArrayList<Integer>(10);
	private static boolean isBigHall = false;
	
	public static void reset(){
		CurrentMovieName = null;
		CurrentSession = null;
		Amount = 0;
		SeatIDList = null;
		Rowbook = Regionbook = isBigHall = false;
		currentRegion = currentRow = null;
		CancelTicket = null;
		
	}
	
	public static boolean getisBigHall(){
		return isBigHall;
	}
	
	public static void setCancelTicket(ArrayList<Integer> ticketIDs) {
		for (int i = 0; i < ticketIDs.size(); i++) {
			CancelTicket.add(ticketIDs.get(i));
		}
	}

	public static void setRowbook() {
		Rowbook = true;
	}

	public static void setRegionbook() {
		Regionbook = true;
	}

	public static String getCurrentMovieName() {
		return CurrentMovieName;
	}

	public static void setCurrentMovieName(String currentMovieName) {
		CurrentMovieName = currentMovieName;
	}

	public static Time getCurrentSession() {
		return CurrentSession;
	}

	public static void setCurrentSession(String currentSession) {
		CurrentSession = new Time(currentSession);
	}

	public static int getAmount() {
		return Amount;
	}

	public static void setAmount(int account) {
		Amount = account;
	}

	/*
	 * public static void setSeatIDList(ArrayList<String> l){ for(int i = 0; i <
	 * l.size(); i++){ SeatIDList.set(i, l.get(i)); } }
	 */

	/**
	 * 一開始先把DB創好，資料存好
	 * 
	 * @throws IOException
	 */
	public static void InitMovieData() throws IOException {
		Movie.Creat_Mov_Db();
		Hall.InitialHall();
		Ticket.InitializeDB();
	}

	/**
	 * 列出所有電影名稱
	 * 
	 * @return ArrayList<String>
	 */
	public static ArrayList<String> getMovieList() {
		return Movie.ShowAll();
	}

	/**
	 * 選好電影名稱後搜尋該名稱的所有場次
	 * 
	 * @param String
	 *            MovieName
	 * @return ArrayList<String>
	 */
	public static ArrayList<String> getSession() {
		return Movie.ShowAllTime(CurrentMovieName);
	}

	public static Movie getMovie() throws IOException {
		if(new Movie(CurrentMovieName, CurrentSession).isIsbig() == true)
			isBigHall = true;
		return new Movie(CurrentMovieName, CurrentSession);
	}

	public static boolean IsEnough() throws IOException {
		if (Amount < getMovie().getHall().SearchEmptySeat())
			return true;
		else
			return false;
	}

	public static ArrayList<String> ShowRegionState() throws IOException {
		ArrayList<String> regionstate = new ArrayList(10);
		if (getMovie().getHall().getIsBigHall() == true) {
			regionstate.add("red : " + getMovie().getHall().RegionEmptySeat("red") + "seats");
			regionstate.add("yellow : " + getMovie().getHall().RegionEmptySeat("yellow") + "seats");
			regionstate.add("blue : " + getMovie().getHall().RegionEmptySeat("blue") + "seats");
			regionstate.add("gray : " + getMovie().getHall().RegionEmptySeat("gray") + "seats");
			return regionstate;
		} else {
			regionstate.add("-1");
			return regionstate;
		}
	}

	public static ArrayList<String> ShowRowState() throws IOException {
		ArrayList<String> rowstate = new ArrayList(10);
		rowstate.add("A : " + getMovie().getHall().RowEmptySeat("A") + "seats");
		rowstate.add("B : " + getMovie().getHall().RowEmptySeat("B") + "seats");
		rowstate.add("C : " + getMovie().getHall().RowEmptySeat("C") + "seats");
		rowstate.add("D : " + getMovie().getHall().RowEmptySeat("D") + "seats");
		rowstate.add("E : " + getMovie().getHall().RowEmptySeat("E") + "seats");
		rowstate.add("F : " + getMovie().getHall().RowEmptySeat("F") + "seats");
		rowstate.add("G : " + getMovie().getHall().RowEmptySeat("G") + "seats");
		rowstate.add("H : " + getMovie().getHall().RowEmptySeat("H") + "seats");
		rowstate.add("I : " + getMovie().getHall().RowEmptySeat("I") + "seats");
		if (getMovie().getHall().getIsBigHall() == true) {
			rowstate.add("J : " + getMovie().getHall().RowEmptySeat("J") + "seats");
			rowstate.add("K : " + getMovie().getHall().RowEmptySeat("K") + "seats");
			rowstate.add("L : " + getMovie().getHall().RowEmptySeat("L") + "seats");
			rowstate.add("M : " + getMovie().getHall().RowEmptySeat("M") + "seats");
		}
		return rowstate;
	}

	public static void setcurrentRow(int index) {
		if (index == 0)
			currentRow = "A";
		else if (index == 1)
			currentRow = "B";
		else if (index == 2)
			currentRow = "C";
		else if (index == 3)
			currentRow = "D";
		else if (index == 4)
			currentRow = "E";
		else if (index == 5)
			currentRow = "F";
		else if (index == 6)
			currentRow = "G";
		else if (index == 7)
			currentRow = "H";
		else if (index == 8)
			currentRow = "I";
		else if (index == 9)
			currentRow = "J";
		else if (index == 10)
			currentRow = "K";
		else if (index == 11)
			currentRow = "L";
		else if (index == 12)
			currentRow = "M";
	}

	public static void setcurrentRegion(int index) {
		if (index == 0)
			currentRegion = "red";
		else if (index == 1)
			currentRegion = "yellow";
		else if (index == 2)
			currentRegion = "blue";
		else if (index == 3)
			currentRegion = "gray";
	}

	/*
	 * public void book() throws IOException{ for(int i = 0; i <
	 * SeatIDList.size(); i++){ String substring =
	 * SeatIDList.get(i).substring(1, SeatIDList.get(i).length()-1); int num =
	 * Integer.valueOf(substring);
	 * getMovie().getHall().SeatNumberBook(SeatIDList.get(0), num); } }
	 */
	public static ArrayList<Integer> book() throws IOException {
		if (Rowbook == true && Regionbook == false)
			return getMovie().getHall().RowBook(Amount, currentRow);
		else if (Rowbook == false && Regionbook == true)
			return getMovie().getHall().RegionBook(Amount, currentRegion);
		else
			return getMovie().getHall().GeneralBook(Amount);

	}

	public static Ticket searchTicket(int id) {
		return new Ticket(id);
	}

	public static void Cancel() throws IOException {
		for (int i = 0; i < CancelTicket.size(); i++) {
			Ticket ticket = new Ticket(CancelTicket.get(i));
			Movie movie = new Movie(ticket.getMovieName(), new Time(ticket.getStartTime()));
			movie.getHall().Cancel(ticket.getSeatID());
			ticket.Delete(CancelTicket.get(i));
		}
	}
	
/*	public static void main(String[] args) throws IOException {
		InitMovieData();
		boolean RepeatUser = true;
		boolean RepeatBooking = true;
		boolean RepeatSearch = true;
		User Consumer;

		while (RepeatUser) {
			System.out.println("Welcome to the Movie system, what would you like to do? ");
			System.out.println("1.Booking 2.Searching 3.Cancel?");
			Scanner scanner = new Scanner(System.in);
			int Mode = 0;
			Mode = scanner.nextInt();
			RepeatBooking = true;
			RepeatSearch = true;

			if (Mode == 2) { // 查詢
				while (RepeatSearch) {
					System.out.println("---------------Search Mode------------------");
					System.out.println(
							"1.Judgement 2.Movie Time or Length 3.Tiket_ID 4.Movie_ID 5.Region/Row Empty ,or 0 to Main Page");
					int SearchMode = scanner.nextInt();
					switch (SearchMode) {
					case 1:
						MovieMain.SJudgement();
						break;
					case 2:
						MovieMain.SMovieTimeLength();
						break;
					case 3:
						MovieMain.STicketID();
						break;
					case 4:
						MovieMain.SMovieID();
						break;
					case 5:
						MovieMain.SRegionRow();
						break;
					case 0:
						RepeatSearch = false;
						break;
					}

				}
			} else if (Mode == 1) { // 訂票
				
				 * System.out.
				 * println("Please enter Consumer's LastName and Age (with space between) "
				 * ); Scanner scanner = new Scanner(System.in);
				 * System.out.printf("Age = "); int age=scanner.nextInt();
				 * System.out.printf("Name = "); String name=scanner.next();
				 * 
				 * if(age>0&&age<150) Consumer=new User(age,name); else
				 * continue;
				 
				while (RepeatBooking) {
					System.out.println("-----------------------------");
					System.out.println("user_index:");
					int user_index = scanner.nextInt();

					System.out.println("Movie to play today:");
					int i = 1;
					ArrayList<String> MovieList = getMovieList();
					for (String e : MovieList) {
						System.out.println(i + "." + e);
						i++;
					}

					System.out.printf("Which movie?  ");
					int Choice = scanner.nextInt();
					if (Choice > MovieList.size()) {
						System.out.println("No such Movie! ");
						continue;
					}
					setCurrentMovieName(MovieList.get(Choice - 1));
					// System.out.println("Movie! "+MovieName);
					
					 * String clas = Movie.SearchMovieName(MovieName); int
					 * user_age = User.SearchIndex(user_index); boolean can_book
					 * = false; switch (clas) { case ("限制"): if (user_age >= 18)
					 * can_book = true; break; case ("保護"): if (user_age >= 6)
					 * can_book = true; break; case ("輔導"): if (user_age >= 15)
					 * can_book = true; break; default: can_book = true;
					 * 
					 * }
					 
					// try {
					// if (true) {
					ArrayList<String> Session = getSession();
					System.out.println(CurrentMovieName);
					if (Session.size() == 0) {
						System.out.println("No session today ");
						continue;
					}
					System.out.println("Sessions today ");
					i = 1;
					for (String e : Session) {
						System.out.println(i + ". " + e);
						i++;
					}
					System.out.printf("Which session? ");
					int SessionChoice = scanner.nextInt();
					setCurrentSession(Session.get(SessionChoice - 1));
					if (SessionChoice > Session.size()) {
						System.out.println("Not in Range ");
						continue;
					}
					// String ChoiceTime = Session.get(SessionChoice - 1);
					// Time MovieTime = new Time();
					// MovieTime.StringtoTime(ChoiceTime);
					// Movie Mov = new Movie(MovieName, MovieTime);
					Movie Mov = getMovie();

					System.out.println("Empty Seat: " + Mov.getHall().SearchEmptySeat());
					Mov.getHall().Show();

					System.out.printf("How many people? ");
					int Amounts = scanner.nextInt();
					setAmount(Amounts);
					System.out.println();
					boolean isenough = IsEnough();
					if (isenough == false)
						System.out.println("座位數量不足");
					ArrayList<String> RegionState = ShowRegionState();
					i = 1;
					for (String e : RegionState) {
						System.out.println(i + ". " + e);
						i++;
					}
					System.out.println();
					System.out.println("Regionbook? Y/N");
					String isregion = scanner.next();
					ArrayList<Integer> bookticketID;
					if (isregion.charAt(0) == 'Y' || isregion.charAt(0) == 'y') {
						System.out.println("here");
						setRegionbook();
						System.out.println("which region");
						int region = Integer.valueOf(scanner.next());
						setcurrentRegion(region - 1);
						System.out.println("here2");
						bookticketID = book();
					} else {
						ArrayList<String> RowState = ShowRowState();
						i = 1;
						for (String e : RowState) {
							System.out.println(i + ". " + e);
							i++;
						}
						System.out.println("Rowbook? Y/N");
						String isrow = scanner.next();
						if (isrow.charAt(0) == 'Y' || isrow.charAt(0) == 'y') {
							setRowbook();
							System.out.println("which row");
							int row = Integer.valueOf(scanner.next());
							setcurrentRow(row - 1);
							bookticketID = book();
						} else
							bookticketID = book();
					}

					System.out.println();
					int counter = 0;
					for(counter = 0; counter < bookticketID.size(); counter++){
						new Ticket(bookticketID.get(counter)).show_one_ticket();
						System.out.println("--------------------------------------");
						System.out.println("hey1");
						
					}
					System.out.println("hey2");
					System.out.println(counter);

					
					  String Region; if (Mov.isIsbig()) { System.out.
					  printf("Specific Region? N/Red/Yellow/Blue/Gray ");
					  Region = scanner.next(); boolean cont = false; if
					  (Cont.charAt(0) == 'N' || Cont.charAt(0) == 'n'){
					  if(Region.charAt(0) == 'N' || Region.charAt(0) == 'n')
					  Mov.getHall().GeneralBook(Amounts); else
					  Mov.getHall().RegionBook(Amounts, Region); }
					  
					  else if (Cont.charAt(0) == 'Y' || Cont.charAt(0) == 'y')
					  { cont = true; if (Region.charAt(0) == 'N' ||
					  Region.charAt(0) == 'n')
					  Mov.getHall().ConditionBook(Amounts, cont); else
					  Mov.getHall().ConditionBook(Amounts, cont, Region); } }
					  else { boolean cont = false; if (Cont.charAt(0) == 'N' ||
					  Cont.charAt(0) == 'n')
					  Mov.getHall().GeneralBook(Amounts); else if
					  (Cont.charAt(0) == 'Y' || Cont.charAt(0) == 'y') {
					  Mov.getHall().ConditionBook(Amounts, cont); } }
					 
					System.out.println("here3");
					Mov.getHall().Show();

					RepeatBooking = false;
					// RepeatUser = false;
				} // else
					// throw new MovieException("失敗，該電影分級為" + clas + "，" +
					// user_age + "歲無法購買");
				// } catch (MovieException e) {
				// System.out.println(e.getMessage());
				// }

				// }

			}
			else{ // 退票 
				//System.out.println("Ticket ID?"); 
				//int ticket_id =scanner.nextInt(); 
				ArrayList<Integer> tickets = new ArrayList(10);
				tickets.add(20);
				tickets.add(15);
				System.out.println(tickets.size());
				System.out.println(tickets.get(0));
				System.out.println(tickets.get(1));
				//tickets.add(5);
				
				setCancelTicket(tickets);
				Cancel(); 
			}

		}
		reset();
	}*/

}
