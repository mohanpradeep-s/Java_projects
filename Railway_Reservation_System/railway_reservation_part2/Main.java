package railway_reservation_part2;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("---------------\n1.Book Ticket\n2.Cancel Ticket\n3.Print Chart\n4.Exit\n----------------");
			int choice = sc.nextInt();
			
			switch(choice) {
				case 1: { // For Booking Tickets
					System.out.println("Enter the Source: ");
					char source = sc.next().charAt(0);
					System.out.println("Enter the Destination: ");
					char destination = sc.next().charAt(0);
					System.out.println("Enter No of Seats to Book: ");
					int seats = sc.nextInt();
					
					if(source!=destination) {
						TicketBooking tb = new TicketBooking(source, destination, seats);
						tb.execute();
					}
					else {
						System.out.println("Choose valid Source and Destination.");
					}
					break;
				}
				case 2 : {  // For Canceling Tickets
					System.out.println("Enter your PNR Number");
					int pnr = sc.nextInt();
					System.out.println("Enter No of Seats to Cancel: ");
					int seats = sc.nextInt();
					TicketCanceling tc = new TicketCanceling(pnr, seats);
					tc.execute();
					break;
				}
				case 3 : { //For Print the chart Booked and Available Tickets
					TicketSystem.getInstance().printChart();
					break;
				}
				case 4 : {
					break;
				}
				default : {
					System.out.println("Unfortunately Stopped!!");
					break;
				}
			}
		}
	}

}
