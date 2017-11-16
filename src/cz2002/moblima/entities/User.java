package cz2002.moblima.entities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class User {


    public enum USER_AGE_CATEGORY {
        CHILD,
        ADULT,
        SENIOR_CITIZEN
    }

    public User(int userID, String firstName, String lastName, String password, String dateOfBirth) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    private int userID;
    private String firstName;
	private String lastName;

    public User(int userID, String firstName, String lastName, String username, String password, String dateOfBirth) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    private String username;

    private String password;
    private String dateOfBirth;
    private USER_AGE_CATEGORY categoryOfUser;

    public User(int id, String firstName, String lastName, String password) {
        this.userID = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.username = "test";
        this.dateOfBirth = "16-11-1992";
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public USER_AGE_CATEGORY getCategoryOfUser() {
        int age = computeAge();
        if (age < 18) {
            this.categoryOfUser = USER_AGE_CATEGORY.CHILD;
        } else if (age > 65) {
            this.categoryOfUser = USER_AGE_CATEGORY.SENIOR_CITIZEN;
        } else {
            this.categoryOfUser = USER_AGE_CATEGORY.ADULT;
        }

        return this.categoryOfUser;
    }

    /**
     * Calculates the age of User by computing the difference between the given date of birth and current date
     *
     * @return Computed age
     */
    public int computeAge() {
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(dateFormat.parse(getDateOfBirth()));

            LocalDate birthday = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            LocalDate now = LocalDate.now();

            Period period = Period.between(birthday, now);
            return period.getYears();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
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
