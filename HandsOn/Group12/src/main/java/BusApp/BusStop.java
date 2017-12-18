package BusApp;


public class BusStop {

    private String stopName;
    private String stopAddress;
    private String stopZone;

    public BusStop(){}

    public BusStop(String stopName, String stopAddress, String stopZone) {
        this.stopName = stopName;
        this.stopAddress = stopAddress;
        this.stopZone = stopZone;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public String getStopAddress() {
        return stopAddress;
    }

    public void setStopAddress(String stopAddress) {
        this.stopAddress = stopAddress;
    }

    public String getStopZone() {
        return stopZone;
    }

    public void setStopZone(String stopZone) {
        this.stopZone = stopZone;
    }
}
