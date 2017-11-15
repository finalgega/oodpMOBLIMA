package cz2002.moblima.entities;

public class Movie {

    private String movieTitle;
    private String movieType;
    private String movieDirector;
    private String movieCast;
    private String movieSynopsis;
    private String movieStatus;
    private String movieShowtimes;
    private String movieClass;
    private String movieReviews;
    private String overallRating;
    private String showStart;
    private String showEnd;
    private String totalSales;

    public Movie(String movieTitle, String movieType, String movieDirector, String movieCast, String movieSynopsis, String movieStatus, String movieShowtimes, String movieClass, String movieReviews, String overallRating, String showStart, String showEnd, String totalSales) {
        this.movieTitle = movieTitle;
        this.movieType = movieType;
        this.movieDirector = movieDirector;
        this.movieCast = movieCast;
        this.movieSynopsis = movieSynopsis;
        this.movieStatus = movieStatus;
        this.movieShowtimes = movieShowtimes;
        this.movieClass = movieClass;
        this.movieReviews = movieReviews;
        this.overallRating = overallRating;
        this.showStart = showStart;
        this.showEnd = showEnd;
        this.totalSales = totalSales;
    }



    public Movie(String movieTitle, String movieType, String movieDirector, String movieCast, String movieSynopsis, String movieStatus, String movieShowtimes, String movieReviews, String overallRating, String showStart, String showEnd, String totalSales) {
        this.movieTitle = movieTitle;
        this.movieType = movieType;
        this.movieDirector = movieDirector;
        this.movieCast = movieCast;
        this.movieSynopsis = movieSynopsis;
        this.movieStatus = movieStatus;
        this.movieShowtimes = movieShowtimes;
        this.movieReviews = movieReviews;
        this.overallRating = overallRating;
        this.showStart = showStart;
        this.showEnd = showEnd;
        this.totalSales = totalSales;
    }

    public Movie(String movieTitle, String movieType, String movieDirector, String movieCast, String movieSynopsis, String movieStatus, String movieShowtimes, String movieReviews) {
        this.movieTitle = movieTitle;
        this.movieType = movieType;
        this.movieDirector = movieDirector;
        this.movieCast = movieCast;
        this.movieSynopsis = movieSynopsis;
        this.movieStatus = movieStatus;
        this.movieShowtimes = movieShowtimes;
        this.movieReviews = movieReviews;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getMovieType() {
        return movieType;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public String getMovieCast() {
        return movieCast;
    }

    public String getMovieSynopsis() {
        return movieSynopsis;
    }

    public String getMovieStatus() {
        return movieStatus;
    }

    public String getMovieShowtimes() {
        return movieShowtimes;
    }

    public String getMovieReviews() {
        return movieReviews;
    }
}
