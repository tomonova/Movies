/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import hr.algebra.model.Enums.Occupation;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author TomoNova
 */
public class Person {
    private int idPerson;
    private String Name;
    private Occupation occupation;

    public Person() {
    }

    public Person(String Name, Occupation occupation) {
        this.Name = Name.trim();
        this.occupation = occupation;
    }

    public Person(int idPerson, String Name, Occupation occupation) {
        this.idPerson = idPerson;
        this.Name = Name.trim();
        this.occupation = occupation;
    }
    

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name.trim();
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public int getIdPerson() {
        return idPerson;
    }

    @Override
    public String toString() {
        return Name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.Name);
        hash = 97 * hash + Objects.hashCode(this.occupation);
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
        final Person other = (Person) obj;
        if (!Objects.equals(this.Name, other.Name)) {
            return false;
        }
        if (this.occupation != other.occupation) {
            return false;
        }
        return true;
    }
    
    
}
