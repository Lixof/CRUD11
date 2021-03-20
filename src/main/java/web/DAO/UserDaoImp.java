package web.DAO;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.models.User;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<User> index(){
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    public User show(int id) {
        Query userQuery = sessionFactory.getCurrentSession().createQuery("from User where id = :paramId" );
        userQuery.setParameter("paramId", id);
        List<User> users = userQuery.getResultList();
        return users.get(0); }

    public void save(User user){ sessionFactory.getCurrentSession().save(user); }

    public void update(int id, User updatedUser){
        Query updateQuery = sessionFactory.getCurrentSession().createQuery("update User set name = :nameParam, lastName = :lastNameParam" +
                ", email = :emailParam"+
                " where id = :idParam");
        updateQuery.setParameter("nameParam", updatedUser.getName());
        updateQuery.setParameter("lastNameParam", updatedUser.getLastName());
        updateQuery.setParameter("emailParam", updatedUser.getEmail() );
        updateQuery.setParameter("idParam", id);
        updateQuery.executeUpdate();
    }

    public void delete(int id){
        Query deleteQuery = sessionFactory.getCurrentSession().createQuery("delete User where id = :idParam");
        deleteQuery.setParameter("idParam", id);
        deleteQuery.executeUpdate();
    }


}
