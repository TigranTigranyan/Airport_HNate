package DAo;

import HibernateEntities.Address;

public interface PassengerDao {
    Address getByIDAddress(Address id);

}
