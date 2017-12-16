package Entities;

public class Library {
	
	String name;
	String schedule;
	String address;
	String telephone;
	String email;
	
	public Library(String name, String schedule, String address, String telephone, String email) {
		this.name=name;
		this.schedule=schedule;
		this.address=address;
		this.telephone=telephone;
		this.email=email;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
