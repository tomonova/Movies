/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 *
 * @author TomoNova
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonArchive {
    @XmlElementWrapper
    @XmlElement(name = "person")
    private List<Person> persons;

    public PersonArchive() {
    }

    public PersonArchive(List<Person> persons) {
        this.persons = persons;
    }

    public List<Person> getPersons() {
        return persons;
    }
}
