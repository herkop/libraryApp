package library;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class EntityServerDao implements EntityDao{
    private static SessionFactory sessionFactory;

    static{
        Configuration configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }
    private Session getSession(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        return session;
    }

    public <T> List<T> getEntity(String query, Object param){

            List<T> entity = null;
            Session session = getSession();
            Query quer = session.createQuery(query);
            quer.setParameter(1, param);
            entity = quer.list();
            session.close();
            return entity;

    }

    public <T> List<T> getEntity(String query, Object param1, Object param2, Object param3){

        List<T> entity = null;
        Session session = getSession();
        Query quer = session.createQuery(query);
		quer.setParameter(1, param1);
        quer.setParameter(2, param2);
        quer.setParameter(3, param3);
        entity = quer.list();
        session.close();
        return entity;

    }

    public <T> T addEntity(T entity) {
        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            session.flush();
            transaction.commit();
            session.close();
            return entity;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public <T> T deleteEntity(T entity){
        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.remove(entity);
            session.flush();
            transaction.commit();
            session.close();
            return entity;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
