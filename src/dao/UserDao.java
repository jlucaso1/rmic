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
import javax.swing.JOptionPane;
import models.User;

/**
 *
 * @author higor
 */
public class UserDao {
    private Connection con;

    public UserDao(Connection con) {
        this.con = con;
    }

    // método  para salvar os registros
    public boolean cadastrarUsuario(User usuario) {
        try {

            //Primeiro  passo  - criar o comando sql
            String sql = "insert into User (user,senha) "
                    + " values (?,?)";

            //Segundo  passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, usuario.getUser());
            stmt.setString(2, usuario.getSenha());

            //Terceiro  passo - executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado realizado com Sucesso!");
            return true;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados: " + erro);
        }
        return false;
    }
// Método para exclusão de registro

    public User Login(User user) {
        String sql = "select * from User where user=? and senha=?";
        try {
            //Segundo  passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, user.getUser());
            stmt.setString(1, user.getSenha());
            ResultSet rs = stmt.executeQuery();
            if (rs.next() == false) {
                return null;
            }
            return new User(rs.getInt("id"), rs.getString("user"), rs.getString("senha"), rs.getBoolean("isAdmin"));
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
