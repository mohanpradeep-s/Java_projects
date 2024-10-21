package railway_ticket_reservation;
import java.util.*;

public class TicketBooker {

	static int availableLowerBerths = 1;
	static int availableMiddleBerths = 1;
	static int availableUpperBerths = 1;
	static int availableRACTickets = 1;
	static int availableWaitingListTickets = 1;
	
	static Queue<Integer> waitingList = new LinkedList<>();
	static Queue<Integer> racList = new LinkedList<>();
	static List<Integer> bookedList = new ArrayList<>();
	
	static List<Integer> lowerBerthPositions = new ArrayList<>(Arrays.asList(1));
	static List<Integer> middleBerthPositions = new ArrayList<>(Arrays.asList(1));
	static List<Integer> upperBerthPositions = new ArrayList<>(Arrays.asList(1));
	static List<Integer> racPositions = new ArrayList<>(Arrays.asList(1));
	static List<Integer> waitingListPositions = new ArrayList<>(Arrays.asList(1));
	
	static Map<Integer,Passenger> passengers = new HashMap<>(); // to store passenger id's as key and Passenger object contains passenger details
	
	public void bookTicket(Passenger p,int berthNum,String allotedBerth) {
		// assign seat number and type of berth
		p.seatNumber = berthNum;
		p.alloted = allotedBerth;
		
		// add passenger details to Map
		passengers.put(p.passengerId, p);
		// add the passenger id to bookedTicketList
		bookedList.add(p.passengerId);
		System.out.println("-------------------Booked Successfully");
	}
	
	public void addToRAC(Passenger p,int racNum,String allotedRAC) {
		// assign seat number and type of berth
		p.seatNumber = racNum;
		p.alloted = allotedRAC;
				
		// add passenger details to Map
		passengers.put(p.passengerId, p);
		// add the passenger id to bookedTicketList
		racList.add(p.passengerId);
		//decrease available RAC tickets by 1
		availableRACTickets--;
		//remove the position alloted to the passenger
		racPositions.remove(0);
		
		System.out.println("-------------------Added To RAC Successfully");
	}
	
	public void addToWL(Passenger p,int waitingNum,String allotedWL) {
		// assign seat number and type of berth
		p.seatNumber = waitingNum;
		p.alloted = allotedWL;
				
		// add passenger details to Map
		passengers.put(p.passengerId, p);
		// add the passenger id to bookedTicketList
		waitingList.add(p.passengerId);
		//decrease available RAC tickets by 1
		availableWaitingListTickets--;
		//remove the position alloted to the passenger
		waitingListPositions.remove(0);
		
		System.out.println("-------------------Added To WL Successfully");
	}
	
	public void cancelTicket(int passengerId) {
		// get Passenger Details
		Passenger p = passengers.get(passengerId);
		passengers.remove(Integer.valueOf(passengerId));
		// remove the bookedTicket from BookedList
		bookedList.remove(Integer.valueOf(passengerId));
		
		// take bookedPosition now it is available
		int bookedPosition = p.seatNumber;
		
		System.out.println("-------------------Ticket Cancelled Successfully");
		
		// add the free position to the berthPositions list and increase 1 position to available berths
		if(p.alloted.equals("L")) {
			availableLowerBerths++;
			lowerBerthPositions.add(bookedPosition);
		}
		else if(p.alloted.equals("M")) {
			availableMiddleBerths++;
			middleBerthPositions.add(bookedPosition);
		}
		else if(p.alloted.equals("U")) {
			availableUpperBerths++;
			upperBerthPositions.add(bookedPosition);
		}
		
		if(racList.size() > 0) {
			Passenger passengerFromRac = passengers.get(racList.poll());
			int racPosition = passengerFromRac.seatNumber;
			racList.remove(Integer.valueOf(passengerFromRac.passengerId));
			racPositions.add(racPosition);
			availableRACTickets++;
			
			if(waitingList.size() > 0) {
				Passenger passengerFromWL = passengers.get(waitingList.poll());
				int wlPosition = passengerFromWL.seatNumber;
				waitingList.remove(Integer.valueOf(passengerFromWL.passengerId));
				waitingListPositions.add(wlPosition);
				availableWaitingListTickets++;
				
				//add the passenger from WL to RAC
				passengerFromWL.seatNumber = racPositions.get(0);
				passengerFromWL.alloted = "RAC";
				racPositions.remove(0);
				racList.add(passengerFromWL.passengerId);
				availableRACTickets--;
			}
			// add the removed passenger from RAC to Berths
			Main.bookTicket(passengerFromRac);
		}
	}
	
	//Print the available seats
	public void printAvailableSeats() {
		System.out.println("Available Lower Berths: "+ availableLowerBerths);
		System.out.println("Available Middle Berths: "+ availableMiddleBerths);
		System.out.println("Available Upper Berths: "+ availableUpperBerths);
		System.out.println("Available RAC Tickets: "+ availableRACTickets);
		System.out.println("Available Waiting List Tickets: "+ availableWaitingListTickets);
		System.out.println("--------------------");
	}
	
	//Print available seats for passengers
	public void printAvailableSeatsPassengers() {
		System.out.println("Available Lower Berths: "+ availableLowerBerths);
		System.out.println("Available Middle Berths: "+ availableMiddleBerths);
		System.out.println("Available Upper Berths: "+ availableUpperBerths);
		System.out.println("--------------------");
	}
	
	//Print the Booked passengers 
	public void PrintBookedPassengers() {
		if(passengers.size()==0) {
			System.out.println("No Passengers are Booked");
			return;
		}
		for(Passenger p : passengers.values()) {
			System.out.println("Passenger ID: "+p.passengerId);
			System.out.println("Passenger Name: "+p.name);
			System.out.println("Passenger Age: "+p.age);
			System.out.println("Status: "+p.seatNumber+p.alloted);
			System.out.println("--------------------");
		}
	}
}
