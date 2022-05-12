package DAo.IMPL;

import DAo.CompanyDao;
import HibernateEntities.Company;
import HibernateEntities.Trip;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class CompanyDaoImpl implements CompanyDao {
    @Override
    public Company getByID(long id) {


        SessionFactory sf = new Configuration().addAnnotatedClass(Company.class).addAnnotatedClass(Trip.class).buildSessionFactory();
        Session s1 = sf.openSession();
        Transaction trans = null;

        try {
            // start a transaction
            trans = s1.beginTransaction();

            // get Student entity using get() method
            Company company = s1.get(Company.class, id);
            System.out.print(company.getCompName());
            System.out.print(company.getFoundingDate());

            // commit transaction
            trans.commit();
        } catch (Exception e) {
            if (trans != null) {
                trans.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Company save(Company compaany) {
        SessionFactory sf = new Configuration().addAnnotatedClass(Company.class).addAnnotatedClass(Trip.class).buildSessionFactory();
        Session s1 = sf.openSession();
        Transaction trans = null;
        try {
            trans = s1.beginTransaction();
            s1.save(compaany);
            trans.commit();
        }
        catch (Exception e) {
            if (trans != null) {
                trans.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Company company) {
        SessionFactory sf = new Configuration().addAnnotatedClass(Company.class).addAnnotatedClass(Trip.class).buildSessionFactory();
        Session s1 = sf.openSession();
        Transaction trans = null;
        try {

            trans = s1.beginTransaction();
            s1.saveOrUpdate(company);
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
    public void delete(long companyId) {
        SessionFactory sf = new Configuration().addAnnotatedClass(Company.class).addAnnotatedClass(Trip.class).buildSessionFactory();
        Session s1 = sf.openSession();
        Transaction trans = null;
        try {

            trans = s1.beginTransaction();

            Company company = s1.get(Company.class,companyId);
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
