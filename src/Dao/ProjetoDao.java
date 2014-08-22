

package Dao;

import java.util.List;

import Model.Projeto;
import Model.Usuario;


public interface ProjetoDao {

    public void save(Projeto projeto);
    public void update(Projeto projeto);
    public Projeto getProjeto(long id);
    public List<Projeto> listTodos();
    public List<Projeto> list();
    public void remove(Projeto projeto);
}
