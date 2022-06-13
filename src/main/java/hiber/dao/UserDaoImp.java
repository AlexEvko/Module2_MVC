package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession()
                    .save(user);
   }

   @Override
   public List<User> getUsers() {
       String hql = "select users from User users";
       TypedQuery<User> query = sessionFactory.getCurrentSession()
                                              .createQuery(hql,User.class);
      return query.getResultList();
   }

   @Override
   public User getCarByUser(String model, Integer series) {
      String hql = "select user from User user where user.car.series =: series and user.car.model =: model";
      TypedQuery<User> query = sessionFactory.getCurrentSession()
                                             .createQuery(hql, User.class);
      query.setParameter("series", series);
      query.setParameter("model", model);
      return query.getSingleResult();

   }
}
