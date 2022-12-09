/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author higor
 */
public class Session implements Serializable {
    final private int id;
    final private Room sala;
    final private Movie filme;
    final private boolean finalizada;
    final private Date data;

    public Session(int id, Room sala, Movie filme, boolean finalizada, Date data) {
        this.id = id;
        this.sala = sala;
        this.filme = filme;
        this.finalizada = finalizada;
        this.data = data;
    }
    
    public int getId() {
        return id;
    }

    public Room getSala() {
        return sala;
    }

    public Movie getFilme() {
        return filme;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public Date getData() {
        return data;
    }
    
    @Override
    public String toString() {
        return "Sala "+sala.getNumSala()+" - "+data.toString();
    }
}
