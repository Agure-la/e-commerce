package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.acme.model.AuthenticationToken;
import org.acme.model.User;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface TokenRepository extends PanacheRepositoryBase<AuthenticationToken, Integer> {

    AuthenticationToken findTokenByUser(User user);
    AuthenticationToken findTokenByToken(String token);

}
