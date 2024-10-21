package railway_reservation_part2;

public class TicketCanceling {
	
	private int pnr;
	private int seats;
	private TicketSystem ticketSystem;
	
	TicketCanceling(int pnr,int seats){
		this.pnr = pnr;
		this.seats = seats;
		this.ticketSystem = TicketSystem.getInstance();
	}
	
	private void cancelTicket() {
		Ticket ticket = ticketSystem.getTicket(pnr);
		WaitingListManager waitingListManager = new WaitingListManager();
		
		if(ticket!=null) {
			//if ticket is in Waiting List
			if(ticket.getTicketStatus()==TicketStatus.WaitingList) {
				waitingListManager.waitingListRemoval(pnr, seats, ticket);
				return;
			}
			//if in booked List
			int bookedSeats = ticket.getSeats();
			char source = ticket.getSource(),destination = ticket.getDestination();
			
			//partial Cancellation
			if(bookedSeats > seats) {
				ticket.setSeats(bookedSeats - seats);
				ticketSystem.storePartiallyCancelledSeats(pnr, seats);
				System.out.println("Partially Cancelled pnr: "+pnr);
			}
			else {
				ticketSystem.processCancellation(pnr, ticket);
				System.out.println("Cancelled Ticket pnr: "+pnr);
			}
			ticketSystem.increaseSeatAvailability(source, destination, seats);//update how many seats are free
			waitingListManager.processWaitingList();// check whether we pick a ticket from waiting list
		}
		else {
			System.out.println("Ticket with pnr "+pnr+"not found.");
		}
	}
	
	protected void execute() {
		this.cancelTicket();
	}
}
