/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import hr.algebra.model.Account;
import hr.algebra.model.User;

/**
 *
 * @author TomoNova
 */
public interface Repository {

    public boolean checkUser(User user) throws Exception;

    public boolean checkIfUserExists(User user) throws Exception;

    public void CreateAccountAndUser(Account account, User user) throws Exception;
    
}
