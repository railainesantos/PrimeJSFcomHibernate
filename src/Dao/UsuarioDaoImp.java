
package Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.Projeto;
import Model.Usuario;
import Utils.HibernateUtil;


public class UsuarioDaoImp implements UsuarioDao {

    public Usuario getUsuario(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
       return (Usuario) session.load(Usuario.class, id);
    }
    public List<Usuario> list() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        List lista = session.createQuery("from Usuario").list();
        t.commit();
        session.close();
        return lista;
    }    

    public List<Usuario> listTab(Projeto projeto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String queryAux="from Usuario ";
	    List lista = session.createQuery(queryAux).list();
	    t.commit();
        session.close();
        return lista;
    }    

  
    public void remove(Usuario usuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(usuario);
        t.commit();
        session.close();
    }
	@Override
	public void save(Usuario usuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.saveOrUpdate(usuario);
        t.commit();
        session.close();
	}
	public void update(Usuario usuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.saveOrUpdate(usuario);
        t.commit();
        session.close();
	}

}
