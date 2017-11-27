package csc202.finalprjct.userComparator;

import csc202.finalprjct.entity.User;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class UserChainedComparator implements Comparator<User> {

    private List<Comparator<User>> listComparators;

    @SafeVarargs
    public UserChainedComparator(Comparator<User>... comparators) {
        this.listComparators = Arrays.asList(comparators);
    }

    @Override
    public int compare(User user1, User user2) {
        for (Comparator<User> comparator : listComparators) {
            int result = comparator.compare(user1, user2);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }
}
