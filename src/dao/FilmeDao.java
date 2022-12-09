/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Movie;
import utils.Constantes;

/**
 *
 * @author higor
 */
public class FilmeDao {
    
    public void cadastrarFilme(Connection con, Movie movie) {
        try {
            //Primeiro  passo  - criar o comando sql
            String sql = "insert into "+Constantes.tableFilmes+" (nome) values (?)";

            //Segundo  passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, movie.getNome());

            //Terceiro  passo - executar o comando sql
            stmt.execute();
            stmt.close();
        } catch (SQLException erro) {
            Logger.getLogger(FilmeDao.class.getName()).log(Level.SEVERE, null, erro);
        }
    }

    // método para o preenchimento da tabela
    public List<Movie> listarFilmes(Connection con) {
        List<Movie> lista = new ArrayList<>();
        
        try {
            String sql = "select * from "+Constantes.tableFilmes+" ORDER BY nome ASC";
            //Segundo  passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Movie movie = new Movie(rs.getInt("id"), rs.getString("nome"), "");
                lista.add(movie);
            }
            
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FilmeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public List<Movie> listarFilmesDisponiveis(Connection con) {
        List<Movie> lista = new ArrayList<>();

        String sql = "select * from "+Constantes.viewFilmesDisponiveis+" ORDER BY nome ASC";
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
            Logger.getLogger(FilmeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
