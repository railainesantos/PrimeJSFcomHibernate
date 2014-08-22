
package Utils;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import Model.Atividade;
import Model.Evento;
import Model.Log;
import Model.MembrosProjeto;
import Model.Milestone;
import Model.Projeto;
import Model.Usuario;


public class HibernateUtil {

    private static SessionFactory sessionFactory;

    private HibernateUtil() {
        
    }

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            try {
                // Create the SessionFactory from standard (hibernate.cfg.xml)
                // config file.
                AnnotationConfiguration ac = new AnnotationConfiguration();
                ac.addAnnotatedClass(Projeto.class);
                ac.addAnnotatedClass(Atividade.class);
                ac.addAnnotatedClass(Usuario.class);
                ac.addAnnotatedClass(Milestone.class);
                ac.addAnnotatedClass(MembrosProjeto.class);
                ac.addAnnotatedClass(Log.class);
                ac.addAnnotatedClass(Evento.class);

                sessionFactory = ac.configure().buildSessionFactory();
                //SchemaExport se = new SchemaExport(ac);
                //se.create(true, true);

            } catch (Throwable ex) {
                // Log the exception.
                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }

            return sessionFactory;

        } else {
            return sessionFactory;
        }
        
    }

    //public static void main(String[] args) {
    //    HibernateUtil.getSessionFactory();
    //}

}
