/*package com.ttn.project2.Service;

import com.ttn.project2.ExceptionHandler.ResourceNotFoundException;
import com.ttn.project2.Model.Cart;
import com.ttn.project2.Model.ProductVariation;
import com.ttn.project2.Model.User;
import com.ttn.project2.repository.CartRepository;
import com.ttn.project2.repository.ProductVariationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class CartService {

    @Autowired
    ProductVariationRepository productVariationRepository;

    @Autowired
    CartRepository cartRepository;

    public String addProduct(Cart cart) {

       Optional<ProductVariation> productVariation1 = productVariationRepository.findById(cart.getId().getProductVariationId());

        if (productVariation1.isPresent()) {


            cartRepository.save(cart);

            return "Product Added Successfully ! ";
       } else {
            return "Variation Id is not valid , Product can not be added ! ";
        }
    }
    }*/


