package com.ttn.project2.repository;

import com.ttn.project2.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Integer> {
    //    @Query("SELECT o.userID, o.status FROM Orders o WHERE p.user = ?1")
    //    int getOrderDetails(Orders orders);
    List<Orders> findAllByUserID(Integer userID);
    //List<Orders> findAllBySellerID(Integer sellerID);
}