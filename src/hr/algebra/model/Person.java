/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import hr.algebra.model.Enums.Occupation;
import java.time.LocalDate;

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
        this.Name = Name;
        this.occupation = occupation;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
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
        return Name+" - "+occupation;
    }
    
    
}
