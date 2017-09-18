package csc202.finalprjct.entity;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserFileManager {
    static File usersFile = new File("users.txt");

    public static Map<String,User> readUsers() throws FileNotFoundException {
        String readText;
        Map<String, User> usersMap = new HashMap<>();
        try (Scanner input = new Scanner(usersFile)) {
            readText = input.useDelimiter("\\Z").next();
        }

        String[] usersAsArray = readText.split("\n");
        String[] userAsArray;
        User newUser;
        for (int i = 0; i < usersAsArray.length; i++) {
            userAsArray = usersAsArray[i].split(":", -1);
            newUser = createUser(userAsArray);
            usersMap.put(newUser.getUsername(), newUser);
        }

        return usersMap;
    }

    public static void writeUsers(Map<String,User> users) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        StringBuffer usersAsString = new StringBuffer();

        for (User user:users.values()) {
            usersAsString.append(user.toString());
        }

        try {
            fw = new FileWriter(usersFile.getAbsoluteFile());
            bw = new BufferedWriter(fw);

            bw.write(usersAsString.toString());

            System.out.println("[HackaMap]Users were saved in the file!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

//    Structure of the file:
//    <username>:<email>:<password>:<firstName>:<lastName>:<dob>:<gender>:<phone>:<profilePhotoUrl>:<ssn>
//    Phone structure:
//    <countryCode>-<areaCode>-<phoneNumber>
    public static User createUser(String[] userAsArray) {
        User newUser = new User();
        if (userAsArray[0].length() > 0) {
            newUser.setUsername(userAsArray[0]);
        }
        if (userAsArray[1].length() > 0) {
            newUser.setEmail(userAsArray[1]);
        }
        if (userAsArray[2].length() > 0) {
            newUser.setPassword(userAsArray[2]);
        }
        if (userAsArray[3].length() > 0) {
            newUser.setFirstName(userAsArray[3]);
        }
        if (userAsArray[4].length() > 0) {
            newUser.setLastName(userAsArray[4]);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (userAsArray[5].length() > 0) {
            try {
                newUser.setDob(sdf.parse(userAsArray[5]));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (userAsArray[6].length() > 0) {
            newUser.setGender(Enum.valueOf(Gender.class, userAsArray[6]));
        }
        if (userAsArray[7].length() > 0) {
            String[] phoneAsArray = userAsArray[7].split("-");
            Phone newPhone = new Phone(Integer.parseInt(phoneAsArray[0]),
                    Integer.parseInt(phoneAsArray[1]),Integer.parseInt(phoneAsArray[2]));
            newUser.setPhone(newPhone);
        }
        if (userAsArray[8].length() > 0) {
            newUser.setProfilePhotoUrl(userAsArray[8]);
        }
        if (userAsArray[9].length() > 0) {
            newUser.setSsn(Integer.parseInt(userAsArray[9]));
        }

        return newUser;
    }
}
