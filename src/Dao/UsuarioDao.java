

package Dao;

import java.util.List;

import Model.Projeto;
import Model.Usuario;


public interface UsuarioDao {

    public void save(Usuario usuario);
    public void update(Usuario usuario);
    public Usuario getUsuario(long id);
    public List<Usuario> list();
    public List<Usuario> listTab(Projeto projeto);
    public void remove(Usuario usuario);
}
