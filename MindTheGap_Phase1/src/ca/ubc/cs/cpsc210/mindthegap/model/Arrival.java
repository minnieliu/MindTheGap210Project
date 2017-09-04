package ca.ubc.cs.cpsc210.mindthegap.model;

/**
 * Represents an estimated arrival with time to arrival in seconds,
 * name of destination and platform at which train arrives.  Platform
 * data is assumed to be of the form:
 *    "Travel Direction - Platform Name"
 * with an arbitrary number of spaces either side of "-" and at the
 * start and end of the string.
 */
public class Arrival implements Comparable<Arrival>{
    private int timeToStation;

     //Constructs a new arrival with the given time to station (in seconds),
     //destination and platform.

      // timeToStation  time until train arrives at station (in seconds)
     private String destination;    //name of destination station
     private String platform;       //name of platform at which train will arrive

    public Arrival(int timeToStation, String destination, String platform) {
        this.timeToStation = timeToStation;
        this.destination = destination;
        this.destination = destination;
        this.platform = platform;

    }

    /**
     * Get direction of travel as indicated by platform prefix (part of platform prior to "-" with
     * leading and trailing whitespace trimmed)
     *
     * @return direction of travel
     */
    public String getTravelDirn() {
        String[] suffix = platform.split(" - ");
        return suffix[0];
    }

    /**
     * Get platform name as indicated by platform suffix (part of platform after "-" with leading
     * and trailing whitespace trimmed)
     *
     * @return  platform name
     */
    public String getPlatformName(){

        String[] suffix = platform.split(" - ");
        return suffix[1];
    }

    /**
     * Get time until train arrives at station rounded up to nearest minute.
     *
     * @return  time until train arrives at station in minutes
     */
    public int getTimeToStationInMins() {
      int seconds = timeToStation;
      int minutes = 0;
        while (seconds >60){
            minutes = minutes + 1;
            seconds = seconds - 60;
        }
        return minutes;
    }

    public String getDestination() {
        return this.destination;
    }

    public String getPlatform() {
        return platform;
    }

    /**
     * Order train arrivals by time until train arrives at station
     * (shorter times ordered before longer times)
     */

    @Override
    public int compareTo(Arrival arrival) {
        // Do not modify the implementation of this method!
        return this.timeToStation - arrival.timeToStation;
    }
}
