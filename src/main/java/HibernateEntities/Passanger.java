package HibernateEntities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "Passanger")
public class Passanger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pId;
    @Column(name = "psg_anme")
    private String psgName;
    @Column(name = "phone_num")
    private String phoneNum;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_Id" ,foreignKey = @ForeignKey(name = "psg_add_fk"))
    private Address address;

    @OneToMany(mappedBy = "passanger",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<PassInTrip> trips = new HashSet<>();

    public Passanger(String psgName, String phoneNum, Address address) {
        this.psgName = psgName;
        this.phoneNum = phoneNum;
        this.address=address;
    }


    public  Passanger(){}

    public Passanger(String s) {


    }


    public int getpId() {
        return Math.toIntExact(pId);
    }

    public void setpId(int pId) {
        this.pId = Long.valueOf(pId);
    }

    public String getPsgName() {
        return psgName;
    }

    public void setPsgName(String psgName) {
        this.psgName = psgName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<PassInTrip> getTrips() {
        return trips;
    }

    public void setTrips(Set<PassInTrip> trips) {
        this.trips = trips;
    }
}
