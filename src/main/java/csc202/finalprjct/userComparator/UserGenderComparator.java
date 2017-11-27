package csc202.finalprjct.userComparator;

import csc202.finalprjct.entity.User;

import java.util.Comparator;

public class UserGenderComparator implements Comparator<User> {
    @Override
    public int compare(User user1, User user2) {
        return user1.getGender().compareTo(user2.getGender());
    }
}
