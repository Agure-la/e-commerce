package org.acme.service;

import org.acme.model.WishList;
import org.acme.repository.WishListRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class WishListService {

    @Inject
    private WishListRepository wishListRepository;

    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public void createWishList(WishList wishList){
        wishListRepository.persist(wishList);
    }

    public List<WishList> readWishList(Integer userId){
        return wishListRepository.findAllByUserIdOrderByCreatedDateDesc(userId);
    }
}
