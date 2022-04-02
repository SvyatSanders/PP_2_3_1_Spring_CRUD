package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getUsers() {
        String jpql = "SELECT u FROM User u";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        return query.getResultList();
    }

    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Transactional
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Transactional
    public void deleteUser(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }
}
