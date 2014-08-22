
package Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.MembrosProjeto;
import Model.Projeto;
import Model.Usuario;
import Utils.HibernateUtil;


public class MembrosProjetoDaoImp implements MembrosProjetoDao {

    public MembrosProjeto getMembrosProjeto(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
        return (MembrosProjeto) session.load(MembrosProjeto.class, id);

    }
    
    

    public List<MembrosProjeto> listTodos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String queryAux="from MembrosProjeto ";
	    List lista = session.createQuery(queryAux).list();
        session.close();
        return lista;
    }    
    public List<MembrosProjeto> listByUsuario(Usuario usuarioAux) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String queryAux="from MembrosProjeto where usuario_idusuario="+usuarioAux.getIdUsuario();
	   
        List lista = session.createQuery(queryAux).list();
     
        t.commit();
        session.close();
        return lista;
    }    

    public List<MembrosProjeto> listByNoUsuario(Usuario usuarioAux, Projeto projetoAux) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String queryAux="from MembrosProjeto where usuario_idusuario<>"
        +usuarioAux.getIdUsuario() +"and projeto_idprojeto="+projetoAux.getIdProjeto();
	   
        List lista = session.createQuery(queryAux).list();
     
        t.commit();
        session.close();
        return lista;
    }    

    public List<MembrosProjeto> listByProjeto(Projeto projetoAux) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String queryAux="from MembrosProjeto where projeto_idprojeto="+projetoAux.getIdProjeto();
	   
        List lista = session.createQuery(queryAux).list();
     
        t.commit();
        session.close();
        return lista;
    }    
    
  
    public void remove(MembrosProjeto membrosProjeto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(membrosProjeto);
        t.commit();
        session.close();
    }
	@Override
	public void save(MembrosProjeto membrosProjeto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.saveOrUpdate(membrosProjeto);
        t.commit();
        session.close();
	}
	public void update(MembrosProjeto membrosProjeto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.saveOrUpdate(membrosProjeto);
        t.commit();
        session.close();
	}

}
