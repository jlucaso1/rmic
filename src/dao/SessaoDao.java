/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Movie;
import models.Room;
import models.Session;
import utils.Constantes;

/**
 *
 * @author higor
 */
public class SessaoDao {
    public void cadastrarSessao(Connection con, Session sessao) {
        try {
            //Primeiro  passo  - criar o comando sql
            String sql = "insert into "+Constantes.tableSessao+" (Sala_num, Filme_id, data, hora) values (?, ?, ?, ?)";

            //Segundo  passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, sessao.getSala().getNumSala());
            stmt.setInt(2, sessao.getFilme().getId());
            stmt.setDate(3, new Date(sessao.getData().getTime()));
            stmt.setTime(0, sessao.getHora());

            //Terceiro  passo - executar o comando sql
            stmt.execute();
            stmt.close();
        } catch (SQLException erro) {
            Logger.getLogger(SessaoDao.class.getName()).log(Level.SEVERE, null, erro);
        }

    }

    // método para o preenchimento da tabela
    public List<Session> listarSessao(Connection con, Movie movie) {
        List<Session> lista = new ArrayList<>();

        String sql = "select * from "+Constantes.tableSessao+" where Filme_id = ?";
        try {
            //Segundo  passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, movie.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Session session = new Session(rs.getInt("id"), new Room(rs.getInt("Sala_num")), movie, rs.getBoolean("finalizada"), rs.getDate("data"), rs.getTime("hora"));
                lista.add(session);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(SessaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public List<Session> listarSessaoDisponivel(Connection con, Movie movie) {
        List<Session> lista = new ArrayList<>();

        String sql = "select * from "+Constantes.viewSessoesDisponiveis+" where Filmes_id = ?";
        try {
            //Segundo  passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, movie.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Session session = new Session(rs.getInt("id"), new Room(rs.getInt("Sala_num")), movie, rs.getBoolean("finalizada"), rs.getDate("data"), rs.getTime("hora"));
                lista.add(session);
            }
            
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(SessaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public void atualizarSessao(Connection con, Session sessao) {
        try {
            //Primeiro  passo  - criar o comando sql
            String sql = "update "+Constantes.tableSessao+" set finalizada=? where id=?";

            //Segundo  passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setBoolean(1, sessao.isFinalizada());
            stmt.setInt(2, sessao.getId());

            //Terceiro  passo - executar o comando sql
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException erro) {
            Logger.getLogger(SessaoDao.class.getName()).log(Level.SEVERE, null, erro);
        }
    }
}
