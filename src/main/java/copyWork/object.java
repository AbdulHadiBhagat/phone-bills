package copyWork;

public class object {

	public String name;
	public String phone;
	public String address;
	public String email;
	public String path;
	
	public  object(
			
	String email,
    String phone) 
	{
		
		this.email = email; 
	    this.phone = phone;
	    
	    
  }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	};
  
  
}
