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
import models.Room;
import utils.Constantes;

/**
 *
 * @author higor
 */
public class SalasDao {
    public List<Room> listarSalas(Connection con) {
        List<Room> lista = new ArrayList<>();
        
        try {
            String sql = "select * from "+Constantes.tableSalas+" ORDER BY num ASC";
            //Segundo  passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Room sala = new Room(rs.getInt("num"));
                lista.add(sala);
            }
            
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FilmeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
