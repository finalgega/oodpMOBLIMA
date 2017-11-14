package cz2002.moblima.controllers;

import cz2002.moblima.entities.Movie;
import cz2002.moblima.utilities.FileIOController;

import java.io.File;
import java.util.ArrayList;

public class MovieController {
    private static File movieFile = new File("movies.txt");

    private static MovieController ourInstance = new MovieController();

    public static MovieController getInstance() {
        return ourInstance;
    }

    private MovieController() {
    }

    public ArrayList<Movie> getListOfMovies() {
        ArrayList<String> movies = new ArrayList<String>();
        ArrayList<Movie> movieArrayList = new ArrayList<Movie>();
        FileIOController.readFile(movies, movieFile);
        for (int i = 0; i < movies.size(); i += 13) {
            Movie movie = new Movie(movies.get(i).substring(7), movies.get(i + 1), movies.get(i + 2), movies.get(i + 3), movies.get(i + 4), movies.get(i + 5), movies.get(i + 6), movies.get(i + 7), movies.get(i + 8), movies.get(i + 9), movies.get(i + 10), movies.get(i + 11), movies.get(i + 12));
            movieArrayList.add(movie);
        }

        return movieArrayList;

    }
}
