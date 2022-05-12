package DAo.IMPL;

import DAo.PassengerDao;
import HibernateEntities.Address;
import HibernateEntities.Passanger;
import HibernateEntities.Trip;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class PassengerDaoImpl implements PassengerDao {
    @Override
    public Address getByIDAddress(Address id) {
        SessionFactory sf = new Configuration().addAnnotatedClass(Passanger.class).addAnnotatedClass(Address.class).buildSessionFactory();
        Session s1 = sf.openSession();
        Transaction trans = null;

        try {
            trans = s1.beginTransaction();

            Address address = s1.get(Address.class, id);
            System.out.println(address.getAddressId());
            System.out.println(address.getCountry());
            System.out.println(address.getCity());


            trans.commit();
        } catch (Exception e) {
            if (trans != null) {
                trans.rollback();
            }
            e.printStackTrace();
        }
        return id;
    }


    public static Long getAddressIdFromDB(String country, String city) {


        Long result = null;
        try (SessionFactory sf = new Configuration().addAnnotatedClass(Passanger.class).addAnnotatedClass(Address.class).buildSessionFactory();
             Session s1 = sf.openSession()){
            Transaction trans = null;
            trans = s1.beginTransaction();

            Query query = s1.createQuery("select a.address_Id from Address a where a.country = :cn and a.city = :ct").
                    setParameter("cn", country).setParameter("ct", city);
            try {
                result = (Long) query.getSingleResult();
            } catch (NoResultException e) {
            }
            System.out.println(result);
            trans.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Address containsAddress(String country, String city, Session s1) {
        Address address = null;
        try {
            Query query = s1.createQuery("select a from Address a where a.country = :cn and a.city = :ct").
                    setParameter("cn", country).setParameter("ct", city);
            try {
                address = (Address) query.getSingleResult();
            } catch (NoResultException e) {
            }
            System.out.println(address);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }


    public static Passanger containsId(String pId, Session s1) {
        Passanger passanger = null;
        try {
            Query query = s1.createQuery("select a from Passanger a where a.pId = :pId ").
                    setParameter("pId", pId);
            try {
                passanger = (Passanger) query.getSingleResult();
            } catch (NoResultException e) {
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return passanger;
    }

    public static Trip containsIdtrip(String tripNo, Session s1) {
        Trip trip = null;
        try {
            Query query = s1.createQuery("select a from Trip a where a.tripNo = :tripNo ").
                    setParameter("tripNo", tripNo);
            try {
                trip = (Trip) query.getSingleResult();
            } catch (NoResultException e) {
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return trip;
    }



}
