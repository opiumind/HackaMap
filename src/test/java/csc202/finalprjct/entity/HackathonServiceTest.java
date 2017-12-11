package csc202.finalprjct.entity;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Olga Lisovskaya on 12/10/17.
 */
public class HackathonServiceTest {
    @Test
    public void buildGraph() throws Exception {
        HackathonService testSrvice = new HackathonService();
        List<Hackathon> hackathons = testSrvice.getHackathonsFromServer();
        assertEquals("Wrong graph.", "[{'DerbyHacks', startDate='February 24', city='Louisville, KY, United States'}, {'DandyHacks', startDate='February 17', city='Rochester, NY, United States'}, {'BrickHack', startDate='February 11', city='Rochester, NY, United States'}, {'SteelHacks', startDate='February 10', city='Pittsburgh, PA, United States'}, {'ProfHacks 2017', startDate='February 4', city='Glassboro, NJ, United States'}, {'User', startDate='December 1', city='Arlington, VA'}]", testSrvice.buildGraph().getShortestPath(0).getPath(hackathons.size() - 1).toString());
    }

}