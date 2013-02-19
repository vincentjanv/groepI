package be.kdg.groepi.service;

import be.kdg.groepi.model.Trip;
import be.kdg.groepi.model.User;
import org.junit.*;

import java.sql.Date;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Gregory
 * Date: 12/02/13
 * Time: 15:01
 * To change this template use File | Settings | File Templates.
 */
public class TripServiceTest {

    private Trip trip;
    //private Long tripId;

    @Before
    public void beforeEachTest(){
        trip = new Trip("Stadwandeling Nieuw Zuid", "Ho-ho-ho", Boolean.TRUE, null,null);// trip aanmaken
    }

    @After
    public void afterEachTest(){
        trip = null;
        for (Trip trip : TripService.getAllTrips()) {
            TripService.deleteTrip(trip);
        }
    }

    private Date fillDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(2007, Calendar.MAY, 12,0,0,0);
        cal.set(Calendar.MILLISECOND, 0);
        return new Date(cal.getTime().getTime());
    }

    @Test
    public void createTrip(){
        TripService.createTrip(trip);
        assertEquals("createTrip: tripEquals", trip, TripService.getTripById(trip.getId()));
    }

    @Test
    public void updateTrip(){
        trip.setAvailable(Boolean.FALSE);
        trip.setDescription("Ho-ho-ho edited");
        trip.setStart(fillDate());
        trip.setEnd(fillDate());
        assertEquals("updateUser: userEquals", trip, TripService.getTripById(trip.getId()));
    }

    @Test
    public void deleteTrip(){
        TripService.deleteTrip(trip);
    }

    /*@Test
    public void createTimedTrip(){}
    @Test
    public void updateTimedTrip(){}
    @Test
    public void deleteTimedTrip(){}
    @Test
    public void createRecurrentTrip(){}
    @Test
    public void updateRecurrence(){}
    @Test
    public void deleteRecurrenceInstances(){}
    @Test
    public void deleteRecurrence(){} */
}
