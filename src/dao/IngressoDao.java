/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Chair;
import models.Purchase;
import models.Room;
import models.Session;
import models.Ticket;
import models.User;
import utils.Constantes;

/**
 *
 * @author higor
 */
public class IngressoDao {

    public void comprarIngresso(Connection con, User usuario, Ticket ingresso) {
        try {
            //Primeiro  passo  - criar o comando sql
            String sql = "insert into " + Constantes.tableCompras + " (Ingresso_id, Usuario_id) values (?, ?)";

            //Segundo  passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, ingresso.getId());
            stmt.setInt(2, usuario.getId());

            //Terceiro  passo - executar o comando sql
            stmt.execute();
            stmt.close();
        } catch (SQLException erro) {
            Logger.getLogger(IngressoDao.class.getName()).log(Level.SEVERE, null, erro);
        }
    }

    // método para o preenchimento da tabela
    public List<Ticket> listarIngressos(Connection con, Session sessao) {
        List<Ticket> lista = new ArrayList<>();

        String sql = "select * from " + Constantes.viewIngressos + " where Sessao_id = ?";
        try {
            //Segundo  passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, sessao.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Ticket ingresso = new Ticket(rs.getInt("id"), sessao, new Chair(rs.getInt("Poltrona_id"), rs.getString("Poltrona_num"), new Room(rs.getInt("Sala_num"))), rs.getBoolean("disponivel"), new Purchase(0, new User(0, "", "", false, rs.getString("nome")), null, null));
                lista.add(ingresso);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(IngressoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List<Ticket> listarIngressosDisponiveis(Connection con, Session sessao) {
        List<Ticket> lista = new ArrayList<>();

        String sql = "select * from " + Constantes.viewIngressosDisponiveis + " where Sessao_id = ?";
        try {
            //Segundo  passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, sessao.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Ticket ingresso = new Ticket(rs.getInt("id"), sessao, new Chair(rs.getInt("Poltrona_id"), rs.getString("Poltrona_num"), new Room(rs.getInt("Sala_num"))), true, null);
                lista.add(ingresso);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(IngressoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
