
package Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.Evento;
import Model.Projeto;
import Utils.HibernateUtil;




public class EventoDaoImp implements EventoDao {

	public void save(Evento evento) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(evento);
        t.commit();
        session.close();
		
	}

	public void update(Evento evento) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(evento);
        t.commit();
        session.close();
	}

	public Evento getEvento(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
        return (Evento) session.load(Evento.class, id);
	}

//lista TODOS os Eventos cadastrados no sistema
	public List<Evento> listTodos() {
			Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction t = session.beginTransaction();
	        String queryAux="from Evento";
		    List lista = session.createQuery(queryAux).list();
		    t.commit();
	        session.close();
		    return lista;
	}

	
//lista os Eventos de um determinado projeto
	public List<Evento> listTab(Projeto projeto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String queryAux="from Evento where projeto_idprojeto="+ projeto.getIdProjeto();
	    List lista = session.createQuery(queryAux).list();
	    t.commit();
        session.close();
	    return lista;
	}

	public void remove(Evento evento) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(evento);
        t.commit();
        session.close();
	}

	public void imprime(Evento evento) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        evento = (Evento) session.load(Evento.class, new String("Evento"));

        System.out.println("Nome da Evento: " + evento.getNome());
        session.close();
	}


}
