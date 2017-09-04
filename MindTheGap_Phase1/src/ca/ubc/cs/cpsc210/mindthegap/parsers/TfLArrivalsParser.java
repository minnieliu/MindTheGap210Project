package ca.ubc.cs.cpsc210.mindthegap.parsers;

import ca.ubc.cs.cpsc210.mindthegap.model.Arrival;
import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;
import ca.ubc.cs.cpsc210.mindthegap.model.StationManager;
import ca.ubc.cs.cpsc210.mindthegap.parsers.exception.TfLArrivalsDataMissingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;
import org.json.JSONObject;

import java.util.Set;

/**
 * A parser for the data returned by the TfL station arrivals query
 */
public class TfLArrivalsParser extends TfLAbstractParser {

    /**
     * Parse arrivals from JSON response produced by TfL query.  All parsed arrivals are
     * added to given station assuming that corresponding JSON object as all of:
     * timeToStation, platformName, lineID and one of destinationName or towards.  If
     * any of the aforementioned elements is missing, the arrival is not added to the station.
     *
     * @param stn             station to which parsed arrivals are to be added
     * @param jsonResponse    the JSON response produced by TfL
     * @throws JSONException  when JSON response does not have expected format
     * @throws TfLArrivalsDataMissingException  when all arrivals are missing at least one of the following:
     * <ul>
     *     <li>timeToStation</li>
     *     <li>platformName</li>
     *     <li>lineId</li>
     *     <li>destinationName and towards</li>
     * </ul>
     */
    public static void parseArrivals(Station stn, String jsonResponse)
            throws JSONException, TfLArrivalsDataMissingException {
        try{
            JSONTokener source = new JSONTokener(jsonResponse);
           JSONObject rootObj = new JSONObject(source);

            int timeToStation = rootObj.getInt("timeToStation");
            String platformName = rootObj.getString("platformName");
            String lineID = rootObj.getString("lineID");
            String destination = rootObj.getString("destinationName");
            String destination1 = rootObj.getString("towards");

            Arrival newArrival = new Arrival(timeToStation,destination,platformName);
            Arrival newArrival1 = new Arrival(timeToStation,destination1,platformName);

            stn.addArrival(stn.getLinewithID(lineID),newArrival);
            stn.addArrival(stn.getLinewithID(lineID),newArrival1);

        }

        catch(JSONException e){
            System.out.println("JSON data doesn't have expected format.");
            e.printStackTrace();

    }
        throw new TfLArrivalsDataMissingException("Cannot add arrivals because missing data");


    }
}
