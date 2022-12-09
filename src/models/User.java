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
public class User implements Serializable {

    final private int id;
    final private String user;
    final private String senha;
    final private String nome;
    final private boolean isAdmin;

    public User(int id, String user, String senha, boolean isAdmin, String nome) {
        this.id = id;
        this.user = user;
        this.senha = senha;
        this.nome = nome;
        this.isAdmin = isAdmin;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getSenha() {
        return senha;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

}
