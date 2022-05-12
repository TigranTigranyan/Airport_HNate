package HibernateEntities;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "Address", uniqueConstraints = {
        @UniqueConstraint(name = "uc_address_country_city", columnNames = {"country", "city"})
})
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long address_Id;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;



    @OneToMany( mappedBy = "address",cascade = CascadeType.PERSIST)
    private Set<Passanger> passanger= new HashSet<>();

    public Address() {
    }

    public Address(String country, String city) {
        this.country = country;
        this.city = city;
    }




    public int getAddressId() {
        return Math.toIntExact(address_Id);
    }

    public void setAddressId(int addressId) {
        this.address_Id = Long.valueOf(addressId);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
