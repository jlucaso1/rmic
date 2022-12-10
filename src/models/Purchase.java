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
public class Purchase implements Serializable {

    final private int id;
    final private User usuario;
    final private Ticket ingresso;
    final private Date data;

    public Purchase(int id, User usuario, Ticket ingresso, Date data) {
        this.id = id;
        this.usuario = usuario;
        this.ingresso = ingresso;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public User getUsuario() {
        return usuario;
    }

    public Ticket getIngresso() {
        return ingresso;
    }

    public Date getData() {
        return data;
    }

}
