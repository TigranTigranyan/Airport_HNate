package DAo;

import HibernateEntities.Company;
import HibernateEntities.Trip;

public interface TripDAo {

    void getByID(long id);
    Trip save(Trip trip);
    void update(Trip trip);
    void delete(long tripNo);
}
