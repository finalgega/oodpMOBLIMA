package cz2002.moblima.entities;

import java.util.ArrayList;

public class Cineplexes {
	
	private int cineplexId;
    private ArrayList<Cinema> cinemaArrayList;

    public Cineplexes(int cinplxId) {
        this(3, cinplxId);
    }

    public Cineplexes(int numOfCinemas, int cinplxId) {
        this(numOfCinemas, cinplxId, 20);
    }

    public Cineplexes(int numOfCinemas, int cinplxId, int numOfSeatsInCinema) {
    	cineplexId = cinplxId;
    	cinemaArrayList = new ArrayList<Cinema>();
        for (int i = 0; i < numOfCinemas; i++) {
            this.cinemaArrayList.add(new Cinema(numOfSeatsInCinema, (cineplexId+1)*10+(i+1), cineplexId));
        }
    }
}
