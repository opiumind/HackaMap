package csc202.finalprjct.entity;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import csc202.finalprjct.additionalServices.RequestService;
import csc202.finalprjct.binarySearchTree.BinarySearchTree;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class HackathonService {
    List<Hackathon> hackathons = new ArrayList<>();
    BinarySearchTree<Hackathon> hackathonsSortedByDistance;

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

    public void getHackathonsDistancesFromServer(String userAddress) {
        StringBuilder origins = new StringBuilder();
        for (Iterator<Hackathon> i = hackathons.iterator(); i.hasNext(); ) {
            origins.append(i.next().city.replaceAll("\\s", "+"));
            if (i.hasNext()) {
                // "%7C" == "|"
                origins.append("%7C");
            }
        }
        String destination = userAddress.replaceAll("\\s", "+");
        String distanceJson = requestService.getJsonAsString("https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=" + origins + "&destinations=" + destination + "&key=AIzaSyDVoJ4r6a3pXUKK1sgkCCPUfMGVYLrUTzU");
        parseDistances(distanceJson);
        setHackathonsSortedByDistanceFromUser();

//        System.out.println("url: " + "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=" + origins + "&destinations=" + destination + "&key=AIzaSyAH8hz-uAi7-5dlgczj3rf-NaX6wBKVrpc");
    }

    private void parseDistances(String json) {
        String[] str1 = json.split("elements");

        for (int i = 1; i < str1.length; i++) {
            Integer distance = null;
            String[] str3 = str1[i].split(":");
            if (str3.length > 4) {
                String str4 = str3[4].trim();
                String str5 = str4.split(" ")[0];
                distance = Integer.valueOf(str5);
            }

            if (distance == null && hackathons.get(i - 1).getCity().isEmpty()) {
                hackathons.get(i - 1).setDistanceFromUser(0);
                return;
            }
            hackathons.get(i - 1).setDistanceFromUser(distance);
        }

//        System.out.println("Check dist " + hackathons.get(1).getDistanceFromUser() + ", " + hackathons.get(3).getCity() + " - " + hackathons.get(3).getDistanceFromUser());
    }

    public List<Hackathon> getHackathonsFromServer() {
        RequestService requestService = new RequestService();
        for (int i = 1; i <= 1; i++) {
            String hackathonsJson = requestService.getJsonAsString("https://www.hackalist.org/api/1.0/2017/" + (i < 10 ? ("0"+i) : i) + ".json");
            hackathons.addAll(parseJsonOfHackathons(hackathonsJson));
        }
        return hackathons;
    }

    private List<Hackathon> parseJsonOfHackathons(String json) {
//        System.out.println(json);
        List<Hackathon> hackathonsList = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

        try {
            // Convert JSON string to Object
//            Map<String, Object> map = mapper.readValue(json, new TypeReference<Map<String,Object>>(){});
            hackathonsList.addAll(Arrays.asList(mapper.readValue(json, Hackathons.class).january));
//            System.out.println("Parsing: " + hackathonsList.get(0).title);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return hackathonsList;
    }
}
