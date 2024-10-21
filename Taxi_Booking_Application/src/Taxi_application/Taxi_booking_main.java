package Taxi_application;

import java.util.Scanner;

public class Taxi_booking_main {

	public static void main(String[] args) throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		boolean book = true;
		
		while(book) {
			System.out.println("-----------------------");
			System.out.println("Enter an operation listing below:\n1.Book a Taxi.\n2.Display Details\n3.Exit");
			System.out.println("-----------------------");
			int operation = sc.nextInt();
			switch(operation) {
				case 1: System.out.println("Enter your Pickup Location from A to F: ");
					    char ch= sc.next().charAt(0);
					    int count=2;
					    while(!containsChar(ch) && count>0) {
					    	System.out.println("Please Enter Location within range: ");
					    	ch= sc.next().charAt(0);
					    	count--;
					    }
					    if(count==0 && !containsChar(ch)) {
					    	System.out.println("Booking Blocked!...");
					    	book = false;
					    	break;
					    }
					    count=2;
					    System.out.println("Enter your Drop Location from A to F: ");
					    char ch1= sc.next().charAt(0);
					    while(!containsChar(ch1) && count>0) {
					    	System.out.println("Please Enter Location within range: ");
					    	ch1= sc.next().charAt(0);
					    	count--;
					    }
					    if(ch1==ch) {
				    		while(ch1==ch && count>0) {
				    			System.out.println("Please Don't Enter Pickup Location again: ");
						    	ch1= sc.next().charAt(0);
						    	count--;
				    		}
				    	}
					    if((count==0 && !containsChar(ch1)) || (count==0 && ch1==ch)) {
					    	System.out.println("Booking Blocked!...");
					    	book = false;
					    	break;
					    }
					    System.out.println("Enter Time: ");
					    int time = sc.nextInt();
					    System.out.println(Taxi_booking.booking(ch, ch1, time));
					    break;
					    
				case 2: Taxi_booking.display();
					    
				case 3: book=false;
						System.out.println("Thank you!..");
						break;
						
				default: book=false;
						 System.out.println("Invalid Input! Try again");
						 break;
					    
			}
		}
	}
	
	static boolean containsChar(char ch) {
		if(ch>='A' && ch<='F') {
			return true;
		}
		return false;
	}

}
