package ca.ubc.cs.cpsc210.mindthegap.parsers;


import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Parser for route strings in TfL line data
 */
public class BranchStringParser {

    /**
     * Parse a branch string obtained from TFL
     *
     * @param branch  branch string
     * @return       list of lat/lon points parsed from branch string
     */
    public static List<LatLon> parseBranch(String branch) throws JSONException {
        List<LatLon> latlons = new LinkedList<LatLon>();
        JSONArray poiJsonArray = new JSONArray("[" + branch + "]");
            for (int i = 0; i < poiJsonArray.length(); i++) {
            String[] suffix = branch.split(" , ");
            Double lat = Double.parseDouble(suffix[0]);
                System.out.println(lat);
            Double lon = Double.parseDouble(suffix[1]);
            LatLon latlon = new LatLon(lat, lon);
            latlons.add(latlon);
        }
        return latlons;
    }

}
