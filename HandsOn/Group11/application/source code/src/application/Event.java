package application;

public class Event {

	private String name;
	private String description;
	private String UriToLocation;
	
	public Event(String name, String description, String uriToLocation) {
		super();
		this.name = name;
		this.description = description;
		UriToLocation = uriToLocation;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getUriToLocation() {
		return UriToLocation;
	}
	
	
	
}
