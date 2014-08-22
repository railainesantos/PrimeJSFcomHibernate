
package Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.Log;
import Model.Projeto;
import Utils.HibernateUtil;


public class LogDaoImp implements LogDao {

    public Log getLog(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
        return (Log) session.load(Log.class, id);
    }
    public List<Log> listTodos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        List lista = session.createQuery("from Log where idlog<>1000").list();
        t.commit();
        session.close();
        return lista;
    }    
    
    public List<Log> list(Projeto projeto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String queryAux="from Log where idlog<>1000 and projeto_idprojeto="+ projeto.getIdProjeto();
        List lista = session.createQuery(queryAux).list();
        t.commit();
        session.close();
        return lista;
    }    
  
    public void remove(Log log) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(log);
        t.commit();
        session.close();
    }

    public void save(Log log) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(log);
        t.commit();
        session.close();
	}
    public void update(Log log) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(log);
        t.commit();
        session.close();
	}

}
