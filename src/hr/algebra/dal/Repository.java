/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import hr.algebra.model.User;

/**
 *
 * @author TomoNova
 */
public interface Repository {

    public boolean checkUser(User user) throws Exception;
    
}
