package linkeddata;

public class RDFResult {

	private String parking_name, parking_direction, parking_zone;
	
	public RDFResult(String parking_name, String parking_direction, String parking_zone) {
		this.parking_name = parking_name;
		this.parking_direction = parking_direction;
		this.parking_zone = parking_zone;
	}

	public String getParkingName() {
		return this.parking_name;
	}

	public String getParkingDirection() {
		return this.parking_direction;
	}
	
	public String getParkingZone() {
		return this.parking_zone;
	}
}