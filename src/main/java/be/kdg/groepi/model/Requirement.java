package be.kdg.groepi.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Author: Ben Oeyen
 * Date: 6/02/13
 * Class: Requirement
 * Description:
 */

@Entity
@Table(name = "T_REQUIREMENT")
public class Requirement implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "requirement_id")
    long fId;
    @Column(name = "name")
    String fName;
    @Column(name = "amount")
    long fAmount;
    @Column(name = "description")
    String fDescription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip fTrip;

    // Hibernates needs empty constructor
    public Requirement() {
    }

    public Requirement(String fName, long fAmount, String fDescription, Trip fTrip) {
        this.fName = fName;
        this.fAmount = fAmount;
        this.fDescription = fDescription;
        this.fTrip = fTrip;
    }

    public Long setId() {
        return fId;
    }

    public Long getId() {
        return fId;
    }

    public String getName() {
        return fName;
    }

    public void setName(String fName) {
        this.fName = fName;
    }

    public long getAmount() {
        return fAmount;
    }

    public void setAmount(long fAmount) {
        this.fAmount = fAmount;
    }

    public void setDescription(String fDescription) {
        this.fDescription = fDescription;
    }

    public String getDescription() {
        return fDescription;
    }

    public Trip getTrip() {
        return fTrip;
    }

    public void setTrip(Trip fTrip) {
        this.fTrip = fTrip;
    }
}
