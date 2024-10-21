package Taxi_application;

import java.util.ArrayList;

public class Taxi_booking {
	
	  private static ArrayList<Taxi> list = new ArrayList<Taxi>();
	  private static int taxiLimit = 4, cus_id_generator=1;
	  private static ArrayList<Taxi> taxibookinghistory = new ArrayList<Taxi>();

	public static String booking(char pickup,char drop,int pickuptime) throws CloneNotSupportedException {
		  
		  if(list.size() < taxiLimit) {
			  list.add(new Taxi());
		  }
		  
		  int min = Integer.MAX_VALUE;
		  Taxi taxiready = null;
		  
		  for(Taxi t : list) {
			  if(t.getDropTime()<=pickuptime && Math.abs(pickup-t.getCurrentLocation())<=min) {
				  if(pickup-t.getCurrentLocation()==min) {
					  if(taxiready!=null && t.getEarnings()<taxiready.getEarnings()) {
						  taxiready = t;
					  }
				  }
				  else {
					  taxiready = t;
					  min = Math.abs(pickup-t.getCurrentLocation());
				  }
			  }
		  }
		  
		  if(taxiready!=null) {
			  taxiready.setCustomerId(cus_id_generator++);
			  taxiready.setPickupTime(pickuptime);
			  taxiready.setPickupLocation(pickup);
			  taxiready.setDropLocation(drop);
			  taxiready.setCurrentLocation(drop);
			  taxiready.setEarnings(taxiready.getEarnings()+(Math.abs(drop-pickup)*15-5)*10+100);
			  taxiready.setDropTime(pickuptime+Math.abs(drop-pickup));
			  taxiready.setTaxiId(list.indexOf(taxiready)+1);
			  taxibookinghistory.add((Taxi)taxiready.clone());
		  }
		  
		  return taxiready!=null ? "\nTaxi number-"+taxiready.getTaxiId()+" is booked." : "Taxi's not available";
		 
	}
	
	public static void display() {
		System.out.println("-------------------");
		for(Taxi t : taxibookinghistory) {
			System.out.println(t.toString());
			System.out.println("-------------------");
		}
	}
}
