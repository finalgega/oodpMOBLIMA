package cz2002.moblima.entities;

public class User {

	private int userID;
	private String firstName;
	private String lastName;
    private String password;

    public User(int id, String fN, String lN, String password) {
        userID = id;
        firstName = fN;
		lastName = lN;
        this.password = password;

	}
	
	public int getUserID() {return userID;}
	
	public String getFirstName() {return firstName;}
	
	public String getLastName() {return lastName;}

    public String getPassword() {
        return password;
    }
}
