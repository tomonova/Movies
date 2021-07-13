/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author TomoNova
 */
public class PersonListBoxModel extends AbstractListModel implements ComboBoxModel {
    
    private List<Person> persons;
    private Person selection = null;

    public PersonListBoxModel(List<Person> persons) {
        this.persons = persons;
    }

    public PersonListBoxModel() {
    }
    
    
    @Override
    public int getSize() {
        return persons.size();
    }

    @Override
    public Object getElementAt(int index) {
        return persons.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selection = (Person)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }
    
}
