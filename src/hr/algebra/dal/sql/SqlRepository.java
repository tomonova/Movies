package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.Account;
import hr.algebra.model.Enums.AccountType;
import hr.algebra.model.Enums.DBStatus;
import hr.algebra.model.Enums.Occupation;
import hr.algebra.model.Genre;
import hr.algebra.model.Movie;
import hr.algebra.model.Person;
import hr.algebra.model.User;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

public class SqlRepository implements Repository {

    private final String path = System.getProperty("user.dir") + "\\src\\hr\\algebra\\resources\\";
    private final String propFilePath = path + "application.properties";
    private static final String SERVER_NAME = "SERVER_NAME";
    private static final String DATABASE_NAME = "DATABASE_NAME";
    private static final String PORT = "PORT";
    private static final String USER = "USER";
    private static final String PASSWORD = "PASSWORD";

    //Data base procedures
    private static final String CHECK_USER = "{ CALL checkUser (?,?,?) }";
    private static final String CHECK_IF_USER_EXISTS = "{ CALL checkIfUserExists (?,?) }";
    private static final String INSERT_ACCOUNT_AND_USER = "{ CALL insertAccountAndUser (?,?,?,?,?) }";
    private static final String GET_ACCOUNT = "{ CALL getAccount (?) }";
    private static final String CREATE_MOVIE = "{ CALL getAccount (?,?,?,?,?,?) }";
    private static final String INSERT_GENRES = "{ CALL insertGenres (?) }";
    private static final String GET_MOVIES = "{ CALL getMovies }";
    private static final String DELETE_DATA = "{ CALL DeleteData }";
    private static final String CHECK_DB_STATUS = "{ CALL CheckDbStatus (?) }";
    private static final String INSERT_DIRECTOR = "{ CALL InsertDirector (?) }";
    private static final String INSERT_ACTOR = "{ CALL InsertActor (?) }";
    private static final String GET_GENRE_ID = "{ CALL GetGenreID (?,?) }";
    private static final String INSERT_MOVIE = "{ CALL InsertMovie (?,?,?,?,?,?,?,?,?,?) }";
    private static final String UPDATE_MOVIE = "{ CALL UpdateMovie (?,?,?,?,?,?,?,?,?,?) }";
    private static final String GET_PERSON_ID = "{ CALL GetPersonID (?,?,?) }";
    private static final String INSERT_MP_CONNECTION = "{ CALL InsertMoviePerson (?,?) }";
    private static final String CHANGE_DBSTATUS = "{ CALL ChangeDBStatus (?) }";
    private static final String GET_MOVIE = "{ CALL GetMovie (?) }";
    private static final String GET_MOVIE_PERSONS = "{ CALL GetMoviePersons (?,?) }";
    private static final String GET_GENRES = "{ CALL GetGenres}";
    private static final String DELETE_MOVIE = "{ CALL DeleteMovie (?)}";
    private static final String GET_FAVOURITE_MOVIES = "{ CALL GetFavouriteMovies (?)}";
    private static final String SAVE_FAVOURITE_MOVIES = "{ CALL SaveFavouriteMovies (?,?)}";
    private static final String DELETE_FAVOURITE_MOVIES = "{ CALL DeleteFavouriteMovies (?)}";
    private static final String DELETE_SELECTED_FAVOURITE_MOVIES = "{ CALL DeleteSelectedFavouriteMovies (?,?)}";
    private static final String DELETE_MOVIE_PERSON = "{ CALL DeleteMoviePerson (?)}";

//    Data base column names
    private static final String ID_ACCOUNT = "IDAccount";
    private static final String NAME = "Name";
    private static final String SURNAME = "Surname";
    private static final String EMAIL = "Email";
    private static final String JOIN_DATE = "JoinDate";
    private static final String ACCOUNT_TYPE = "AccountType";
    private static final String ID_MOVIE = "IDMovie";
    private static final String TITLE = "Title";
    private static final String PUBLISHED_DATE = "PubDate";
    private static final String RELEASE_YEAR = "ReleaseYear";
    private static final String DESCRIPTION = "Description";
    private static final String ORIGINAL_TITLE = "OriginalTitle";
    private static final String GENRE = "Genre";
    private static final String PICTURE_PATH = "Picture";
    private static final String RATING = "Rating";
    private static final String LENGTH = "Length";
    private static final String ID_PERSON = "IDPerson";
    private static final String ID_GENRE = "IDGenre";
    private static final String PERSON_NAME = "Name";
    private static final String PERSON_OCCUPATION = "OccupationID";
    private static final String GENRE_NAME = "Name";

    @Override
    public boolean checkUser(User user) throws Exception {
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(propFilePath));
        DataSource ds = DataSourceSingleton.getInstance(
                appProps.getProperty(SERVER_NAME),
                appProps.getProperty(DATABASE_NAME),
                Integer.parseInt(appProps.getProperty(PORT)),
                appProps.getProperty(USER),
                appProps.getProperty(PASSWORD));
        try (Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(CHECK_USER)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.registerOutParameter(3, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(3) == 1;
        }
    }

    @Override
    public boolean checkIfUserExists(User user) throws Exception {
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(propFilePath));
        DataSource ds = DataSourceSingleton.getInstance(
                appProps.getProperty(SERVER_NAME),
                appProps.getProperty(DATABASE_NAME),
                Integer.parseInt(appProps.getProperty(PORT)),
                appProps.getProperty(USER),
                appProps.getProperty(PASSWORD));
        try (Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(CHECK_IF_USER_EXISTS)) {
            stmt.setString(1, user.getUsername());
            stmt.registerOutParameter(2, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(2) == 1;
        }
    }

    @Override
    public void CreateAccountAndUser(Account account, User user) throws Exception {
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(propFilePath));
        DataSource ds = DataSourceSingleton.getInstance(
                appProps.getProperty(SERVER_NAME),
                appProps.getProperty(DATABASE_NAME),
                Integer.parseInt(appProps.getProperty(PORT)),
                appProps.getProperty(USER),
                appProps.getProperty(PASSWORD));
        try (Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(INSERT_ACCOUNT_AND_USER)) {
            stmt.setString(1, account.getFirstName());
            stmt.setString(2, account.getLastName());
            stmt.setString(3, account.getEmail());
            stmt.setInt(4, account.getAccountType().getValue());
            stmt.setString(5, user.getPassword());
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Account> getAccount(String username) throws Exception {
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(propFilePath));
        DataSource ds = DataSourceSingleton.getInstance(
                appProps.getProperty(SERVER_NAME),
                appProps.getProperty(DATABASE_NAME),
                Integer.parseInt(appProps.getProperty(PORT)),
                appProps.getProperty(USER),
                appProps.getProperty(PASSWORD));
        try (Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(GET_ACCOUNT)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(new Account(
                            rs.getInt(ID_ACCOUNT),
                            rs.getString(NAME),
                            rs.getString(SURNAME),
                            rs.getString(EMAIL),
                            AccountType.fromInt((rs.getInt(ACCOUNT_TYPE))),
                            LocalDate.parse(rs.getString(JOIN_DATE), Account.DATE_FORMATTER)));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Movie> selectMovies() throws Exception {
        List<Movie> movies = new ArrayList<>();
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(propFilePath));
        DataSource ds = DataSourceSingleton.getInstance(
                appProps.getProperty(SERVER_NAME),
                appProps.getProperty(DATABASE_NAME),
                Integer.parseInt(appProps.getProperty(PORT)),
                appProps.getProperty(USER),
                appProps.getProperty(PASSWORD));
        try (Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(GET_MOVIES);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt(ID_MOVIE),
                        rs.getString(TITLE),
                        LocalDate.parse(rs.getString(PUBLISHED_DATE), Movie.DATE_FORMATTER),
                        Integer.parseInt(rs.getString(RELEASE_YEAR)),
                        rs.getString(DESCRIPTION),
                        rs.getString(ORIGINAL_TITLE),
                        rs.getString(PICTURE_PATH),
                        new Genre(rs.getString(GENRE)),
                        Integer.parseInt(rs.getString(RATING)),
                        Integer.parseInt(rs.getString(LENGTH))));
            }
            return movies;
        }
    }

    @Override
    public Optional<Movie> getMovie(int selectedMovieId) throws Exception {
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(propFilePath));
        DataSource ds = DataSourceSingleton.getInstance(
                appProps.getProperty(SERVER_NAME),
                appProps.getProperty(DATABASE_NAME),
                Integer.parseInt(appProps.getProperty(PORT)),
                appProps.getProperty(USER),
                appProps.getProperty(PASSWORD));
        try (Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(GET_MOVIE)) {
            stmt.setInt(1, selectedMovieId);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(new Movie(
                            rs.getInt(ID_MOVIE),
                            rs.getString(TITLE),
                            LocalDate.parse(rs.getString(PUBLISHED_DATE), Movie.DATE_FORMATTER),
                            rs.getInt(RELEASE_YEAR),
                            rs.getString(DESCRIPTION),
                            rs.getString(ORIGINAL_TITLE),
                            rs.getString(PICTURE_PATH),
                            getMoviePersons(selectedMovieId, Occupation.REDATELJ),
                            getMoviePersons(selectedMovieId, Occupation.GLUMAC),
                            new Genre(rs.getString(GENRE)),
                            rs.getInt(RATING),
                            rs.getInt(LENGTH)));
                }
            }
        }
        return Optional.empty();
    }

    public List<Person> getMoviePersons(int movieID, Occupation occupation) throws FileNotFoundException, IOException, SQLException {
        List<Person> persons = new ArrayList<>();
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(propFilePath));
        DataSource ds = DataSourceSingleton.getInstance(
                appProps.getProperty(SERVER_NAME),
                appProps.getProperty(DATABASE_NAME),
                Integer.parseInt(appProps.getProperty(PORT)),
                appProps.getProperty(USER),
                appProps.getProperty(PASSWORD));
        try (Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(GET_MOVIE_PERSONS)) {
            stmt.setInt(1, movieID);
            stmt.setInt(2, occupation.getValue());
            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    persons.add(new Person(
                            rs.getInt(ID_PERSON),
                            rs.getString(PERSON_NAME),
                            Occupation.fromInt(rs.getInt(PERSON_OCCUPATION))));
                }
                return persons;
            }
        }
    }

    @Override
    public void createMovies(List<Movie> movies) throws Exception {
        movies.forEach(m -> {
            try {
                int idMovie = insertMovie(m);
                connectPersonsAndMovie(m, idMovie);
            } catch (IOException ex) {
                Logger.getLogger(SqlRepository.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(SqlRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private int insertMovie(Movie movie) throws FileNotFoundException, IOException, SQLException {
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(propFilePath));
        DataSource ds = DataSourceSingleton.getInstance(
                appProps.getProperty(SERVER_NAME),
                appProps.getProperty(DATABASE_NAME),
                Integer.parseInt(appProps.getProperty(PORT)),
                appProps.getProperty(USER),
                appProps.getProperty(PASSWORD));
        try (Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(INSERT_MOVIE)) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getPubDate().toString());
            stmt.setInt(3, movie.getReleaseYear());
            stmt.setString(4, movie.getDescription());
            stmt.setString(5, movie.getOriginalTitle());
            stmt.setInt(6, GetGenreID(movie.getGenre()));
            stmt.setString(7, movie.getPicturePath());
            stmt.setInt(8, movie.getRating());
            stmt.setInt(9, movie.getLength());
            stmt.registerOutParameter(10, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(10);
        }
    }

    private int GetGenreID(Genre genre) throws FileNotFoundException, IOException, SQLException {
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(propFilePath));
        DataSource ds = DataSourceSingleton.getInstance(
                appProps.getProperty(SERVER_NAME),
                appProps.getProperty(DATABASE_NAME),
                Integer.parseInt(appProps.getProperty(PORT)),
                appProps.getProperty(USER),
                appProps.getProperty(PASSWORD));
        try (Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(GET_GENRE_ID)) {
            stmt.setString(1, genre.getGenre());
            stmt.registerOutParameter(2, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(2);
        }
    }

    private void connectPersonsAndMovie(Movie movie, int idMovie) throws SQLException {
        List<Integer> actorsInt = getPersonID(movie.getGlumci(), Occupation.GLUMAC);
        List<Integer> directorsInt = getPersonID(movie.getRedatelj(), Occupation.REDATELJ);
        List<Integer> personsInt = actorsInt;
        personsInt.addAll(directorsInt);
        createMPConnections(personsInt, idMovie);
    }

    private List<Integer> getPersonID(List<Person> persons, Occupation occupation) throws SQLException {
        List<Integer> personID = new ArrayList<>();
        Properties appProps = new Properties();
        DataSource ds = DataSourceSingleton.getInstance(
                appProps.getProperty(SERVER_NAME),
                appProps.getProperty(DATABASE_NAME),
                //                Integer.parseInt(appProps.getProperty(PORT)),
                appProps.getProperty(USER),
                appProps.getProperty(PASSWORD));
        try (Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(GET_PERSON_ID)) {
            for (Person person : persons) {
                stmt.setString(1, person.getName());
                stmt.setInt(2, occupation.getValue());
                stmt.registerOutParameter(3, Types.INTEGER);
                stmt.executeUpdate();
                personID.add(stmt.getInt(3));
            }
        }
        return personID;
    }

    private void createMPConnections(List<Integer> personsInt, int idMovie) throws SQLException {
        Properties appProps = new Properties();
        DataSource ds = DataSourceSingleton.getInstance(
                appProps.getProperty(SERVER_NAME),
                appProps.getProperty(DATABASE_NAME),
                // Integer.parseInt(appProps.getProperty(PORT)),
                appProps.getProperty(USER),
                appProps.getProperty(PASSWORD));
        try (Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(INSERT_MP_CONNECTION)) {
            for (Integer personID : personsInt) {
                stmt.setInt(1, idMovie);
                stmt.setInt(2, personID);
                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void insertGenres(List<Movie> movies) throws Exception {
        HashSet<Genre> zanrovi = new HashSet<>();
        movies.forEach(m -> {
            zanrovi.add(m.getGenre());
        });
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(propFilePath));
        DataSource ds = DataSourceSingleton.getInstance(
                appProps.getProperty(SERVER_NAME),
                appProps.getProperty(DATABASE_NAME),
                Integer.parseInt(appProps.getProperty(PORT)),
                appProps.getProperty(USER),
                appProps.getProperty(PASSWORD));
        try (Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(INSERT_GENRES)) {
            for (Genre genre : zanrovi) {
                stmt.setString(1, genre.getGenre().trim());
                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void insertPersons(List<Movie> movies) throws Exception {
        HashSet<Person> directorSet = new HashSet<>();
        HashSet<Person> actorSet = new HashSet<>();
//        Dodaj redatelje
        for (Movie movie : movies) {
            List<Person> directors = new ArrayList<>();
            directors = movie.getRedatelj();
            directors.forEach(directorSet::add);
        }
//        Dodaj glumce
        for (Movie movie : movies) {
            List<Person> actors = new ArrayList<>();
            actors = movie.getGlumci();
            actors.forEach(actorSet::add);
        }
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(propFilePath));
        DataSource ds = DataSourceSingleton.getInstance(
                appProps.getProperty(SERVER_NAME),
                appProps.getProperty(DATABASE_NAME),
                Integer.parseInt(appProps.getProperty(PORT)),
                appProps.getProperty(USER),
                appProps.getProperty(PASSWORD));
        try (Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(INSERT_DIRECTOR)) {
            for (Person person : directorSet) {
                stmt.setString(1, person.getName().trim());
                stmt.executeUpdate();
            }
        }
        try (Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(INSERT_ACTOR)) {
            for (Person person : actorSet) {
                stmt.setString(1, person.getName().trim());
                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void deleteData() throws Exception {
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(propFilePath));
        DataSource ds = DataSourceSingleton.getInstance(
                appProps.getProperty(SERVER_NAME),
                appProps.getProperty(DATABASE_NAME),
                Integer.parseInt(appProps.getProperty(PORT)),
                appProps.getProperty(USER),
                appProps.getProperty(PASSWORD));
        try (Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_DATA)) {
            stmt.executeUpdate();
        }
    }

    @Override
    public boolean checkDBStatus() throws Exception {
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(propFilePath));
        DataSource ds = DataSourceSingleton.getInstance(
                appProps.getProperty(SERVER_NAME),
                appProps.getProperty(DATABASE_NAME),
                Integer.parseInt(appProps.getProperty(PORT)),
                appProps.getProperty(USER),
                appProps.getProperty(PASSWORD));
        try (Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(CHECK_DB_STATUS)) {
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(1) == 1;
        }
    }

    @Override
    public void setDBstatus(DBStatus dbStatus) throws Exception {
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(propFilePath));
        DataSource ds = DataSourceSingleton.getInstance(
                appProps.getProperty(SERVER_NAME),
                appProps.getProperty(DATABASE_NAME),
                Integer.parseInt(appProps.getProperty(PORT)),
                appProps.getProperty(USER),
                appProps.getProperty(PASSWORD));
        try (Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(CHANGE_DBSTATUS)) {
            stmt.setInt(1, dbStatus.getValue());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Genre> getGenres() throws Exception {
        List<Genre> genres = new ArrayList<>();
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(propFilePath));
        DataSource ds = DataSourceSingleton.getInstance(
                appProps.getProperty(SERVER_NAME),
                appProps.getProperty(DATABASE_NAME),
                Integer.parseInt(appProps.getProperty(PORT)),
                appProps.getProperty(USER),
                appProps.getProperty(PASSWORD));
        try (Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(GET_GENRES);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                genres.add(new Genre(
                        rs.getInt(ID_GENRE),
                        rs.getString(GENRE_NAME)));
            }
            return genres;
        }
    }

    @Override
    public void deleteMovie(int idMovie) throws Exception {
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(propFilePath));
        DataSource ds = DataSourceSingleton.getInstance(
                appProps.getProperty(SERVER_NAME),
                appProps.getProperty(DATABASE_NAME),
                Integer.parseInt(appProps.getProperty(PORT)),
                appProps.getProperty(USER),
                appProps.getProperty(PASSWORD));
        try (Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_MOVIE)) {
            stmt.setInt(1, idMovie);
            stmt.executeUpdate();
        }
    }

    @Override
    public Set<Movie> GetMovies(User user) throws Exception {
        Set<Movie> movies = new TreeSet<>();
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(propFilePath));
        DataSource ds = DataSourceSingleton.getInstance(
                appProps.getProperty(SERVER_NAME),
                appProps.getProperty(DATABASE_NAME),
                Integer.parseInt(appProps.getProperty(PORT)),
                appProps.getProperty(USER),
                appProps.getProperty(PASSWORD));
        try (Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(GET_FAVOURITE_MOVIES)) {
            stmt.setString(1, user.getUsername());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt(ID_MOVIE),
                        rs.getString(TITLE),
                        LocalDate.parse(rs.getString(PUBLISHED_DATE), Movie.DATE_FORMATTER),
                        Integer.parseInt(rs.getString(RELEASE_YEAR)),
                        rs.getString(DESCRIPTION),
                        rs.getString(ORIGINAL_TITLE),
                        rs.getString(PICTURE_PATH),
                        new Genre(rs.getString(GENRE)),
                        Integer.parseInt(rs.getString(RATING)),
                        Integer.parseInt(rs.getString(LENGTH))));
            }
            return movies;
        }
    }

    @Override
    public Set<Movie> GetMovies() throws Exception {
        Set<Movie> movies = new TreeSet<>();
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(propFilePath));
        DataSource ds = DataSourceSingleton.getInstance(
                appProps.getProperty(SERVER_NAME),
                appProps.getProperty(DATABASE_NAME),
                Integer.parseInt(appProps.getProperty(PORT)),
                appProps.getProperty(USER),
                appProps.getProperty(PASSWORD));
        try (Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(GET_MOVIES);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt(ID_MOVIE),
                        rs.getString(TITLE),
                        LocalDate.parse(rs.getString(PUBLISHED_DATE), Movie.DATE_FORMATTER),
                        Integer.parseInt(rs.getString(RELEASE_YEAR)),
                        rs.getString(DESCRIPTION),
                        rs.getString(ORIGINAL_TITLE),
                        rs.getString(PICTURE_PATH),
                        new Genre(rs.getString(GENRE)),
                        Integer.parseInt(rs.getString(RATING)),
                        Integer.parseInt(rs.getString(LENGTH))));
            }
            return movies;
        }
    }

    @Override
    public void SaveFavorites(Set<Movie> favouriteMovies,User user) throws Exception {
        DeleteFavouriteMovies(user);
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(propFilePath));
        DataSource ds = DataSourceSingleton.getInstance(
                appProps.getProperty(SERVER_NAME),
                appProps.getProperty(DATABASE_NAME),
                Integer.parseInt(appProps.getProperty(PORT)),
                appProps.getProperty(USER),
                appProps.getProperty(PASSWORD));
        try (Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(SAVE_FAVOURITE_MOVIES)) {
            if (favouriteMovies.isEmpty()) {
                return;
            }
            for (Movie movie : favouriteMovies) {
                stmt.setInt(1,movie.getIdMovie());
                stmt.setString(2,user.getUsername());
                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void DeleteFavouriteMovies(User user) throws FileNotFoundException, FileNotFoundException, IOException, SQLException{
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(propFilePath));
        DataSource ds = DataSourceSingleton.getInstance(
                appProps.getProperty(SERVER_NAME),
                appProps.getProperty(DATABASE_NAME),
                Integer.parseInt(appProps.getProperty(PORT)),
                appProps.getProperty(USER),
                appProps.getProperty(PASSWORD));
        try (Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_FAVOURITE_MOVIES)) {
            stmt.setString(1, user.getUsername());
            stmt.executeUpdate();
        }
    }
    
    @Override
    public void DeleteFavouriteMovies(User user,Set<Movie> movies) throws Exception{
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(propFilePath));
        DataSource ds = DataSourceSingleton.getInstance(
                appProps.getProperty(SERVER_NAME),
                appProps.getProperty(DATABASE_NAME),
                Integer.parseInt(appProps.getProperty(PORT)),
                appProps.getProperty(USER),
                appProps.getProperty(PASSWORD));
        try (Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_SELECTED_FAVOURITE_MOVIES)) {
            for (Movie movie : movies) {
                stmt.setString(1,user.getUsername());
                stmt.setInt(2,movie.getIdMovie());
                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void createMovie(Movie movie) throws Exception{
        int movieID=insertMovie(movie);
        connectPersonsAndMovie(movie, movieID);
    }

    @Override
    public void updateArticle(Movie movie) throws Exception {
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(propFilePath));
        DataSource ds = DataSourceSingleton.getInstance(
                appProps.getProperty(SERVER_NAME),
                appProps.getProperty(DATABASE_NAME),
                Integer.parseInt(appProps.getProperty(PORT)),
                appProps.getProperty(USER),
                appProps.getProperty(PASSWORD));
        try (Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_MOVIE)) {
            stmt.setInt(1, movie.getIdMovie());
            stmt.setString(2, movie.getTitle());
            stmt.setString(3, movie.getPubDate().toString());
            stmt.setInt(4, movie.getReleaseYear());
            stmt.setString(5, movie.getDescription());
            stmt.setString(6, movie.getOriginalTitle());
            stmt.setInt(7, GetGenreID(movie.getGenre()));
            stmt.setString(8, movie.getPicturePath());
            stmt.setInt(9, movie.getRating());
            stmt.setInt(10, movie.getLength());
            stmt.executeUpdate();
        }
        deleteExistingPersonMovieConnection(movie);
        connectPersonsAndMovie(movie, movie.getIdMovie());
    }

    private void deleteExistingPersonMovieConnection(Movie movie) throws Exception{
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(propFilePath));
        DataSource ds = DataSourceSingleton.getInstance(
                appProps.getProperty(SERVER_NAME),
                appProps.getProperty(DATABASE_NAME),
                Integer.parseInt(appProps.getProperty(PORT)),
                appProps.getProperty(USER),
                appProps.getProperty(PASSWORD));
        try (Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_MOVIE_PERSON)) {
            stmt.setInt(1, movie.getIdMovie());
            stmt.executeUpdate();
        }
    }
}
