package railway_reservation_part2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TicketSystem {

	// Tickets Information
	protected Map<Integer, Ticket> ticketsBooked = new HashMap<>();
	protected Map<Integer, Ticket> ticketsCancelled = new HashMap<>();
	protected ConcurrentHashMap<Integer, Ticket> waitingList = new ConcurrentHashMap<>();
	
	// Seats Information
	protected int[] seatsAvailable = new int[5];
	
	// To Store the no of partial seats cancelled 
	protected Map<Integer,Integer> partiallyCancelled = new HashMap<>();
	
	// WaitingList seats check
	protected int seatsBooked = 0; // To track seats booked in waiting list
	
	private static TicketSystem instance = null;
	
	private TicketSystem() {
		Arrays.fill(seatsAvailable, 8);
	}
	
	// Singleton Logic
	public static TicketSystem getInstance() {
		if(instance == null) {
			instance = new TicketSystem();
		}
		return instance;
	}
	
	protected void setSeatsBooked(int seatsBooked) {
		this.seatsBooked = seatsBooked;
	}
	
	protected int getSeatsBooked() {
		return seatsBooked;
	}
	
	//methods used in booking class and waitingListManager class
	public void addToBookedTickets(int pnr,Ticket ticket) {
		ticketsBooked.put(pnr, ticket);
	}
	
	protected void decreaseSeatAvailability(char source,char destination,int seats) {
		for(int i=source-'A';i<destination-'A';i++) {
			seatsAvailable[i] -= seats;
		}
	}
	
	protected boolean checkSeatAvailability(char source,char destination,int seats) {
		for(int i=source-'A';i<destination-'A';i++) {
			if(seatsAvailable[i] < seats) {
				return false;
			}
		}
		return true;
	}
	
	//methods used in Cancel class
	protected Ticket getTicket(int pnr) {
		Ticket bookedTicket = ticketsBooked.get(pnr);
		return bookedTicket != null ? bookedTicket : waitingList.get(pnr);
	}
	
	protected void increaseSeatAvailability(char source,char destination,int seats) {
		for(int i=source-'A';i<destination-'A';i++) {
			seatsAvailable[i] += seats;
		}
	}
	
	protected void storePartiallyCancelledSeats(int pnr,int seats) {
		partiallyCancelled.put(pnr, partiallyCancelled.getOrDefault(pnr, 0) + seats);
	}
	
	protected void processCancellation(int pnr,Ticket ticket) {
		Integer getSeats = partiallyCancelled.get(pnr);
		int seatsToAdd = getSeats != null ? getSeats : 0;
		ticket.setSeats(ticket.getSeats() + seatsToAdd);
		addToCanceledTickets(pnr,ticket);
	}
	
	protected void addToCanceledTickets(int pnr,Ticket ticket) {
		ticket.setTicketStatus(TicketStatus.Cancelled);
		ticketsCancelled.put(pnr, ticket);
		removeFromBookedTicket(pnr);
	}
	
	protected void removeFromBookedTicket(int pnr) {
		ticketsBooked.remove(pnr);
	}
	
	//Print all Details
	public void printChart() {
		System.out.println("\nTickets Booked: ");
		ticketsBooked.values().forEach(System.out::println);
		
		System.out.println("\nTickets Cancelled: ");
		ticketsCancelled.values().forEach(System.out::println);
		
		System.out.println("\nTickets in Waiting List: ");
		waitingList.values().forEach(System.out::println);
		
		System.out.println("\nSeat Availability : "+Arrays.toString(seatsAvailable));
		
		System.out.println("\n\t\tSeats Booked: ");
		System.out.println("\t1\t2\t3\t4\t5\t6\t7\t8");
		
		for(char c='A';c<='E';c++) {
			System.out.println();
			System.out.print(c);
			int seatsBooked = 8 - seatsAvailable[c - 'A']; // total seats(8) - available seats = booked seats
			for(int i=0;i<seatsBooked;i++) {
				System.out.print("\t*");
			}
			System.out.println();
		}
		System.out.println();
	}
}
