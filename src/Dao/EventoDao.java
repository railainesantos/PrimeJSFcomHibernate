

package Dao;

import java.util.List;

import Model.Evento;
import Model.Projeto;


public interface EventoDao {

    public void save(Evento evento);
    public void update(Evento evento);
    public Evento getEvento(long id);
    public List<Evento> listTodos();
    public List<Evento> listTab(Projeto projeto);
    public void remove(Evento evento);
    public void imprime(Evento evento);

}
