package ca.ubc.cs.cpsc210.mindthegap.model;

import ca.ubc.cs.cpsc210.mindthegap.parsers.BranchStringParser;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import org.json.JSONException;


import java.util.Iterator;
import java.util.List;

/**
 * A branch of a line consisting of a path of lat/lon points.
 * These points are used to draw the branch on a map.  Note that the points used to
 * represent the branch are not necessarily co-located with stations.
 */
public class Branch implements Iterable<LatLon> {
    private List<LatLon> pts;

   // Constructs a Branch by parsing the points that define the branch from
   //the given string.
    private String lineString;  //string of coordinates representing points on branch

    public Branch(String lineString) {
      try {BranchStringParser.parseBranch(lineString);}
      catch (JSONException e){
          System.out.println("JSON data doesn't have expected format.");
          e.printStackTrace();
      }

      }

    /**
     * Get list of all points on this branch
     *
     * @return  all points on branch
     */
    public List<LatLon> getPoints() {
        return pts;
    }

    /**
     * Two branches are equal if their lists of points are equal
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Branch)) {
            return false;
        }
        Branch other = (Branch) o;
        if (this.getPoints().equals(other.getPoints())) {
            return true;
        } else return false;
    }


    /**
     * Two branches are equal if their lists of points are equal
     */
    @Override
    public int hashCode() {
        int hash = 11;
        hash = 7 * hash + pts.hashCode();
        return hash;

    }

    @Override
    public Iterator<LatLon> iterator() {
        // Do not modify the implementation of this method!
        return pts.iterator();
    }
}
