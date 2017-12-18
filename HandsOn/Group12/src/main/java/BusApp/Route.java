package BusApp;

public class Route {

    private String idRoute;
    private String routeLine;
    private String journey;
    private String routeURL;

    public Route(){}

    public Route(String idRoute, String routeLine, String journey, String routeURL) {
        this.idRoute = idRoute;
        this.routeLine = routeLine;
        this.journey = journey;
        this.routeURL = routeURL;
    }

    public String getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(String idRoute) {
        this.idRoute = idRoute;
    }

    public String getRouteLine() {
        return routeLine;
    }

    public void setRouteLine(String routeLine) {
        this.routeLine = routeLine;
    }

    public String getJourney() {
        return journey;
    }

    public void setJourney(String journey) {
        this.journey = journey;
    }

    public String getRouteURL() {
        return routeURL;
    }

    public void setRouteURL(String routeURL) {
        this.routeURL = routeURL;
    }

}
