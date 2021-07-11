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
public enum Occupation {
    REDATELJ(0),
    GLUMAC(1),
    PRODUCENT(2),
    SCENARIST(3);
    private final int value;
    
    private Occupation(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
