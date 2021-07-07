/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.parsers;

import hr.algebra.model.Movie;
import hr.algebra.utils.FileUtils;
import hr.algebra.factory.ParserFactory;
import hr.algebra.factory.UrlConnectionFactory;
import hr.algebra.model.Enums.Occupation;
import hr.algebra.model.Enums.TagType;
import hr.algebra.model.Genre;
import hr.algebra.model.Person;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author TomoNova
 */
public class MovieParser {

    private static final String RSS_URL = "https://www.blitz-cinestar.hr/rss.aspx?najava=2";
    private static final String ATTRIBUTE_URL = "url";
    private static final String EXT = ".jpg";
    private static final String DIR = "assets";
    private static final String DELIMITER = ",";

    public MovieParser() {
    }

    public static List<Movie> parse() throws IOException, XMLStreamException {
        List<Movie> movies = new ArrayList<>();
        HttpURLConnection con = UrlConnectionFactory.getHttpUrlConnection(RSS_URL);
        try (InputStream is = con.getInputStream()) {
            XMLEventReader reader = ParserFactory.createStaxParser(is);

            Optional<TagType> tagType = Optional.empty();
            Movie movie = null;
            StartElement startElement = null;
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        tagType = TagType.from(qName);
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        if (tagType.isPresent()) {
                            Characters characters = event.asCharacters();
                            String data = characters.getData().trim();
                            switch (tagType.get()) {
                                case ITEM:
                                    movie = new Movie();
                                    movies.add(movie);
                                    break;
                                case TITLE:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setTitle(data);
                                    }
                                    break;
                                case PUB_DATE:
                                    if (movie != null && !data.isEmpty()) {
                                        LocalDate publishedDate = LocalDate.parse(data, DateTimeFormatter.ISO_LOCAL_DATE);
                                        movie.setPubDate(publishedDate);
                                    }
                                    break;
                                case DESCRIPTION:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setDescription(data);
                                    }
                                    break;
                                case ORIGNAZIV:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setOriginalTitle(data);
                                    }
                                    break;
                                case REDATELJ:
                                    if (movie != null && !data.isEmpty()) {
                                        List<Person> persons = GetOccupations(data, Occupation.REDATELJ);
                                        movie.setRedatelj(persons);
                                    }
                                    break;
                                case GLUMCI:
                                    if (movie != null && !data.isEmpty()) {
                                        List<Person> persons = GetOccupations(data, Occupation.GLUMAC);
                                        movie.setGlumci(persons);
                                    }
                                    break;
                                case TRAJANJE:
                                    if (movie != null && !data.isEmpty() && TestStringToInt(data)) {
                                        movie.setLength(Integer.parseInt(data));
                                    }
                                    break;
                                case GODINA:
                                    if (movie != null && !data.isEmpty() && TestStringToInt(data)) {
                                        movie.setReleaseYear(Integer.parseInt(data));
                                    }
                                    break;
                                case ZANR:
                                    if (movie != null && !data.isEmpty()) {
                                        Genre genre = new Genre(data);
                                        movie.setGenre(genre);
                                    }
                                    break;
                                case PLAKAT:
                                    if (movie != null && startElement != null && movie.getPicturePath() == null) {
                                        Attribute urlAttribute = startElement.getAttributeByName(new QName(ATTRIBUTE_URL));
                                        if (urlAttribute != null) {
                                            handlePicture(movie, urlAttribute.getValue());
                                        }
                                    }
                                    break;
                                case RATING:
                                    if (movie != null && !data.isEmpty() && TestStringToInt(data)) {
                                        movie.setRating(Integer.parseInt(data));
                                    }
                                    break;
                            }
                        }
                        break;
                }
            }
        }
        return movies;

    }

    private static List<Person> GetOccupations(String data, Occupation occupation) {
        List<Person> persons = new ArrayList<>();
        String[] personsArray = data.split(DELIMITER);
        for (String item : personsArray) {
            Person person = new Person(item, occupation);
            persons.add(person);
        }
        return persons;
    }

    private static void handlePicture(Movie movie, String pictureUrl) {

        try {
            String ext = pictureUrl.substring(pictureUrl.lastIndexOf("."));
            if (ext.length() > 4) {
                ext = EXT;
            }
            String pictureName = UUID.randomUUID() + ext;
            String localPicturePath = DIR + File.separator + pictureName;

            FileUtils.copyFromUrl(pictureUrl, localPicturePath);
            movie.setPicturePath(localPicturePath);

        } catch (IOException ex) {
            Logger.getLogger(MovieParser.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static boolean TestStringToInt(String data) {
        try {
            Integer.parseInt(data);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
