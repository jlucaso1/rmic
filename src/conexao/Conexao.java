package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/Cinema", "ahnitam", "ahnitam");
        } catch (SQLException | ClassNotFoundException erro) {
            throw new RuntimeException(erro);
        }
    }
}
