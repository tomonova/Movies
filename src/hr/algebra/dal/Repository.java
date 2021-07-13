/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import hr.algebra.model.Account;
import hr.algebra.model.Enums.DBStatus;
import hr.algebra.model.Genre;
import hr.algebra.model.Movie;
import hr.algebra.model.User;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author TomoNova
 */
public interface Repository {

    public boolean checkUser(User user) throws Exception;
    public boolean checkIfUserExists(User user) throws Exception;
    public void CreateAccountAndUser(Account account, User user) throws Exception;
    public Optional<Account> getAccount(String username) throws Exception;
    public List<Movie> selectMovies() throws Exception;
    public void createMovies(List<Movie> movies)throws Exception;
    public void insertGenres(List<Movie> movies)throws Exception;
    public void insertPersons(List<Movie> movies)throws Exception;
    public void deleteData() throws Exception;
    public boolean checkDBStatus() throws Exception;
    public void setDBstatus(DBStatus dbStatus) throws Exception;
    public Optional<Movie> getMovie(int selectedMovieId) throws Exception;
    public List<Genre> getGenres() throws Exception;
    public void deleteMovie(int idMovie) throws Exception;
}
