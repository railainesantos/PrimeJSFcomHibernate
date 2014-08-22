
package Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.Projeto;
import Model.Usuario;
import Utils.HibernateUtil;
import br.com.jbc.controller.Controller;


public class ProjetoDaoImp implements ProjetoDao {

    public Projeto getProjeto(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
        return (Projeto) session.load(Projeto.class, id);
    }
    public List<Projeto> listTodos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        List lista = session.createQuery("from Projeto where idprojeto<>1000").list();
        t.commit();
        session.close();
        return lista;
    }    
    
    public List<Projeto> list() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String queryAux="from Projeto where idprojeto<>1000 ";
        List lista = session.createQuery(queryAux).list();
        t.commit();
        session.close();
        return lista;
    }    
  
    public void remove(Projeto projeto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(projeto);
        t.commit();
        session.close();
    }

    public void save(Projeto projeto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(projeto);
        t.commit();
        session.close();
	}
    public void update(Projeto projeto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(projeto);
        t.commit();
        session.close();
	}

}
