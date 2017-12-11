package csc202.finalprjct.entity;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import csc202.finalprjct.additionalServices.RequestService;
import csc202.finalprjct.binarySearchTree.BinarySearchTree;
import csc202.finalprjct.graph.WeightedEdge;
import csc202.finalprjct.graph.WeightedGraph;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Service
public class HackathonService {
    List<Hackathon> hackathons = new ArrayList<>();
    BinarySearchTree<Hackathon> hackathonsSortedByDistance;
    List<Hackathon> nonstopTourList;

    private RequestService requestService = new RequestService();

    public List<Hackathon> getHackathons() {
        return hackathons;
    }

    public BinarySearchTree<Hackathon> getHackathonsSortedByDistance() {
        return hackathonsSortedByDistance;
    }

    private void setHackathonsSortedByDistanceFromUser() {
        hackathonsSortedByDistance = new BinarySearchTree<Hackathon>(hackathons.toArray(new Hackathon[]{}));
    }

    /** Gets distances between the specified location and all hackathons
     *
     * @param sourceAddress
     */
    public List<Integer> getDistancesFromServer(String sourceAddress, List<Hackathon> hackathons) {
        StringBuilder origins = new StringBuilder();
        for (Iterator<Hackathon> i = hackathons.iterator(); i.hasNext(); ) {
            origins.append(i.next().city.replaceAll("\\s", "+"));
            if (i.hasNext()) {
                // "%7C" == "|"
                origins.append("%7C");
            }
        }
        String destination = sourceAddress.replaceAll("\\s", "+");
        String distanceJson = requestService.getJsonAsString("https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=" + origins + "&destinations=" + destination + "&key=AIzaSyDVoJ4r6a3pXUKK1sgkCCPUfMGVYLrUTzU");

        return parseDistances(distanceJson);

//        System.out.println("url: " + "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=" + origins + "&destinations=" + destination + "&key=AIzaSyAH8hz-uAi7-5dlgczj3rf-NaX6wBKVrpc");
    }

    /** Sets distance from user to all hackathons
     *
     * @param sourceAddress user address
     */
    public void setDistancesFromUserToHackathons(String sourceAddress) {
        List<Integer> distances = getDistancesFromServer(sourceAddress, hackathons);
        for (int i = 0; i < hackathons.size(); i++) {
            if (distances.get(i) == null && hackathons.get(i).getCity().isEmpty()) {
                hackathons.get(i).setDistanceFromUser(0);
                return;
            }
            hackathons.get(i).setDistanceFromUser(distances.get(i));
        }
        setHackathonsSortedByDistanceFromUser();
    }

    /** Sets distances to all hackathons
     *
     * @param json a json string with distance value
     */
    private List<Integer> parseDistances(String json) {
        String[] str1 = json.split("elements");
        List<Integer> distances = new ArrayList<>();

        for (int i = 1; i < str1.length; i++) {
            Integer distance = null;
            String[] str3 = str1[i].split(":");
            if (str3.length > 4) {
                String str4 = str3[4].trim();
                String str5 = str4.split(" ")[0];
                distance = Integer.valueOf(str5);
            }
            distances.add(distance);


        }
        return distances;

//        System.out.println("Check dist " + hackathons.get(1).getDistanceFromUser() + ", " + hackathons.get(3).getCity() + " - " + hackathons.get(3).getDistanceFromUser());
    }

    /** Gets hackathons from server
     * @return List of hackathons
     */
    public List<Hackathon> getHackathonsFromServer() {
        RequestService requestService = new RequestService();
        hackathons = new ArrayList<>();
        for (int i = 2; i <= 2; i++) {
            String hackathonsJson = requestService.getJsonAsString("https://www.hackalist.org/api/1.0/2017/" + (i < 10 ? ("0"+i) : i) + ".json");
            hackathons.addAll(parseJsonOfHackathons(hackathonsJson));
        }
        return hackathons;
    }


     /** Gets hackathons from json.
      * @param json a json string with data about hackathons
      @return List of hackathons
     */
    private List<Hackathon> parseJsonOfHackathons(String json) {
        List<Hackathon> hackathonsList = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

        try {
            // Convert JSON string to Object
//            Map<String, Object> map = mapper.readValue(json, new TypeReference<Map<String,Object>>(){});
            hackathonsList.addAll(Arrays.asList(mapper.readValue(json, Hackathons.class).month));

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return hackathonsList;
    }

    public List<Hackathon> getOfflineHackathons() {
        List<Hackathon> offlineHackathons = new ArrayList<>();
        for (Hackathon hackathon : getHackathons()) {
            if (!hackathon.getCity().equals("Online")) {
                offlineHackathons.add(hackathon);
            }
        }
        return offlineHackathons;
    }


    /** Build a graph from all hackathons with weight as distance between hackathons
     *  @return Weighted Graph of hackathons
     */
    public WeightedGraph<Hackathon> buildGraph() {
        List<WeightedEdge> edges = new ArrayList<>();
        List<Hackathon> hackathons = getOfflineHackathons();
        Hackathon user = new Hackathon();
        user.setCity("Arlington, VA");
        user.setStartDate("December 1");
        user.setYear("2016");
        user.setTitle("My Location");
        hackathons.add(0, user);
        for (int i = 0; i < hackathons.size(); i++) {
            String currentAddress = hackathons.get(i).getCity();
            LocalDate currentStartDate = hackathons.get(i).getStartDateValue();
            LocalDate nextStartDate = null;
            List<Hackathon> nextDateHackathons = new ArrayList<>();
            List<Integer> indexesOfNextDateHackathons = new ArrayList<>();
            for (int j = i + 1; j < hackathons.size(); j++) {
                if (i > 11) System.out.println("#####!" + hackathons.get(j).getCity());
                if (nextStartDate == null
                        || nextStartDate.isEqual(hackathons.get(i).getStartDateValue())
                        || nextStartDate.isEqual(hackathons.get(j).getStartDateValue())) {
                    nextStartDate = hackathons.get(j).getStartDateValue();
                    if (currentStartDate.isBefore(nextStartDate)) {
                        indexesOfNextDateHackathons.add(j);
                        nextDateHackathons.add(hackathons.get(j));
                    }
                } else {
                    break;
                }
            }
            if (!nextDateHackathons.isEmpty()) {
                List<Integer> distancesToNextHackathons = getDistancesFromServer(currentAddress, nextDateHackathons);
                for (int k = 0; k < indexesOfNextDateHackathons.size(); k++) {
                    int indexOfNextHackathon = indexesOfNextDateHackathons.get(k);

                    double distance = Double.valueOf(distancesToNextHackathons.get(k));
                    System.out.println("!!$!$!$!indexOfNext: " + indexOfNextHackathon + ", Distance: " + distance + ", city: " + hackathons.get(indexOfNextHackathon).getCity());

                    edges.add(new WeightedEdge(i, indexOfNextHackathon, distance));
                }
            }
        }

        return new WeightedGraph<>(hackathons, edges);
    }

    public void createNonstopTourList() {
        getHackathonsFromServer();
        WeightedGraph<Hackathon> hackathonsGraph = buildGraph();
        nonstopTourList = new ArrayList<>();
        nonstopTourList.addAll(hackathonsGraph.getShortestPath(0).getPath(this.hackathons.size() - 1));
        Collections.reverse(nonstopTourList);
    }

    public List<Hackathon> getNonstopTourList() {
        return nonstopTourList;
    }
}
