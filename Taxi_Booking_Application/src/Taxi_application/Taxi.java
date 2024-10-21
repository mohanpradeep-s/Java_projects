package Taxi_application;

public class Taxi implements Cloneable{
	private char currentLocation = 'A';
	private int customerId;
	private int taxiId;
	private char pickupLocation;
	private char dropLocation;
	private int pickupTime;
	private int dropTime;
	private int earnings;
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public char getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(char currentLocation) {
		this.currentLocation = currentLocation;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getTaxiId() {
		return taxiId;
	}
	public void setTaxiId(int taxiId) {
		this.taxiId = taxiId;
	}
	public char getPickupLocation() {
		return pickupLocation;
	}
	public void setPickupLocation(char pickupLoacation) {
		this.pickupLocation = pickupLoacation;
	}
	public char getDropLocation() {
		return dropLocation;
	}
	public void setDropLocation(char dropLoacation) {
		this.dropLocation = dropLoacation;
	}
	public int getPickupTime() {
		return pickupTime;
	}
	public void setPickupTime(int pickupTime) {
		this.pickupTime = pickupTime;
	}
	public int getDropTime() {
		return dropTime;
	}
	public void setDropTime(int dropTime) {
		this.dropTime = dropTime;
	}
	public int getEarnings() {
		return earnings;
	}
	public void setEarnings(int earnings) {
		this.earnings = earnings;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Taxi-Id :"+getTaxiId()+"\nCustomer_Id :"+getCustomerId()+"\nPickupLocation :"+getPickupLocation()+"\nDropLocation :"+getDropLocation()+"\nPickupTime :"+getPickupTime()+"\nDropTime :"+getDropTime()
		       +"\nCurrentLocation :"+getCurrentLocation()+"\nTotalEarnings :"+getEarnings();
	}
}
