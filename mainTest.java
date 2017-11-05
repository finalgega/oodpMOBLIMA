package MainPackage;

import java.util.ArrayList;

public class mainTest {

	public static void main(String[] args){
		ArrayList<User> uL = new ArrayList<User>();
		User u1 = new User(1, "Bob", "Bobby");
		User u2 = new User(2, "Tic", "Tac");
		uL.add(u1);
		uL.add(u2);
		//Users.addUsers(uL);
		uL = Users.readUserFile();
		User u;
		for(int i = 0; i<uL.size(); i++) {
			u = uL.get(i);
			System.out.println(String.valueOf(u.getUserID())+" "+u.getFirstName()+" "+u.getLastName());
		}
	}
}
