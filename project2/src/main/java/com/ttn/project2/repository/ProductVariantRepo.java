package com.ttn.project2.repository;


import com.ttn.project2.Model.ProductVariants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVariantRepo extends JpaRepository<ProductVariants, Integer> {

//    @Query("SELECT SUM(p.amount) FROM Purchase p WHERE p.user = ?1")
//    int findTotal(User user);
}
