package com.ttn.project2.repository;


import com.ttn.project2.Model.OrderedProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderedProductsRepo extends JpaRepository<OrderedProducts, Integer> {
    List<OrderedProducts> findAllBySellerID(Integer sellerID);
}
