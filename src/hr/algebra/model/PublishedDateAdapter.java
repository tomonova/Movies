/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author TomoNova
 */
public class PublishedDateAdapter extends XmlAdapter<String, LocalDate>{
    @Override
    public LocalDate unmarshal(String text) throws Exception {
        return LocalDate.parse(text, Movie.DATE_FORMATTER);
    }

    @Override
    public String marshal(LocalDate date) throws Exception {
        return date.format(Movie.DATE_FORMATTER);
    }
}
