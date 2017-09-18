package csc202.finalprjct.entity;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    Map<String, User> users = new HashMap<>();

    @PostConstruct
    public void init() {
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

    public void save(User newUser) {
        users.put(newUser.getUsername(), newUser);
        System.out.println("[HackaMap]user " + users.get(newUser.getUsername()).getUsername() + " saved in the users' map");
        UserFileManager.writeUsers(users);
    }
}
