

package Dao;

import java.util.List;

import Model.Milestone;
import Model.Projeto;


public interface MilestoneDao {

    public void save(Milestone milestone);
    public void update(Milestone milestone);
    public Milestone getMilestone(long id);
    public List<Milestone> list(Projeto projeto);
    public List<Milestone> listTodos();
    public void remove(Milestone milestone);
    public void imprime(Milestone milestone);

}
