package ca.ubc.cs.cpsc210.mindthegap.model;

import java.util.*;

/**
 * Represents an arrivals board for a particular station, on a particular line,
 * for trains traveling in a particular direction (as indicated by platform prefix).
 *
 * Invariant: maintains arrivals in order of time to station
 * (first train to arrive will be listed first).
 */
public class ArrivalBoard implements Iterable<Arrival> {
    private List<Arrival> arrivals;


   // Constructs an arrival board for the given line with an empty list of arrivals
   //and given travel direction.

     private Line line;               // line on which arrivals listed on this board operate (cannot be null)
     private String travelDirn;       //travelDirn  the direction of travel

    public ArrivalBoard(Line line, String travelDirn) {
        this.line = line;
        this.travelDirn = travelDirn;
    }

    public Line getLine() {
        return line;
    }

    public String getTravelDirn() {
        return travelDirn;
    }


    /**
     * Get total number of arrivals posted on this arrival board
     *
     * @return  total number of arrivals
     */
    public int getNumArrivals() {
        if (arrivals != null) {
            return arrivals.size();
        }
        return 0;
    }

    /**
     * Add a train arrival this arrivals board.
     *
     * @param arrival  the arrival to add to this arrivals board
     */
    public void addArrival(Arrival arrival) {
        if (arrivals != null) {
            arrivals.add(arrival);
        }
        else arrivals = new ArrayList<Arrival>();
        arrivals.add(arrival);

    }

    /**
     * Clear all arrivals from this arrival board
     */
    public void clearArrivals() {
       arrivals.clear();
    }

    /**
     * Two ArrivalBoards are equal if their lines are equal and travel directions are equal
     */
    @Override
    public boolean equals(Object o) {

        if (o == null) {
            return false;
        }

        if (!(o instanceof ArrivalBoard)) {
            return false;
        }
        ArrivalBoard other = (ArrivalBoard) o;
        if (other.getLine().equals(this.getLine()) &&
                other.getTravelDirn().equals(this.getTravelDirn())) {
            return true;
        }return false;
    }
    /**
     * Two ArrivalBoards are equal if their lines are equal and travel directions are equal
     */
    @Override
    public int hashCode() {
        int hash = 11;
        hash = 7 * hash + line.hashCode();
        hash = hash * travelDirn.hashCode();
        return hash;
    }

    @Override
    /**
     * Produces an iterator over the arrivals on this arrival board
     * ordered by time to arrival (first train to arrive is produced
     * first).
     */
    public Iterator<Arrival> iterator() {
        // Do not modify the implementation of this method!
        return arrivals.iterator();
    }
}
