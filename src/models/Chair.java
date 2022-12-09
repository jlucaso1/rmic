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
    final private String num;
    final private Room sala;

    public Chair(int id, String num, Room sala) {
        this.id = id;
        this.num = num;
        this.sala = sala;
    }

    public int getId() {
        return id;
    }

    public String getNum() {
        return num;
    }

    public Room getSala() {
        return sala;
    }

}
