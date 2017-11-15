package MainPackage;

public class User {

	private int userID;
	private String passWord;
	private String firstName;
	private String lastName;
	
	public User(int id, String fN, String lN, String pW) {
		userID = id;
		firstName = fN;
		lastName = lN;
		passWord = pW;
	}
	
	public int getUserID() {return userID;}
	
	public String getFirstName() {return firstName;}
	
	public String getLastName() {return lastName;}
	
	public String getPassWord() {return passWord;}
}
