package csc202.finalprjct.entity;

import csc202.finalprjct.map.SuperHashMap;
import csc202.finalprjct.map.SuperMap;
import csc202.finalprjct.userComparator.UserChainedComparator;
import csc202.finalprjct.userComparator.UserDobComparator;
import csc202.finalprjct.userComparator.UserGenderComparator;
import csc202.finalprjct.userComparator.UserUsernameComparator;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.util.*;

@Service
public class UserService {
    SuperMap<String, User> users = new SuperHashMap<>();

    @PostConstruct
    public void init() {
        readUsers();
    }

    public void readUsers() {
        try {
            users = UserFileManager.readUsers();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //todo say users about this error
        }
        System.out.println("[HackaMap]Users size: " + users.size());
    }

    public User findByUsername(String username) {
       return users.get(username);
    }

    public void sortAndPrint(SuperMap<String, User> users, Comparator<User>... comparators) {
        List<User> usersList = (List)users.values();


        Collections.sort(usersList, new UserChainedComparator(comparators));

        System.out.println("Sorted by username, gender and date of birth users:\n");

        for (User user : usersList) {
            System.out.println(user);
        }
    }

    public void save(User newUser) {
        users.put(newUser.getUsername(), newUser);
        System.out.println("[HackaMap]user " + users.get(newUser.getUsername()).getUsername() + " saved in the users' map");
        UserFileManager.writeUsers(users);
        readUsers();
    }
}
