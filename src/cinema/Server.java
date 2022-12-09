/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinema;

/**
 *
 * @author higor
 */
import conexao.Conexao;
import dao.CinemaDao;
import dao.UserDao;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import models.User;

public class Server extends UnicastRemoteObject
    implements Services {
    public static void main(String args[]) {
        try {
            LocateRegistry.createRegistry(1099);
            // Creates an object of the HelloServer class.
            Server obj = new Server();
            // Bind this object instance to the name "HelloServer".
            Naming.rebind("rmi://localhost:1099/Cinema", obj);
            System.out.println("Ligado no registro!!");
            
        } catch (MalformedURLException | RemoteException ex) {
            System.err.println("error: " + ex.getMessage());
        }
    }
      
    final private String tableFilmes = "Filmes";
    final private String tableIngressos = "Ingressos";
    final private String tableCadeiras = "Cadeiras";
    final private String tableSessao = "Sessao";
    final private String tableSalas = "Salas";
    
    final private String viewFilmesDisponiveis = "filmes_disponiveis";
    final private String viewSessoesDisponiveis = "sessoes_disponiveis";
    final private String viewIngressosDisponiveis = "ingressos_disponiveis";
    final private String viewIngressos = "ingressos_view";
    
  public Server() throws RemoteException {
    super();
  }
@Override
public boolean cadastrarUsuario(User usuario) throws RemoteException{
    Connection con = new Conexao().getConnection();
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
            con.close();
            return true;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados: " + erro);
        }
        return false;
    }
// Método para exclusão de registro
@Override
    public User Login(User user) throws RemoteException{
        Connection con = new Conexao().getConnection();
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
            con.close();
            return new User(rs.getInt("id"), rs.getString("user"), rs.getString("senha"), rs.getBoolean("isAdmin"));
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public void cadastrarFilme(Movie movie) throws RemoteException{
        Connection con = new Conexao().getConnection();
        try {

            //Primeiro  passo  - criar o comando sql
            String sql = "insert into "+tableFilmes+" (nome) "
                    + " values (?)";

            //Segundo  passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, movie.getNome());

            //Terceiro  passo - executar o comando sql
            stmt.execute();
            con.close();
con.close();
            JOptionPane.showMessageDialog(null, "Cadastrado realizado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados: " + erro);

        }

    }
    @Override
    // método para o preenchimento da tabela
    public List<Movie> listarFilmes() throws RemoteException{
        Connection con = new Conexao().getConnection();
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
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CinemaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    @Override
    public List<Movie> listarFilmesDisponiveis() throws RemoteException{
        Connection con = new Conexao().getConnection();
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
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CinemaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    @Override
    public void cadastrarSessao(Session sessao) throws RemoteException{
        Connection con = new Conexao().getConnection();
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
con.close();
            JOptionPane.showMessageDialog(null, "Cadastrado realizado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados: " + erro);

        }

    }
@Override
    // método para o preenchimento da tabela
    public List<Session> listarSessao(Movie movie) throws RemoteException{
        Connection con = new Conexao().getConnection();
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
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CinemaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    @Override
    public List<Session> listarSessaoDisponivel(Movie movie) throws RemoteException{
        Connection con = new Conexao().getConnection();
        List<Session> lista = new ArrayList<>();

        String sql = "select * from "+viewSessoesDisponiveis+" where Filmes_idFilmes = ?";
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
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CinemaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    @Override
    public void atualizarSessao(Session sessao) throws RemoteException{
        Connection con = new Conexao().getConnection();
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
con.close();
            JOptionPane.showMessageDialog(null, "atualizado com Sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados: " + erro);

        }
    }
@Override
    // método para o preenchimento da tabela
    public List<Ticket> listarIngressos(Session sessao) throws RemoteException{
        Connection con = new Conexao().getConnection();
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
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CinemaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    @Override
    public List<Ticket> listarIngressosDisponiveis(Session sessao) throws RemoteException{
        Connection con = new Conexao().getConnection();
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
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CinemaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

}
