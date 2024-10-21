package railway_reservation_part2;

public class WaitingListManager {

	private TicketSystem ticketSystem;
	
	WaitingListManager(){
		this.ticketSystem = TicketSystem.getInstance();
	}
	
	// Handle WaitingList to BookingList process
	protected void processWaitingList() {
		for(Ticket waiting : ticketSystem.waitingList.values()) {
			boolean isValid;
			char source = waiting.getSource(),destination = waiting.getDestination();
			int seats = waiting.getSeats();
			
			//seat availability check
			isValid = ticketSystem.checkSeatAvailability(source, destination, seats);
			
			if(isValid) {
				ticketSystem.decreaseSeatAvailability(source, destination, seats);// update seatsAvailable Array
				ticketSystem.setSeatsBooked(ticketSystem.getSeatsBooked()- seats);// update seatsBooked on waitingList
				updateTicketToBookingList(waiting);
			}
		}
	}
	
	protected void updateTicketToBookingList(Ticket waiting) {
		int pnrNumber = waiting.getPnrNumber();
		waiting.setTicketStatus(TicketStatus.Booked);// status from waiting list to booked
		ticketSystem.addToBookedTickets(pnrNumber, waiting);// add waiting to booked
		ticketSystem.waitingList.remove(pnrNumber);//remove from waitingList Queue
		System.out.println("Booking Confirmed for pnr number"+pnrNumber);
	}
	
	protected void waitingListEntry(char source,char destination,int seats) {
		WaitingList wl = new WaitingList(source, destination, seats);
		wl.execute();
	}
	
	protected void waitingListRemoval(int pnr,int seatsToCancel,Ticket waitingListTicket) {
		int seatsBooked = waitingListTicket.getSeats();
		if(seatsBooked == seatsToCancel) {
			System.out.println("Cancelled ticket in waiting list with pnr: "+pnr);
		}
		else {
			waitingListTicket.setSeats(waitingListTicket.getSeats()- seatsToCancel);
			System.out.println("Partially cancelled ticket in waiting list with pnr: "+pnr);
		}
		ticketSystem.setSeatsBooked(ticketSystem.getSeatsBooked()- seatsToCancel);
	}
}
