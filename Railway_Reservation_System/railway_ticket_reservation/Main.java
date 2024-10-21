package railway_ticket_reservation;

import java.util.Scanner;

public class Main {
	
	public static void bookTicket (Passenger p) {
		Scanner sc = new Scanner(System.in);
		TicketBooker tb = new TicketBooker();
		//if no WL is available means all tickets are filled
		if(TicketBooker.availableWaitingListTickets==0) {
			System.out.println("No Tickets Available");
			return;
		}
		//check if berth is available
		if((p.berthPreference.equals("L") && TicketBooker.availableLowerBerths > 0 ) ||
			(p.berthPreference.equals("M") && TicketBooker.availableMiddleBerths > 0) ||
			(p.berthPreference.equals("U") && TicketBooker.availableUpperBerths > 0)) 
		{
			System.out.println("Preferred Berth Available");
			
			if(p.berthPreference.equals("L")) {
				System.out.println("Lower Berth Given");
				tb.bookTicket(p,(TicketBooker.lowerBerthPositions.get(0)), "L");
				TicketBooker.lowerBerthPositions.remove(0);
				TicketBooker.availableLowerBerths--;
			}
			else if(p.berthPreference.equals("M")) {
				System.out.println("Middle Berth Given");
				tb.bookTicket(p,(TicketBooker.middleBerthPositions.get(0)), "M");
				TicketBooker.middleBerthPositions.remove(0);
				TicketBooker.availableMiddleBerths--;
			}
			else if(p.berthPreference.equals("U")) {
				System.out.println("Upper Berth Given");
				tb.bookTicket(p,(TicketBooker.upperBerthPositions.get(0)), "U");
				TicketBooker.upperBerthPositions.remove(0);
				TicketBooker.availableUpperBerths--;
			}
		}
			
			//preference not available -> book available ticket
			else if(TicketBooker.availableLowerBerths>0 || TicketBooker.availableMiddleBerths>0 || TicketBooker.availableUpperBerths>0){
				System.out.println("Preferred berth not available");
				TicketBooker tb1 = new TicketBooker();
				tb1.printAvailableSeatsPassengers();
				System.out.println("Choose any of these available seats or enter 5 to cancel booking");
				String berth = sc.next();
				if(berth.equals("5")) {
					System.out.println("---Booking Cancelled---");
					return;
				}
				if(TicketBooker.availableLowerBerths > 0 && berth.equals("L")) {
					System.out.println("Lower Berth Given");
					tb.bookTicket(p,(TicketBooker.lowerBerthPositions.get(0)), "L");
					TicketBooker.lowerBerthPositions.remove(0);
					TicketBooker.availableLowerBerths--;
				}
				else if(TicketBooker.availableMiddleBerths > 0 && berth.equals("M")) {
					System.out.println("Middle Berth Given");
					tb.bookTicket(p,(TicketBooker.middleBerthPositions.get(0)), "M");
					TicketBooker.middleBerthPositions.remove(0);
					TicketBooker.availableMiddleBerths--;
				}
				else if(TicketBooker.availableUpperBerths > 0 && berth.equals("U")) {
					System.out.println("Upper Berth Given");
					tb.bookTicket(p,(TicketBooker.upperBerthPositions.get(0)), "U");
					TicketBooker.upperBerthPositions.remove(0);
					TicketBooker.availableUpperBerths--;
				}
			}
			else {
				// if no berth is available
				if(TicketBooker.availableRACTickets > 0) {
					System.out.println("RAC available");
					tb.addToRAC(p, (TicketBooker.racPositions.get(0)), "RAC");
				}
				
				// if no RAC is available
				else if(TicketBooker.availableWaitingListTickets > 0) {
					System.out.println("WaitingList available");
					tb.addToWL(p, (TicketBooker.waitingListPositions.get(0)), "WL");
				}
			}
	}
	
	public static void cancelTicket(int id) {
		TicketBooker tb = new TicketBooker();
		tb.cancelTicket(id);
	}
	

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		boolean loop = true;
		
		while(loop) {
			System.out.println("----------------------\n1.Book Ticket\n2.Cancel Ticket\n3.Print Booked Tickets\n4.Print Available Tickets\n5.Exit\n----------------------");
			int choice = sc.nextInt();
			switch(choice) {
				// Booking Ticket
				case 1 : {
					 System.out.println("Enter the Name,Age,BerthPreference(U,M,L): ");
					 String name = sc.next();
					 int age = sc.nextInt();
					 sc.nextLine();
					 String berthPreference = sc.next();
					 Passenger p = new Passenger(name, age, berthPreference);
					 bookTicket(p);
				}
				break;
				// Cancel Ticket
				case 2 : {
					 System.out.println("Enter the Seat No:");
					 int seatNo = sc.nextInt();
					 cancelTicket(seatNo);
				}
				break;
				//Booked Tickets
				case 3 : {
					TicketBooker tb = new TicketBooker();
					tb.PrintBookedPassengers();
				}
				break;
				//Available Tickets
				case 4 : {
					TicketBooker tb = new TicketBooker();
					tb.printAvailableSeats();
				}
				break;
				case 5 : {
					loop = false;
				}
				break;
				
				default:break;
			}
		}
		
		sc.close();
	}

}
