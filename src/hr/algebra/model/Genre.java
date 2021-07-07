/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

/**
 *
 * @author TomoNova
 */
public class Genre {
    private String genre;
    private int idGenre;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getIdGenre() {
        return idGenre;
    }

    public Genre(String genre, int idGenre) {
        this.genre = genre;
        this.idGenre = idGenre;
    }

    public Genre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return genre;
    }
}
