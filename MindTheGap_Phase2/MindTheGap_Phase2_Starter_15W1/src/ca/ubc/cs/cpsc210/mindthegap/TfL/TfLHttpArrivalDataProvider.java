package ca.ubc.cs.cpsc210.mindthegap.TfL;

import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;

/**
 * Wrapper for TfL Arrival Data Provider
 */
public class TfLHttpArrivalDataProvider extends AbstractHttpDataProvider {
    private Station stn;

    public TfLHttpArrivalDataProvider(Station stn) {
        super();
        this.stn = stn;
    }

    @Override
    /**
     * Produces URL used to query TfL web service for expected arrivals at
     * station specified in call to constructor.
     *
     * @returns URL to query TfL web service for arrival data
     */
    protected URL getURL() throws MalformedURLException {
        Set<Line> lines = stn.getLines();
        String ids = new String();
        String sep = ",";
        for(Line line : lines){
            ids = ids + line.getId() + sep;
        }

        ids = ids.substring(0,ids.length()-1);

        String request = "https://api.tfl.gov.uk/Line/" +
                ids +
                "/Arrivals?stopPointId=" +
                stn.getID() +
                "&app_id=&app_key=";


        return new URL(request);
    }
}
