package HibernateEntities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Trip")
public class Trip {
    @Id
    private int tripNo;
    @Column(name = "plane")
    private String plane;
    @Column(name = "time_out")
   private Date timeOut;
    @Column(name = "time_in")
    private Date timeIn;
    @Column(name = "town_from")
   private String townFrom;
    @Column(name = "town_to")
   private String townTo;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_comp" ,foreignKey = @ForeignKey(name = "trip_comp_fk"))
    private Company company;

    @OneToMany(mappedBy = "trip",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<PassInTrip> passengers = new HashSet<>();

    public Trip() {

    }


    public Trip(int tripNo, Company company, String plane, String townFrom, String townTo, Date timeOut, Date timeIn) {
        this.tripNo = tripNo;
        this.plane = plane;
        this.townFrom = townFrom;
        this.townTo = townTo;
        this.timeOut = timeOut;
        this.timeIn = timeIn;
        this.company = company;
    }

    public Trip(String s) {
        this.tripNo= Integer.parseInt(s);

    }


    public int getTripNo() {
        return tripNo;
    }

    public void setTripNo(int tripNo) {
        this.tripNo = tripNo;
    }


    public String getPlane() {
        return plane;
    }

    public void setPlane(String plane) {
        this.plane = plane;
    }

    public Date getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Date timeOut) {
        this.timeOut = timeOut;
    }

    public Date getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(Date timeIn) {
        this.timeIn = timeIn;
    }

    public String getTownFrom() {
        return townFrom;
    }

    public void setTownFrom(String townFrom) {
        this.townFrom = townFrom;
    }

    public String getTownTo() {
        return townTo;
    }

    public void setTownTo(String townTo) {
        this.townTo = townTo;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<PassInTrip> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<PassInTrip> passengers) {
        this.passengers = passengers;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Trip{");
        sb.append(", tripNo=").append(tripNo);
        sb.append(", plane='").append(plane).append('\'');
        sb.append(", timeOut=").append(timeOut);
        sb.append(", timeIn=").append(timeIn);
        sb.append(", townFrom='").append(townFrom).append('\'');
        sb.append(", townTo='").append(townTo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
