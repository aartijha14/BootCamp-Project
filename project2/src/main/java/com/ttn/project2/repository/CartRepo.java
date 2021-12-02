package com.ttn.project2.repository;


import com.ttn.project2.Model.Cart;
import com.ttn.project2.Model.ProductVariants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByUserID(Integer userID);

    Cart findAllByUserIDAndProductVariants(Integer userID, ProductVariants productVariants);
//    @Query("SELECT SUM(p.amount) FROM Purchase p WHERE p.user = ?1")
//    int findTotal(User user);
}
