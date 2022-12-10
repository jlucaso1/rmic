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
import models.Movie;
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
public class ComprasDao {
    public List<Purchase> listarCompras(Connection con, User usuario) {
        List<Purchase> lista = new ArrayList<>();
        
        try {
            String sql = "select * from "+Constantes.viewCompras+" where Usuario_id = ?";
            //Segundo  passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, usuario.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Purchase compra = new Purchase(0, usuario, new Ticket(0, new Session(0, new Room(rs.getInt("Sala_num")), new Movie(0, rs.getString("Filme_nome"), ""), true, rs.getDate("Sessao_data"), rs.getTime("Sessao_hora")), new Chair(0, rs.getString("Poltrona_num"), null), true, null), rs.getDate("data"));
                lista.add(compra);
            }
            
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FilmeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
