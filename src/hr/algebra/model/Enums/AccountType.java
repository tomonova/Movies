/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model.Enums;

/**
 *
 * @author TomoNova
 */
public enum AccountType {
    REGULAR(0),
    ADMIN(1);
    private final int value;

    private AccountType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
