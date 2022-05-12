package DAo;

import HibernateEntities.Company;

import java.util.Set;

public interface CompanyDao {
    Company getByID(long id);
    Company save(Company compaany);
    void update(Company company);
    void delete(long companyId);
}
