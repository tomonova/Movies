/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model.Enums;

import java.util.Optional;

/**
 *
 * @author TomoNova
 */
public enum TagType {
    ITEM("item"),
    TITLE("title"),
    PUB_DATE("pubDate"),
    DESCRIPTION("description"),
    ORIGNAZIV("orignaziv"),
    REDATELJ("redatelj"),
    GLUMCI("glumci"),
    TRAJANJE("trajanje"),
    GODINA("godina"),
    ZANR("zanr"),
    PLAKAT("plakat"),
    RATING("rating"),
    VRSTA("vrsta"),
    LINK("link"),
    DATUMPRIKAZIVANJA("datumprikazivanja");

    private final String name;

    private TagType(String name) {
        this.name = name;
    }

    public static Optional<TagType> from(String name) {
        for (TagType value : values()) {
            if (value.name.equals(name)) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }
}
