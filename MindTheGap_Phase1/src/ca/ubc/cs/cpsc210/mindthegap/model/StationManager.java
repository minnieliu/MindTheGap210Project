package ca.ubc.cs.cpsc210.mindthegap.model;

import ca.ubc.cs.cpsc210.mindthegap.model.exception.StationException;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import ca.ubc.cs.cpsc210.mindthegap.util.SphericalGeometry;
import com.sun.istack.internal.Nullable;


import java.util.*;

/**
 * Manages all tube stations on network.
 *
 * Singleton pattern applied to ensure only a single instance of this class that
 * is globally accessible throughout application.
 */
public class StationManager implements Iterable<Station> {
    public static final int RADIUS = 10000;
    private static StationManager instance;
    private Set<Station> stns;
    private Station selectedstn;
    /**
     * Constructs station manager with empty set of stations and null as the selected station
     */
    private StationManager() {
        this.stns = null;
        this.selectedstn = null;
    }

    /**
     * Gets one and only instance of this class
     *
     * @return  instance of class
     */
    public static StationManager getInstance() {
        // Do not modify the implementation of this method!
        if(instance == null) {
            instance = new StationManager();
        }

        return instance;
    }

    public Station getSelected() {
        return this.selectedstn ;
    }

    /**
     * Get station with given id or null if no such station is found in this manager
     *
     * @param id  the id of this station
     *
     * @return  station with given id or null if no such station is found
     */
    public Station getStationWithId(String id) {
        for (Station s: stns){
            if (s.getID().equals(id)){
                return s;
            }
        } return null;


    }

    /**
     * Set the station selected by user
     *
     * @param selected   station selected by user
     * @throws StationException when station manager doesn't contain selected station
     */
    public void setSelected(Station selected) throws StationException {
        for (Station s : stns) {
            if (s.equals(selected)) {
                this.selectedstn = selected;
            }
            throw new StationException("Station not found");
        }
    }



    /**
     * Clear selected station (selected station is null)
     */
    public void clearSelectedStation() {
        selectedstn = null;
    }

    /**
     * Add all stations on given line. Station added only if it is not already in the collection.
     * @param line  the line from which stations are to be added
     */
    public void addStationsOnLine(Line line) {
          for(Station stn: line.getStations()){
              if (stns.contains(stn)){}
              else stns.add(stn);
          }
    }

    /**
     * Get number of stations managed
     *
     * @return  number of stations added to manager
     */
    public int getNumStations() {
        int counter = 0;
        for (Station s: stns){
            counter ++;
        }
        return counter;
    }

    /**
     * Remove all stations from station manager
     */
    @Nullable
    public void clearStations() {
        if (stns != null)
       stns.clear();
    }

    /**
     * Find nearest station to given point.  Returns null if no station is closer than RADIUS metres.
     *
     * @param pt  point to which nearest station is sought
     * @return    station closest to pt but less than 10,000m away; null if no station is within RADIUS metres of pt
     */
    public Station findNearestTo(LatLon pt) {
        for (Station s:stns){
            double distance = SphericalGeometry.distanceBetween(s.getLocn(),pt);
            double smallest = 10000;
            if (distance < smallest) {
                smallest = distance;
                return s;
            }
            return null;

            } return null;
    }

    @Override
    public Iterator<Station> iterator() {
        // Do not modify the implementation of this method!
        return stns.iterator();
    }
}
