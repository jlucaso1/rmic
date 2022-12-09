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
public class Chair implements Serializable {
    final private int id;
    final private int numCadeira;
    final private Room sala;

    public Chair(int id, int numCadeira, Room sala) {
        this.id = id;
        this.numCadeira = numCadeira;
        this.sala = sala;
    }
    
    public int getId() {
        return id;
    }

    public int getNumCadeira() {
        return numCadeira;
    }

    public Room getSala() {
        return sala;
    }
    
    
    
}
