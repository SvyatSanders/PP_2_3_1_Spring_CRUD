package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl {

    @Autowired
    private SessionFactory sessionFactory;

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

    @Transactional
    public void saveUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }
}
