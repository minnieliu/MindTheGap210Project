package ca.ubc.cs.cpsc210.mindthegap.model;

import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;

import java.util.*;

/**
 * Represents a station on the underground with an id, name, location (lat/lon)
 * set of lines with stops at this station and a list of arrival boards.
 */
public class Station implements Iterable<ArrivalBoard> {
    private List<ArrivalBoard> arrivalBoards;
    // private ArrivalBoard arrivalBoard;

    // Constructs a station with given id, name and location.
    // Set of lines and list of arrival boards are empty.

     private String id;     // the id of this station (cannot by null)
     private String name;   // name of this station
     private LatLon locn;   // location of this station
     private Set<Line> lines; // set of lines in this station

    public Station(String id, String name, LatLon locn) {
        this.id = id;
        this.name = name;
        this.locn = locn;
    }

    public String getName() {
        return this.name;
    }

    public LatLon getLocn() {
        return this.locn;
    }

    public String getID() {
        return this.id;
    }

    public Set<Line> getLines() {
        if (lines != null){
            return lines;
        }
        return null;
    }

    public int getNumArrivalBoards() {
        if (arrivalBoards != null) {
            return arrivalBoards.size();
        } else return 0;
    }


    /**
     * Add line to set of lines with stops at this station.
     *
     * @param line  the line to add
     */
    public void addLine(Line line) {
        if (lines != null){
                lines.add(line);
            line.setStation(this);
        }
            lines = new HashSet<Line>();
        lines.add(line);
        line.setStation(this);


    }

    /**
     * Remove line from set of lines with stops at this station
     *
     * @param line the line to remove
     */
    public void removeLine(Line line) {
       lines.remove(line);
    }

    /**
     * Determine if this station is on a given line
     * @param line  the line
     * @return  true if this station is on given line
     */
    public boolean hasLine(Line line) {
      if(lines.contains(line)){
          return true;
      }
        else return false;
    }

    /**
     * Add train arrival travelling on a particular line in a particular direction to this station.
     * Arrival is added to corresponding arrival board based on the line on which it is
     * operating and the direction of travel (as indicated by platform prefix).  If the arrival
     * board for given line and travel direction does not exist, it is created and added to
     * arrival boards for this station.
     *
     * @param line    line on which train is travelling
     * @param arrival the train arrival to add to station
     */
    public void addArrival(Line line, Arrival arrival) {

        ArrivalBoard ab = new ArrivalBoard(line, arrival.getTravelDirn());
        boolean exists = false;
        for (ArrivalBoard arrivalBoard: arrivalBoards) {
            if (arrivalBoard.getTravelDirn().equals(arrival.getTravelDirn()) &&
                    arrivalBoard.getLine().equals(line)) {
                arrivalBoard.addArrival(arrival);
                ab = null;
                exists = true;
            }
        }
        if (!exists) {
            arrivalBoards.add(ab);
            ab.addArrival(arrival);
        }
    }


    /**
     * Remove all arrival boards from this station
     */
    public void clearArrivalBoards() {
        arrivalBoards.clear();

    }

    public Line getLinewithID(String id){
        for (Line l: lines){
            if (l.getId().equals(id)){
                return l;
            }
            return null;
        }return null;
    }

    /**
     * Two stations are equal if their ids are equal
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof ArrivalBoard)) {
            return false;
        }

        Station other = (Station) o;
        if (this.getID().equals(other.getID())){
            return true;
        }
        else return false;
    }

    /**
     * Two stations are equal if their ids are equal
     */
    @Override
    public int hashCode() {
      int hash = 11;
        hash = hash * 7 + this.getID().hashCode();
        return hash;
    }

    @Override
    public Iterator<ArrivalBoard> iterator() {
        // Do not modify the implementation of this method!
        return arrivalBoards.iterator();
    }
}
