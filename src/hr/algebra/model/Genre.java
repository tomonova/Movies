/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.util.Objects;

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

    public Genre(int idGenre,String genre) {
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.genre);
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
        final Genre other = (Genre) obj;
        if (!Objects.equals(this.genre, other.genre)) {
            return false;
        }
        return true;
    }
    
}
