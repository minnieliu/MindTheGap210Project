package ca.ubc.cs.cpsc210.mindthegap.tests.model;

import ca.ubc.cs.cpsc210.mindthegap.model.*;
import ca.ubc.cs.cpsc210.mindthegap.model.exception.StationException;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import org.junit.Before;
import org.junit.Test;



import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Unit tests for StationManager
 */
public class StationManagerTest {
    private StationManager stnManager;

    @Before
    public void setUp() {
        stnManager = StationManager.getInstance();
        stnManager.clearSelectedStation();
        stnManager.clearStations();

    }

    @Test
    public void testStationManagerConstructor() {
        assertEquals(stnManager.getSelected(), null);
    }

    @Test
    public void testArrival(){
        Arrival arrival1 = new Arrival(320,"Downtown","West Bound - Marine Drive");
        Arrival arrival2 = new Arrival(70,"Richmond","East Bound - Lansdowne");
        Arrival arrival3 = new Arrival(70,"Metrotown","East Bound - Broadway");

        assertEquals(arrival1.getDestination(),"Downtown");
        assertEquals(arrival2.getPlatform(), "East Bound - Lansdowne");
        assertEquals(arrival3.getPlatformName(),"Broadway");
        assertEquals(arrival1.getTimeToStationInMins(), 5);
        assertEquals(arrival3.getTravelDirn(), "East Bound");

    }

    @Test
    public void testArrivalBoard(){
        Arrival arrival1 = new Arrival(320,"Downtown","West Bound - Marine Drive");

        Line line1 = new Line(LineResourceData.CENTRAL,"123","Canada Line");
        Line line2 = new Line(LineResourceData.NORTHERN,"456","Millenium Line");

        ArrivalBoard ab = new ArrivalBoard(line2, "West");

        //assertEquals(ab.getLine(),line2);
        assertEquals(ab.getTravelDirn(),"West");
        assertEquals(ab.getNumArrivals(), 0);
        ab.addArrival(arrival1);
        assertEquals(ab.getNumArrivals(), 1);
        ab.clearArrivals();
        assertEquals(ab.getNumArrivals(), 0);

    }
    @Test
    public void testBranch(){
        Branch branch = new Branch("");

        assertEquals(branch.getPoints(),null);
    }

    @Test
    public void testLine(){
        Line line1 = new Line(LineResourceData.CENTRAL,"123","Canada Line");
        Line line2 = new Line(LineResourceData.NORTHERN,"456","Millenium Line");
        LatLon loc = new LatLon(10,10);
        Station station1 = new Station("789","123",loc);
        Station station2 = new Station("123", "hello", loc);
        Station station3 = new Station("183", "hello", loc);
        Branch branch = new Branch("");

        assertEquals(line1.getName(), "Canada Line");
        assertEquals(line2.getName(), "Millenium Line");
        assertEquals(line1.getColour(),0xFFDC241F);
        assertEquals(line1.getId(), "123");
        assertFalse(line1.hasStation(station1));
        assertEquals(line1.getStations(), null);
        line1.addStation(station1);
        line1.addStation(station2);
        line1.addStation(station3);
        // TODO test for get station!
        assertEquals(line1.getStations().size(), 3);
//        assertTrue(line1.hasStation(station2));
        line1.removeStation(station1);
        assertFalse(line1.hasStation(station1));
        line1.addStation(station1);
        // assertTrue(line1.hasStation(station1));
        line1.clearStations();
        assertFalse(line1.hasStation(station1));
        // TODO test addBranches

    }

    @Test
    public void testStation(){
        LatLon loc = new LatLon(10,10);
        Station station1 = new Station("123","Brighouse",loc);
        Line line1 = new Line(LineResourceData.CENTRAL,"123","Canada Line");
        Line line2 = new Line(LineResourceData.NORTHERN,"456","Millenium Line");
        Arrival arrival1 = new Arrival(320,"Downtown","West Bound - Marine Drive");


        assertEquals(station1.getName(), "Brighouse");
        assertEquals(station1.getID(), "123");
        assertEquals(station1.getLocn(),loc);
        assertEquals(station1.getLines(),null);
        assertEquals(station1.getNumArrivalBoards(), 0);
        station1.addLine(line1);
        assertTrue(station1.hasLine(line1));
        station1.removeLine(line1);
        assertFalse(station1.hasLine(line1));
        station1.addArrival(line2, arrival1);
        // have not yet tested if addArrival is doing the correct thing

    }




}