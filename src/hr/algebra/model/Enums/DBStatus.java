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
public enum DBStatus {
    NOT_READY(0),
    READY(1);

    private final int value;

    private DBStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
