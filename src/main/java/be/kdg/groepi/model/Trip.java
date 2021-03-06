package be.kdg.groepi.model;

import be.kdg.groepi.annotations.ExcludeFromGson;
import be.kdg.groepi.utils.CompareUtil;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Author: Ben Oeyen
 * Date: 6/02/13
 * Class: Trip
 * Description:
 */

/*
 Hibernate Inheritance: Table Per Subclass
 http://viralpatel.net/blogs/hibernate-inheritance-table-per-subclass-annotation-xml-mapping/
 Hibernate Inheritance: Table Per Class Hierarchy
 http://viralpatel.net/blogs/hibernate-inheritence-table-per-hierarchy-mapping/
 */
@Entity
@Table(name = "T_TRIP")
//@Inheritance(strategy=InheritanceType.JOINED)  //Hibernate Inheritance: Table Per Subclass
public class Trip implements Serializable, Comparable {

    @Id
    @GeneratedValue
    @Column(name = "trip_id")
    private Long fId;
    @Column(name = "title")
    private String fTitle;
    @Column(name = "description")
    private String fDescription;
    @Column(name = "public")
    private Boolean fAvailable;
    @Column(name = "repeatable")
    private Boolean fRepeatable;
    @Column(name = "enableChat")
    private Boolean fEnableChat;
    @ExcludeFromGson
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User fOrganiser;
    @ExcludeFromGson
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "fTrip")
    @Cascade(CascadeType.DELETE)
    @OrderBy(value = "stopnumber")
    private Set<Stop> fStops = new HashSet<>();
    @ExcludeFromGson
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "fTrip")
    @Cascade(CascadeType.DELETE)
    private Set<Requirement> fRequirements = new HashSet<>();

    public Trip() {
    }

    public Trip(String fTitle, String fDescription, Boolean fAvailable, Boolean fRepeatable, Boolean fEnableChat, User fOrganiser) {
        this.fTitle = fTitle;
        this.fDescription = fDescription;
        this.fAvailable = fAvailable;
        this.fRepeatable = fRepeatable;
        this.fEnableChat = fEnableChat;
        this.fOrganiser = fOrganiser;
    }

    public Long getId() {
        return fId;
    }

    public void setId(Long fId) {
        this.fId = fId;
    }

    public String getTitle() {
        return fTitle;
    }

    public void setTitle(String fTitle) {
        this.fTitle = fTitle;
    }

    public String getDescription() {
        return fDescription;
    }

    public void setDescription(String fDescription) {
        this.fDescription = fDescription;
    }

    public Boolean getAvailable() {
        return fAvailable;
    }

    public void setAvailable(Boolean fAvailable) {
        this.fAvailable = fAvailable;
    }

    public Boolean getRepeatable() {
        return fRepeatable;
    }

    public void setRepeatable(Boolean fRepeatable) {
        this.fRepeatable = fRepeatable;
    }

    public Boolean getEnableChat() {
        return fEnableChat;
    }

    public void setEnableChat(Boolean fEnableChat) {
        this.fEnableChat = fEnableChat;
    }

    public User getOrganiser() {
        return fOrganiser;
    }

    public void setOrganiser(User fOrganiser) {
        this.fOrganiser = fOrganiser;
    }

    public Set<Requirement> getRequirements() {
        return fRequirements;
    }

    public void setRequirements(Set<Requirement> fRequirements) {
        this.fRequirements = fRequirements;
    }

    public void addRequirementToTrip(Requirement requirement) {
        this.fRequirements.add(requirement);
    }

    public void removeRequirementFromTrip(Requirement requirement) {
        this.fRequirements.remove(requirement);
    }

    public Set<Stop> getStops() {
        return fStops;
    }

    public void setStops(Set<Stop> fStops) {
        this.fStops = fStops;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        Trip trip = (Trip) o;
        if (!CompareUtil.compareString(fTitle, trip.getTitle())) {
            return false;
        }
        if (!CompareUtil.compareString(fDescription, trip.getDescription())) {
            return false;
        }
        if (fAvailable != trip.getAvailable()) {
            return false;
        }
        if (fRepeatable != trip.getRepeatable()) {
            return false;
        }
        if (fEnableChat != trip.getEnableChat()) {
            return false;
        }
        if (!this.fOrganiser.equals(trip.getOrganiser())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public int compareTo(Object o) {
        Trip trip = (Trip) o;
        return this.fTitle.compareToIgnoreCase(trip.getTitle());
    }
}
