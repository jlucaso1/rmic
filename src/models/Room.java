/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;

/**
 *
 * @author higor
 */
public class Room implements Serializable {
    final private int numSala;

    public Room(int numSala) {
        this.numSala = numSala;
    }

    public int getNumSala() {
        return numSala;
    }

    @Override
    public String toString() {
        return "Sala " + numSala;
    }
    
    
}
