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
    final private Chair cadeira;
    final private boolean disponivel;

    public Ticket(int id, Session sessao, Chair cadeira, boolean disponivel) {
        this.id = id;
        this.sessao = sessao;
        this.cadeira = cadeira;
        this.disponivel = disponivel;
    }

    
    public int getId() {
        return id;
    }

    public Session getSessao() {
        return sessao;
    }

    public Chair getCadeira() {
        return cadeira;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    @Override
    public String toString() {
        return "Sala "+cadeira.getSala().getNumSala()+ " - Poltrona "+cadeira.getNum();
    }
    
    
}
