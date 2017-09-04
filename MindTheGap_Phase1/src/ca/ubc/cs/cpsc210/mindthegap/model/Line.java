package ca.ubc.cs.cpsc210.mindthegap.model;

import java.util.*;

/**
 * Represents a line on the underground with a name, id, list of stations and list of branches.
 *
 * Invariants:
 * - no duplicates in list of stations
 * - iterator iterates over stations in the order in which they were added to the line
 */
public class Line implements Iterable<Station> {
    private List<Station> stns;
    public Station stn;

    //Constructs a line with given resource data, id and name.
     //List of stations and list of branches are empty.
     private LineResourceData lmd ;    //the line meta-data
     private String id;                //the line id
     private String name;              //the name of the line
     private Set<Branch> branches;    // the list of branches

    public Line(LineResourceData lmd, String id, String name) {
        this.lmd = lmd;
        this.id = id;
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    /**
     * Get colour specified by line resource data
     *
     * @return  colour in which to plot this line
     */
    public int getColour() {
        return this.lmd.getColour();
    }



    public void setStation(Station theStation){
        if (stn != theStation){
            stn = theStation;
            stn.addLine(this);
        }
    }


    /**
     * Add station to line.
     *
     * @param stn  the station to add to this line
     */
    public void addStation(Station stn) {
        if (stns != null && !stns.contains(stn)) {
                stns.add(stn);
            }
        else if (stns == null){
            stns = new LinkedList<Station>();
             stns.add(stn);
    }
    }
    /**
     * Remove station from line
     *
     * @param stn  the station to remove from this line
     */
    public void removeStation(Station stn) {
        stns.remove(stn);
    }

    /**
     * Clear all stations from this line
     */
    public void clearStations() {
        stns.clear();
    }

    public List<Station> getStations() {
        if (stns != null) {

            return stns;
        } else return null;
    }

    /**
     * Determines if this line has a stop at a given station
     *
     * @param stn  the station
     * @return  true if line has a stop at given station
     */
    public boolean hasStation(Station stn) {
        if (stns != null){
            if (stns.contains(stn)){
                return true;
            }return false;
        } return false;
    }

    /**
     * Add a branch to this line
     *
     * @param b  the branch to add to line
     */
    public void addBranch(Branch b) {
        branches.add(b);
    }

    public Set<Branch> getBranches() {
        return branches;
    }

    /**
     * Two lines are equal if their ids are equal
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof ArrivalBoard)) {
            return false;
        }
        Line other = (Line) o;
        if (this.getId().equals(other.getId())) {
            return true;
        } else return false;
    }

    /**
     * Two lines are equal if their ids are equal
     */
    @Override
    public int hashCode() {
        int hash = 11;
                hash = hash * 7 + this.getId().hashCode();
        return hash;
    }

    @Override
    public Iterator<Station> iterator() {
        // Do not modify the implementation of this method!
        return stns.iterator();
    }
}
