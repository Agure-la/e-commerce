package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.acme.model.WishList;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public interface WishListRepository extends PanacheRepositoryBase<WishList, Integer> {

    List<WishList> findAllByUserIdOrderByCreatedDateDesc(Integer userId);
}
