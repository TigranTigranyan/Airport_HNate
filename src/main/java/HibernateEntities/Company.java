package HibernateEntities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "comp_name",nullable = false)
    private String compName;
    @Column(name="founding_date")
    private Date foundingDate;

    @OneToMany(mappedBy = "company", cascade = CascadeType.PERSIST)
    private Set<Trip> trip = new java.util.LinkedHashSet<>();

    public Set<Trip> getTrip() {
        return trip;
    }


    public Company() {
    }

    public Company(String compName, Date foundingDate) {
        this.compName = compName;
        this.foundingDate = foundingDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public Date getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(Date foundingDate) {
        this.foundingDate = foundingDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Company{");
        sb.append("id=").append(id);
        sb.append(", compName='").append(compName).append('\'');
        sb.append(", foundingDate=").append(foundingDate);
        sb.append('}');
        return sb.toString();
    }

}
