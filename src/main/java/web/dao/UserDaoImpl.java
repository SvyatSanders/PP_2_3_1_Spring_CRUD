package web.dao;

import org.springframework.stereotype.Component;
import web.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoImpl {
    private final List<User> users = new ArrayList<>();

    {
        users.add(new User("Andy", "Yorhol", 45, "male"));
        users.get(0).setId(1);
        users.add(new User("Cate", "Bishop", 23, "female"));
        users.get(1).setId(2);
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUserById(int id) {
        return users.stream().filter(x -> x.getId() == id).findAny().orElse(null);
    }

    public void saveUser(User user) {
        users.add(user);
    }
}
