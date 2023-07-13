package com.projet.clubpage.repository;
import com.projet.clubpage.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager entityManager;

    public Optional<User> findOneByEmail(String email) {
        return entityManager.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList()
                .stream().findAny();
    }

    public User save(User user) {
        if (user.getIdx() == null) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
        return user;
    }
}
