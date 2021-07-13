/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author TomoNova
 */
public class GenreComboBoxModel extends AbstractListModel implements ComboBoxModel {

    private List<Genre> genres;
    private Genre selection = null;

    public GenreComboBoxModel(List<Genre> genres) {
        this.genres = new ArrayList<>();
        this.genres.add(new Genre(0, "Choose genre"));
        for (Genre genre : genres) {
            this.genres.add(genre);
        }
    }

    @Override
    public int getSize() {
        return genres.size();
    }

    @Override
    public Object getElementAt(int index) {
        return genres.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selection = (Genre) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }

}
