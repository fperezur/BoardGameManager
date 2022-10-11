package m6_streams.models;

import java.util.Arrays;
import java.util.Objects;

public class BoardGame {

    private String name;
    private int numbersOfPlayers;
    private double rating;
    private Author author;
    private Genre[] genres;
    private Difficulty difficulty;

    public BoardGame(String name, int numbersOfPlayers, double rating, Author author, Genre[] genres, Difficulty difficulty) {
        this.name = name;
        this.numbersOfPlayers = numbersOfPlayers;
        this.rating = rating;
        this.author = author;
        this.genres = genres;
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumbersOfPlayers() {
        return numbersOfPlayers;
    }

    public void setNumbersOfPlayers(int numbersOfPlayers) {
        this.numbersOfPlayers = numbersOfPlayers;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre[] getGenres() {
        return genres;
    }

    public void setGenres(Genre[] genres) {
        this.genres = genres;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.author);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BoardGame other = (BoardGame) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.author, other.author);
    }

    
    
    @Override
    public String toString() {
        return "BoardGame{" + "name=" + name + ", numbersOfPlayers=" + numbersOfPlayers 
                + ", rating=" + rating + ", author=" + author + ", genres=" + Arrays.toString(genres)
                + ", difficulty=" + difficulty + '}';
    }
    
    
    
    
}
