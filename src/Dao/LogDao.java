
package Dao;

import java.util.List;

import Model.Log;
import Model.Projeto;


public interface LogDao {

    public void save(Log log);
    public void update(Log log);
    public Log getLog(long id);
    public List<Log> listTodos();
    public List<Log> list(Projeto projeto);
    public void remove(Log log);
}
