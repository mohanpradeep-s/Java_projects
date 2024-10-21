package railway_ticket_reservation;

public class Passenger {
	static int id = 1; // id for every passenger
	String name;
	int age;
	String berthPreference;
	int passengerId;
	String alloted;
	int seatNumber;
	
	public Passenger(String name,int age,String berthPreference){
		this.name = name;
		this.age = age;
		this.berthPreference = berthPreference;
		this.passengerId = id++;
		alloted = "";
		seatNumber = -1;
		
	}
}
