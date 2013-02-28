package be.kdg.groepi.service;

import be.kdg.groepi.model.*;
import be.kdg.groepi.utils.CompareUtil;
import be.kdg.groepi.utils.DateUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static be.kdg.groepi.utils.DateUtil.dateToLong;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: Gregory
 * Date: 12/02/13
 * Time: 15:01
 * To change this template use File | Settings | File Templates.
 */
public class TripInstanceServiceTest {

    private TripInstance tripinstance;
    private Trip trip;
    private User user;
    //private Long tripId;

    @Before
    public void beforeEachTest() {
        user = new User("TIMMEH", "TIM@M.EH", "hemmit", dateToLong(4, 5, 2011, 15, 32, 0));
        UserService.createUser(user);
        trip = new Trip("Onze eerste trip", "Hopelijk is deze niet te saai!", true, true, user);// trip aanmaken
        TripService.createTrip(trip);
        long startDate = DateUtil.dateToLong(27, 02, 2013, 16, 00, 00);
        long endDate = DateUtil.dateToLong(27, 02, 2013, 20, 00, 00);
        tripinstance = new TripInstance("Bachelor feestje", "Iemand gaat trouwen, bier en vrouwen ole", false, startDate, endDate, user, trip);
        TripInstanceService.createTripInstance(tripinstance);
    }

    @After
    public void afterEachTest() {
        tripinstance = null;
        for (TripInstance tempTripInstance : TripInstanceService.getAllTripInstances()) {
            TripInstanceService.deleteTripInstance(tempTripInstance);
        }
    }

    @Test
    public void createTripInstance() {
        assertEquals("createTripInstance: ", tripinstance, TripInstanceService.getTripInstanceById(tripinstance.getId()));
    }

    @Test
    public void updateTripInstances() {
        tripinstance.setAvailable(Boolean.FALSE);
        tripinstance.setDescription("Ho-ho-ho edited");

        TripInstanceService.updateTripInstance(tripinstance);
        assertEquals("updateTripInstances: ", tripinstance, TripInstanceService.getTripInstanceById(tripinstance.getId()));
    }

    @Test
    public void deleteTripInstance() {
        assertNotNull("deleteTripInstance: Trip found", TripInstanceService.getTripInstanceById(tripinstance.getId()));
        TripInstanceService.deleteTripInstance(tripinstance);
        assertNull("deleteTripInstance: TripInstance not found", TripInstanceService.getTripInstanceById(tripinstance.getId()));
    }

    @Test
    public void addUserToTripInstance() {
        assertTrue("TripInstance: tripInstance should have no participants", tripinstance.getParticipants().isEmpty());
        tripinstance.addParticipantToTripInstance(user);
        assertFalse("TripInstance: tripinstance should have participants", tripinstance.getParticipants().isEmpty());
    }

    @Test
    public void addCostToTripInstance() {
        //assertTrue("TripInstance: tripInstance should have no costs", tripinstance.getCosts().isEmpty());
        Cost cost = new Cost("BEN's cost", 35.53, tripinstance);
        CostService.createCost(cost);
        tripinstance.addCostToTripInstance(cost);
        assertFalse("TripInstance: tripinstance should have costs", tripinstance.getCosts().isEmpty());
    }

    @Test
    public void addRequirementInstanceToTripInstance() {
        //assertTrue("TripInstance: tripInstance should have no requirementInstances", tripinstance.getRequirementInstances().isEmpty());
        RequirementInstance requirementInstance = new RequirementInstance("BEN", 5, "descri", tripinstance);
        RequirementInstanceService.createRequirementInstance(requirementInstance);
        tripinstance.addRequirementInstanceToTripInstance(requirementInstance);
        assertFalse("TripInstance: tripinstance should have requirementInstances", tripinstance.getRequirementInstances().isEmpty());
    }

    @Test
    public void addMessageToTripInstance() {
        //assertTrue("TripInstance: tripinstance should have no messages", tripinstance.getMessages().isEmpty());
        Message message = new Message("BEN's message", dateToLong(12, 10, 1990, 8, 17, 35), tripinstance);
        MessageService.createMessage(message);
        tripinstance.addMessageToTripInstance(message);
        assertFalse("TripInstance: tripInstance should have messages", tripinstance.getMessages().isEmpty());
    }
    
    @Test
    public void getAllTripInstances() {
        long startDate = DateUtil.dateToLong(27, 02, 2013, 16, 00, 00);
        long endDate = DateUtil.dateToLong(27, 02, 2013, 20, 00, 00);
        TripInstance tripinstance1 = new TripInstance("Bachelor feestje 1", "Iemand gaat trouwen, bier en vrouwen ole", false, startDate, endDate, user, trip);
        TripInstanceService.createTripInstance(tripinstance1);
        TripInstance tripinstance2 = new TripInstance("Bachelor feestje 2", "Iemand gaat trouwen, bier en vrouwen ole", false, startDate, endDate, user, trip);
        TripInstanceService.createTripInstance(tripinstance2);
        TripInstance tripinstance3 = new TripInstance("Bachelor feestje 3", "Iemand gaat trouwen, bier en vrouwen ole", false, startDate, endDate, user, trip);
        TripInstanceService.createTripInstance(tripinstance3);
        List<TripInstance> tripList = new ArrayList<>();
        tripList.add(tripinstance1);
        tripList.add(tripinstance2);
        tripList.add(tripinstance3);
        assertFalse("TripInstance: tripList does not contain the tripInstances it should", CompareUtil.compareList(TripInstanceService.getAllTripInstances(), tripList));
    }
    
}
