package cz2002.moblima.entities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class User {


    public enum USER_AGE_CATEGORY {
        CHILD,
        ADULT,
        SENIOR_CITIZEN
    }

	private int userID;
	private String firstName;
	private String lastName;
    private String username;

    private String password;
    private Date dateOfBirth;
    private USER_AGE_CATEGORY categoryOfUser;

    public User(int id, String firstName, String lastName, String password) {
        this.userID = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getUserID() {return userID;}

    public String getUsername() {
        return username;
    }

    public String getFirstName() {return firstName;}
	
	public String getLastName() {return lastName;}


    public String getPassword() {
        return password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public USER_AGE_CATEGORY getCategoryOfUser() {
        return categoryOfUser;
    }

    private static void testCalculateAge() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your data of birth in the following format [DD-MM-YYYY] : ");
        String dateOfBirth = sc.nextLine();
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(dateFormat.parse(dateOfBirth));
            System.out.println("YEAR IN CALENDAR OBJ : " + calendar.get(Calendar.YEAR));
            //  Start of Age Calculation
            LocalDate birthday = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            LocalDate now = LocalDate.now();

            //  Use Period
            Period period = Period.between(birthday, now);
            System.out.println("Days after Birthday : " + period.getDays());
            System.out.println("Months after Birthday : " + period.getMonths());
            System.out.println("Age : " + period.getYears());
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

}
