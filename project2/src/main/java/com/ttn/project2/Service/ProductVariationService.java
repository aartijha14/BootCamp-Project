package com.ttn.project2.Service;

import com.ttn.project2.ExceptionHandler.ResourceNotFoundException;
import com.ttn.project2.Model.ProductVariants;
import com.ttn.project2.Model.User;
import com.ttn.project2.repository.CartRepo;
import com.ttn.project2.repository.ProductVariantRepo;
import com.ttn.project2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service

public class ProductVariationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductVariantRepo productVariationRepository;

    @Autowired
    CartRepo cartRepository;



    public List<ProductVariants> viewCart(@PathVariable long id) throws ResourceNotFoundException {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("userId : " + id + " is not found"));

        return this.productVariationRepository.findAll();
    }

    public String addProductVariation(ProductVariants productVariation) {


            productVariationRepository.save(productVariation);

            return "Product Variation Added Successfully ! ";
        }

}
