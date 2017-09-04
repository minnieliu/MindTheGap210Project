package ca.ubc.cs.cpsc210.mindthegap.tests.model;

/**
 * Created by Minnie on 2015-10-30.
 */


import java.util.ArrayList;
import java.util.List;

import ca.ubc.cs.cpsc210.mindthegap.TfL.FileDataProvider;
import ca.ubc.cs.cpsc210.mindthegap.parsers.BranchStringParser;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ParserTest {
    private static final double DELTA = 1.0e-6;   // for testing doubles
    private List<LatLon> latlons;
    private String lineString;
    private LatLon first;


    @Before
    public void setUp() {
        latlons = new ArrayList<LatLon>();
        lineString = new String();
        first = latlons.get(0);
        FileDataProvider source = new FileDataProvider("central_inbound.json");
        try {
            BranchStringParser.parseBranch(lineString);
        }
        catch (JSONException e) {
            System.out.println("JSON data doesn't have expected format.");
            e.printStackTrace();
        }
    }

    @Test
    public void parseLatLon() {
        assertEquals(first.getLatitude(), 0.093493);


    }








}
