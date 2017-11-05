package MainPackage;

public class User {

	private int userID;
	private String firstName;
	private String lastName;
	
	public User(int id, String fN, String lN) {
		userID = id;
		firstName = fN;
		lastName = lN;
	}
	
	public int getUserID() {return userID;}
	
	public String getFirstName() {return firstName;}
	
	public String getLastName() {return lastName;}
}
