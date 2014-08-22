

package Dao;

import java.util.List;

import Model.Atividade;
import Model.Milestone;


public interface AtividadeDao {

    public void save(Atividade atividade);
    public void update(Atividade atividade);
    public Atividade getAtividade(long id);
    public List<Atividade> listTodos();
    public List<Atividade> list(Milestone milestone);
    public List<Atividade> listTab(Milestone milestone);
    public void remove(Atividade atividade);
    public void imprime(Atividade atividade);

}
