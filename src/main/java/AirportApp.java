import DAo.IMPL.PassengerDaoImpl;
import HibernateEntities.*;
import ReadFile.ReadFilesFromTxt;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class AirportApp {
    public static void main(String[] args) throws IllegalArgumentException {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Passanger.class);
        configuration.addAnnotatedClass(Address.class);
        configuration.addAnnotatedClass(Company.class);
        configuration.addAnnotatedClass(Trip.class);
        configuration.addAnnotatedClass(PassInTrip.class);
        SessionFactory factory = configuration.buildSessionFactory();


        ReadFilesFromTxt.readFileCompany(factory);
        ReadFilesFromTxt.readFileTrip(factory);
        ReadFilesFromTxt.readFilePassengerAddress(factory);
        ReadFilesFromTxt.readPassinTrip(factory);
        PassengerDaoImpl passengerDao=new PassengerDaoImpl();

    }


}
