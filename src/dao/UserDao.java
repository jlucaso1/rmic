/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;
import utils.Constantes;

/**
 *
 * @author higor
 */
public class UserDao {

    // método  para salvar os registros
    public boolean cadastrarUsuario(Connection con, User usuario) {
        try {

            //Primeiro  passo  - criar o comando sql
            String sql = "insert into " + Constantes.tableUsuarios + " (usuario,senha,nome) values (?,?,?)";

            //Segundo  passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, usuario.getUser());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getNome());

            //Terceiro  passo - executar o comando sql
            stmt.execute();
            stmt.close();

            return true;
        } catch (SQLException erro) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, erro);
        }
        return false;
    }
// Método para exclusão de registro

    public User Login(Connection con, User user) {
        String sql = "select * from " + Constantes.tableUsuarios + " where usuario=? and senha=?";
        try {
            //Segundo  passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, user.getUser());
            stmt.setString(1, user.getSenha());
            ResultSet rs = stmt.executeQuery();
            if (rs.next() == false) {
                return null;
            }
            return new User(rs.getInt("id"), rs.getString("usuario"), rs.getString("senha"), rs.getBoolean("isAdmin"), rs.getString("nome"));
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
