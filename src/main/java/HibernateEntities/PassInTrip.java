package HibernateEntities;
import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Pass_In_Trip")
public class PassInTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tripNo")
    private Trip trip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pId")
    private Passanger passanger;

    @Column(name = "tripDate")
    private Date datetrip;

    @Column(name = "place")
    private String place;

    public PassInTrip() {

    }

    public PassInTrip(Trip trip, Passanger passanger, Date datetrip, String place) {
        this.trip = trip;
        this.passanger = passanger;
        this.datetrip = datetrip;
        this.place = place;
    }


    public PassInTrip(Passanger passenger, Trip trip) {
        this.passanger = passenger;
        this.trip = trip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Passanger getPassanger() {
        return passanger;
    }

    public void setPassenger(Passanger passenger) {
        this.passanger = passenger;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

}
