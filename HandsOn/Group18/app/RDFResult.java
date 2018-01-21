package linkeddata;

public class RDFResult {

	private String parking_name, polititian, parking_zone;
	
	public RDFResult(String parking_name, String polititian, String parking_zone) {
		this.parking_name = parking_name;
		this.polititian = polititian;
		this.parking_zone = parking_zone;
	}

	public String getParkingName() {
		return this.parking_name;
	}

	public String getPolititian() {
		return this.polititian;
	}
	
	public String getParkingZone() {
		return this.parking_zone;
	}
}