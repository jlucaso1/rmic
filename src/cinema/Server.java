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
import dao.FilmeDao;
import dao.IngressoDao;
import dao.SessaoDao;
import dao.UserDao;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Movie;
import models.Session;
import models.Ticket;
import models.User;

public class Server extends UnicastRemoteObject
        implements Services {
    public static void main(String args[]) {
        int porta = 1099;
        try {
            LocateRegistry.createRegistry(porta);
            Server obj = new Server();
            Naming.rebind("rmi://localhost:"+porta+"/Cinema", obj);
            System.out.println("Server Rodando na porta !!");
        } catch (MalformedURLException | RemoteException ex) {
            System.err.println("error: " + ex.getMessage());
        }
    }

    public Server() throws RemoteException {
        super();
    }

    @Override
    public boolean cadastrarUsuario(User usuario) throws RemoteException {
        Connection con = new Conexao().getConnection();
        boolean result = new UserDao().cadastrarUsuario(con, usuario);
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
// Método para exclusão de registro

    @Override
    public User Login(User usuario) throws RemoteException {
        Connection con = new Conexao().getConnection();
        User result = new UserDao().Login(con, usuario);
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public void cadastrarFilme(Movie movie) throws RemoteException {
        Connection con = new Conexao().getConnection();
        new FilmeDao().cadastrarFilme(con, movie);
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    // método para o preenchimento da tabela
    public List<Movie> listarFilmes() throws RemoteException {
        Connection con = new Conexao().getConnection();
        List<Movie> result = new FilmeDao().listarFilmes(con);
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public List<Movie> listarFilmesDisponiveis() throws RemoteException {
        Connection con = new Conexao().getConnection();
        List<Movie> result = new FilmeDao().listarFilmesDisponiveis(con);
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public void cadastrarSessao(Session sessao) throws RemoteException {
        Connection con = new Conexao().getConnection();
        new SessaoDao().cadastrarSessao(con, sessao);
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    // método para o preenchimento da tabela
    public List<Session> listarSessao(Movie movie) throws RemoteException {
        Connection con = new Conexao().getConnection();
        List<Session> result = new SessaoDao().listarSessao(con, movie);
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public List<Session> listarSessaoDisponivel(Movie movie) throws RemoteException {
        Connection con = new Conexao().getConnection();
        List<Session> result = new SessaoDao().listarSessaoDisponivel(con, movie);
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public void atualizarSessao(Session sessao) throws RemoteException {
        Connection con = new Conexao().getConnection();
        new SessaoDao().atualizarSessao(con, sessao);
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    // método para o preenchimento da tabela
    public List<Ticket> listarIngressos(Session sessao) throws RemoteException {
        Connection con = new Conexao().getConnection();
        List<Ticket> result = new IngressoDao().listarIngressos(con, sessao);
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public List<Ticket> listarIngressosDisponiveis(Session sessao) throws RemoteException {
        Connection con = new Conexao().getConnection();
        List<Ticket> result = new IngressoDao().listarIngressosDisponiveis(con, sessao);
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public void comprarIngresso(User usuario, Ticket ingresso) throws RemoteException {
        Connection con = new Conexao().getConnection();
        new IngressoDao().comprarIngresso(con, usuario, ingresso);
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
