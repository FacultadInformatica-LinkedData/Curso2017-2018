package Entities;

public class Activity {

	String title;
	String schedule;
	String dateStart;
	String dateEnd;
	String url;
	
	public Activity(String title, String schedule, String dateStart, String dateEnd, String url) {
		super();
		this.title = title;
		this.schedule = schedule;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.url = url;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public String getDateStart() {
		return dateStart;
	}
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	public String getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
