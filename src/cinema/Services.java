/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cinema;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.List;
import models.Movie;
import models.Purchase;
import models.Room;
import models.Session;
import models.Ticket;
import models.User;
/**
 *
 * @author higor
 */
public interface Services extends Remote{
    public void finalizarSessao(Session sessao) throws RemoteException;
    public boolean cadastrarUsuario(User user) throws RemoteException;
    public User Login(User user) throws RemoteException;
    public void cadastrarFilme(Movie movie) throws RemoteException;
    public List<Movie> listarFilmes() throws RemoteException;
    public List<Movie> listarFilmesDisponiveis() throws RemoteException;
    public void cadastrarSessao(Session sessao) throws RemoteException;
    public List<Session> listarDatasSessao(Movie movie) throws RemoteException;
    public List<Session> listarHorariosSessao(Session sessao) throws RemoteException;
    public List<Session> listarDatasSessaoDisponivel(Movie movie) throws RemoteException;
    public List<Session> listarHorariosSessaoDisponivel(Session sessao) throws RemoteException;
    public List<Ticket> listarIngressos(Session sessao) throws RemoteException;
    public List<Ticket> listarIngressosDisponiveis(Session sessao) throws RemoteException;
    public void comprarIngresso(User usuario, Ticket ingresso) throws RemoteException;
    public List<Purchase> listarCompras(User usuario) throws RemoteException;
    public List<Room> listarSalas() throws RemoteException;
    
}
