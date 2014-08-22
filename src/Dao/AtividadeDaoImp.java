
package Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.Atividade;
import Model.Milestone;
import Model.Projeto;
import Utils.HibernateUtil;


public class AtividadeDaoImp implements AtividadeDao {

    public Atividade getAtividade(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
        return (Atividade) session.load(Atividade.class, id);
    }

   public List<Atividade> listTodos() {
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String queryAux="from Atividade ";
	    List lista = session.createQuery(queryAux).list();
	    t.commit();
        session.close();
	    return lista;
	}
    public List<Atividade> list(Milestone milestone) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String queryAux="from Atividade where milestone_idmilestone="+ milestone.getIdMilestone();
	    List lista = session.createQuery(queryAux).list();
	    t.commit();
        session.close();
	    return lista;
}    

    public List<Atividade> listTab(Milestone milestone) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String queryAux="from Atividade where milestone_idmilestone=null ";
	    List lista = session.createQuery(queryAux).list();
	    t.commit();
        session.close();
	    return lista;
}    

  
    public void remove(Atividade atividade) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(atividade);
        t.commit();
        session.close();
    }
    public void imprime (Atividade atividade) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        atividade = (Atividade) session.load(Atividade.class, new String("Atividade"));

        System.out.println("Nome da Atividade: " + atividade.getNome());
        session.close();

  }
    public void save(Atividade atividade) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(atividade);
        t.commit();
        session.close();
	}
    public void update(Atividade atividade) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(atividade);
        t.commit();
        session.close();
	}

}
