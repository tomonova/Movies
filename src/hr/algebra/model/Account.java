/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import hr.algebra.model.Enums.AccountType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author TomoNova
 */
public class Account {

    private int idAccount;
    private String firstName;
    private String lastName;
    private String email;
    private AccountType accountType;
    private LocalDate joinDate;
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    public Account(String firstName, String lastName, String email, AccountType accountType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.accountType = accountType;
    }

    public Account(int idAccount, String firstName, String lastName, String email, AccountType accountType, LocalDate joinDate) {
        this.idAccount = idAccount;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.accountType = accountType;
        this.joinDate = joinDate;
    }
    

    public Account() {
    }

    public int getIdAccount() {
        return idAccount;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        return firstName + lastName;
    }

}
