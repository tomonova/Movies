/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author TomoNova
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Movie implements Comparable<Movie> {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_DATE;
    @XmlAttribute
    private int idMovie;
    @XmlElement(name = "title")
    private String title;
    @XmlJavaTypeAdapter(PublishedDateAdapter.class)
    @XmlElement(name = "publishedDate")
    private LocalDate pubDate;
    @XmlElement(name = "releaseYear")
    private int releaseYear;
    @XmlElement(name = "discription")
    private String description;
    @XmlElement(name = "originalTitle")
    private String originalTitle;
    @XmlElement(name = "picturePath")
    private String picturePath;
    @XmlElementWrapper(name="directors")
    @XmlElement(name = "director")
    private List<Person> redatelj;
    @XmlElementWrapper(name="actors")
    @XmlElement(name = "actor")
    private List<Person> glumci;
    @XmlElement(name = "genre")
    private Genre genre;
    @XmlElement(name = "rating")
    private int rating;
    @XmlElement(name = "length")
    private int length;

    public Movie() {
        this.glumci=new ArrayList<Person>();
        this.redatelj=new ArrayList<Person>();
    }

    public Movie(int idMovie, String title, LocalDate pubDate,
            int releaseYear, String description, String originalTitle,
            String picturePath, Genre genre, int rating, int length) {
        this.idMovie = idMovie;
        this.title = title;
        this.pubDate = pubDate;
        this.releaseYear = releaseYear;
        this.description = description;
        this.originalTitle = originalTitle;
        this.picturePath = picturePath;
        this.genre = genre;
        this.rating = rating;
        this.length = length;
    }

    public Movie(int idMovie, String title, LocalDate pubDate, int releaseYear, String description,
            String originalTitle, String picturePath, List<Person> redatelj,
            List<Person> glumci, Genre genre, int rating, int length) {
        this.idMovie = idMovie;
        this.title = title;
        this.pubDate = pubDate;
        this.releaseYear = releaseYear;
        this.description = description;
        this.originalTitle = originalTitle;
        this.picturePath = picturePath;
        this.redatelj = redatelj;
        this.glumci = glumci;
        this.genre = genre;
        this.rating = rating;
        this.length = length;
    }

    public Movie(String title, LocalDate pubDate, int releaseYear, String description,
            String originalTitle, String picturePath, List<Person> redatelj,
            List<Person> glumci, Genre genre, int rating, int length) {
        this.title = title;
        this.pubDate = pubDate;
        this.releaseYear = releaseYear;
        this.description = description;
        this.originalTitle = originalTitle;
        this.picturePath = picturePath;
        this.redatelj = redatelj;
        this.glumci = glumci;
        this.genre = genre;
        this.rating = rating;
        this.length = length;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getPubDate() {
        return pubDate;
    }

    public String getDescription() {
        return description;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getRating() {
        return rating;
    }

    public int getLength() {
        return length;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPubDate(LocalDate pubDate) {
        this.pubDate = pubDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<Person> getRedatelj() {
        return redatelj;
    }

    public void setRedatelj(List<Person> redatelj) {
        this.redatelj = redatelj;
    }

    public List<Person> getGlumci() {
        return glumci;
    }

    public void setGlumci(List<Person> glumci) {
        this.glumci = glumci;
    }

    @Override
    public String toString() {
        return idMovie + " - " + title;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.title);
        hash = 29 * hash + this.releaseYear;
        hash = 29 * hash + Objects.hashCode(this.genre);
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
        final Movie other = (Movie) obj;
        if (this.releaseYear != other.releaseYear) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.genre, other.genre)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Movie o) {
        return Integer.compare(this.idMovie, o.idMovie);
    }

}
