/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.util.Date;
import java.sql.Time;

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
    final private Time hora;

    public Session(int id, Room sala, Movie filme, boolean finalizada, Date data, Time hora) {
        this.id = id;
        this.sala = sala;
        this.filme = filme;
        this.finalizada = finalizada;
        this.data = data;
        this.hora = hora;
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

    public Time getHora() {
        return hora;
    }

    @Override
    public String toString() {
        return "Sala " + sala.getNumSala() + " - " + data.toString();
    }
}
