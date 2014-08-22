

package Dao;

import java.util.List;

import Model.MembrosProjeto;
import Model.Projeto;
import Model.Usuario;


public interface MembrosProjetoDao {

    public void save(MembrosProjeto membrosProjeto);
    public void update(MembrosProjeto membrosProjeto);
    public MembrosProjeto getMembrosProjeto(long id);
    public List<MembrosProjeto> listByProjeto(Projeto projetoAux);
    public List<MembrosProjeto> listByUsuario(Usuario usuarioAux);
    public List<MembrosProjeto> listByNoUsuario(Usuario usuarioAux,Projeto projetoAux);
    public List<MembrosProjeto> listTodos();
    public void remove(MembrosProjeto membrosProjeto);
}
