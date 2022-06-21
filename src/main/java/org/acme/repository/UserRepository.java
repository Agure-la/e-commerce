package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.acme.model.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public interface UserRepository extends PanacheRepositoryBase<User, Integer> {

    List<User> findAll();

    User findByEmail(String email);

    User findUserByEmail(String email);
}
