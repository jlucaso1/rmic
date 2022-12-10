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
public class Ticket implements Serializable {

    final private int id;
    final private Session sessao;
    final private Chair poltrona;
    final private boolean disponivel;
    final private Purchase compra;

    public Ticket(int id, Session sessao, Chair poltrona, boolean disponivel, Purchase compra) {
        this.id = id;
        this.sessao = sessao;
        this.poltrona = poltrona;
        this.disponivel = disponivel;
        this.compra = compra;
    }

    public Purchase getCompra() {
        return compra;
    }

    public int getId() {
        return id;
    }

    public Session getSessao() {
        return sessao;
    }

    public Chair getPoltrona() {
        return poltrona;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    @Override
    public String toString() {
        return "Sala " + poltrona.getSala().getNumSala() + " - Poltrona " + poltrona.getNum();
    }

}
