/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.Chair;
import models.Movie;
import models.Room;
import models.Session;
import models.Ticket;

/**
 *
 * @author higor
 */
public class CinemaDao {
    final private Connection con;
    
    final private String tableFilmes = "Filmes";
    final private String tableIngressos = "Ingressos";
    final private String tableCadeiras = "Cadeiras";
    final private String tableSessao = "Sessao";
    final private String tableSalas = "Salas";
    
    final private String viewFilmesDisponiveis = "filmes_disponiveis";
    final private String viewSessoesDisponiveis = "sessoes_disponiveis";
    final private String viewIngressosDisponiveis = "ingressos_disponiveis";
    final private String viewIngressos = "ingressos_view";

    public CinemaDao() {
        this.con = new Conexao().getConnection();
    }
    
    public void cadastrarFilme(Movie movie) {
        try {

            //Primeiro  passo  - criar o comando sql
            String sql = "insert into "+tableFilmes+" (nome) "
                    + " values (?)";

            //Segundo  passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, movie.getNome());

            //Terceiro  passo - executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado realizado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados: " + erro);

        }

    }

    // método para o preenchimento da tabela
    public List<Movie> listarFilmes() {
        List<Movie> lista = new ArrayList<>();

        String sql = "select * from "+tableFilmes+" ORDER BY nome ASC";
        try {
            //Segundo  passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Movie movie = new Movie(rs.getInt("idFilmes"), rs.getString("nome"), rs.getString("descricao"));
                lista.add(movie);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(CinemaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public List<Movie> listarFilmesDisponiveis() {
        List<Movie> lista = new ArrayList<>();

        String sql = "select * from "+viewFilmesDisponiveis+" ORDER BY nome ASC";
        try {
            //Segundo  passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Movie movie = new Movie(rs.getInt("idFilmes"), rs.getString("nome"), "");
                lista.add(movie);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(CinemaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public void cadastrarSessao(Session sessao) {
        try {

            //Primeiro  passo  - criar o comando sql
            String sql = "insert into "+tableSessao+" (Salas_numSala, Filmes_idFilmes, inicio) "
                    + " values (?, ?, ?)";

            //Segundo  passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, sessao.getSala().getNumSala());
            stmt.setInt(2, sessao.getSala().getNumSala());
            stmt.setDate(3, new Date(sessao.getData().getTime()));

            //Terceiro  passo - executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado realizado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados: " + erro);

        }

    }

    // método para o preenchimento da tabela
    public List<Session> listarSessao(Movie movie) {
        List<Session> lista = new ArrayList<>();

        String sql = "select * from "+tableSessao+" where "+tableSessao+".Filmes_idFilmes = ?";
        try {
            //Segundo  passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, movie.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Session session = new Session(rs.getInt("idSessao"), new Room(rs.getInt("Salas_numSala")), movie, rs.getBoolean("finalizada"), rs.getDate("inicio"));
                lista.add(session);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(CinemaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public List<Session> listarSessaoDisponivel(Movie movie) {
        List<Session> lista = new ArrayList<>();

        String sql = "select * from "+viewSessoesDisponiveis+" where "+tableSessao+".Filmes_idFilmes = ?";
        try {
            //Segundo  passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, movie.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Session session = new Session(rs.getInt("idSessao"), new Room(rs.getInt("Salas_numSala")), movie, rs.getBoolean("finalizada"), rs.getDate("inicio"));
                lista.add(session);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(CinemaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public void atualizarSessao(Session sessao) {
        try {

            //Primeiro  passo  - criar o comando sql
            String sql = "update "+tableSessao+" set finalizada=? where idSessao=?";

            //Segundo  passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setBoolean(1, sessao.isFinalizada());
            stmt.setInt(2, sessao.getId());

            //Terceiro  passo - executar o comando sql
            stmt.executeUpdate();
            stmt.close();

            JOptionPane.showMessageDialog(null, "atualizado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados: " + erro);

        }
    }
    
    public void atualizarIngresso(Ticket ingresso) {
        try {

            //Primeiro  passo  - criar o comando sql
            String sql = "update "+tableIngressos+" set disponivel=? where idIngressos=?";

            //Segundo  passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setBoolean(1, ingresso.isDisponivel());
            stmt.setInt(2, ingresso.getId());

            //Terceiro  passo - executar o comando sql
            stmt.executeUpdate();
            stmt.close();

            JOptionPane.showMessageDialog(null, "atualizado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados: " + erro);

        }
    }

    // método para o preenchimento da tabela
    public List<Ticket> listarIngressos(Session sessao) {
        List<Ticket> lista = new ArrayList<>();

        String sql = "select * from "+viewIngressos+" where Sessao_idSessao = ?";
        try {
            //Segundo  passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, sessao.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Ticket ingresso = new Ticket(rs.getInt("idIngressos"), sessao, new Chair(rs.getInt("Cadeiras_id"), rs.getInt("numCadeira"), new Room(rs.getInt("Salas_numSala"))), rs.getBoolean("disponivel"));
                lista.add(ingresso);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(CinemaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public List<Ticket> listarIngressosDisponiveis(Session sessao) {
        List<Ticket> lista = new ArrayList<>();

        String sql = "select * from "+viewIngressosDisponiveis+" where Sessao_idSessao = ?";
        try {
            //Segundo  passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, sessao.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Ticket ingresso = new Ticket(rs.getInt("idIngressos"), sessao, new Chair(rs.getInt("Cadeiras_id"), rs.getInt("numCadeira"), new Room(rs.getInt("Salas_numSala"))), rs.getBoolean("disponivel"));
                lista.add(ingresso);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(CinemaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
