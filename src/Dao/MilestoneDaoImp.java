
package Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.Atividade;
import Model.Milestone;
import Model.Projeto;
import Utils.HibernateUtil;


public class MilestoneDaoImp implements MilestoneDao {

    public Milestone getMilestone(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
        return (Milestone) session.load(Milestone.class, id);
    }

//    public List<Milestone> list() {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction t = session.beginTransaction();
//        String queryAux="from Milestone " ;
//        List lista = session.createQuery(queryAux).list();
//        t.commit();
//        return lista;
//    }    
    
    public List<Milestone> list(Projeto projeto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String queryAux="from Milestone where projeto_idprojeto="+ projeto.getIdProjeto();
        List lista = session.createQuery(queryAux).list();
        t.commit();
        session.close();
        return lista;
    }    

	public List<Milestone> listTodos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String queryAux="from Milestone ";
        List lista = session.createQuery(queryAux).list();
        t.commit();
        session.close();
        return lista;
	}

    
    public void remove(Milestone milestone) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(milestone);
        t.commit();
        session.close();
    }
    public void imprime (Milestone milestone) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        milestone = (Milestone) session.load(Milestone.class, new String("Milestone"));
        session.close();

        System.out.println("Nome da Milestone: " + milestone.getNome());

  }
    public void save(Milestone milestone) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(milestone);
        t.commit();
        session.close();
	}
    public void update(Milestone milestone) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(milestone);
        t.commit();
        session.close();
	}


}
