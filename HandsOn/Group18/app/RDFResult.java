package linkeddata;

public class RDFResult {

	private String parking_name, parking_direction;
	private int numberSpots;
	
	public RDFResult(String parking_name, String parking_direction, int numberSpots) {
		this.parking_name = parking_name;
		this.parking_direction = parking_direction;
		this.numberSpots = numberSpots;
	}

	public String getParkingName() {
		return this.parking_name;
	}

	public String getParkingDirection() {
		return this.parking_direction;
	}
	
	public int getNumberSpots() {
		return this.numberSpots;
	}
}