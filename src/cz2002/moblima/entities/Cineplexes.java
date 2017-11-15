package cz2002.moblima.entities;

import java.util.ArrayList;

public class Cineplexes {

    private ArrayList<Cinema> cinemaArrayList;

    public Cineplexes() {
        this(3);
    }

    public Cineplexes(int numOfCinemas) {
        this(numOfCinemas, 20);
    }

    public Cineplexes(int numOfCinemas, int numOfSeatsInCinema) {
        for (int i = 0; i < numOfCinemas; i++) {
            this.cinemaArrayList.add(new Cinema(numOfSeatsInCinema));
        }
    }
}
