package DAo.IMPL;

import DAo.TripDAo;
import HibernateEntities.Company;
import HibernateEntities.Trip;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TripDaooImpl implements TripDAo {
    @Override
    public void getByID(long id) {
        SessionFactory sf = new Configuration().addAnnotatedClass(Company.class).addAnnotatedClass(Trip.class).buildSessionFactory();
        Session s1 = sf.openSession();
        Transaction trans = null;

        try {
            // start a transaction
            trans = s1.beginTransaction();

            // get Student entity using get() method
            Trip trip = s1.get(Trip.class, id);
            System.out.println(trip.getTripNo());
            System.out.println(trip.getTownFrom());
            System.out.println(trip.getTownTo());

            // commit transaction
            trans.commit();
        } catch (Exception e) {
            if (trans != null) {
                trans.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Trip save(Trip trip) {
        SessionFactory sf = new Configuration().addAnnotatedClass(Company.class).addAnnotatedClass(Trip.class).buildSessionFactory();
        Session s1 = sf.openSession();
        Transaction trans = null;
        try {
            trans = s1.beginTransaction();
            s1.save(trip);
            trans.commit();
        }
        catch (Exception e) {
            if (trans != null) {
                trans.rollback();
            }
            e.printStackTrace();
        }
        return null;    }

    @Override
    public void update(Trip trip) {
        SessionFactory sf = new Configuration().addAnnotatedClass(Company.class).addAnnotatedClass(Trip.class).buildSessionFactory();
        Session s1 = sf.openSession();
        Transaction trans = null;
        try {

            trans = s1.beginTransaction();
            s1.saveOrUpdate(trip);
            Company company1 = s1.get(Company.class, 1);

            company1.getCompName();

            s1.saveOrUpdate(company1);

            trans.commit();
        }catch (Exception e) {
            if (trans != null) {
                trans.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long tripNo) {
        SessionFactory sf = new Configuration().addAnnotatedClass(Company.class).addAnnotatedClass(Trip.class).buildSessionFactory();
        Session s1 = sf.openSession();
        Transaction trans = null;
        try {

            trans = s1.beginTransaction();

            Company company = s1.get(Company.class,tripNo);
            if (company != null) {
                s1.remove(company);
                System.out.println("Company is  deleted");
            }

            trans.commit();
        }
        catch (Exception e) {
            if (trans != null) {
                trans.rollback();
            }
            e.printStackTrace();
        }

    }
}
