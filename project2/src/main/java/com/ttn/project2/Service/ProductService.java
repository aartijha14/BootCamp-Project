/*package com.ttn.project2.Service;

import com.ttn.project2.ExceptionHandler.GenericUserValidationFailedException;
import com.ttn.project2.ExceptionHandler.ResourceNotFoundException;
import com.ttn.project2.Model.*;
import com.ttn.project2.repository.CategoryRepository;
import com.ttn.project2.repository.ProductRepository;
import com.ttn.project2.repository.SellerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SellerRepository sellerRepository;



    //Add Product
    public String addProduct(Products product) {

        Optional<Category> productCategory = categoryRepository.findById(product.getCategory().getId());

        // checking if valid category
        if (!productCategory.isPresent())
            throw new ResourceNotFoundException("No category found with id : " + product.getCategory().getId());

        // checking if category is a leaf category
        if (productCategory.get().isLeafCategory())
            throw new GenericUserValidationFailedException("Category associated with id : " + product.getCategory().getId() + " is not a leaf category.");


        productRepository.save(product);
        return "Product added Successfully";

    }


    public ResponseEntity addNewProduct(Product product) {


        Optional<Category> productCategory = categoryRepository.findById(product.getCategory().getId());

        // checking if valid category
        if (!productCategory.isPresent())
            throw new ResourceNotFoundException("No category found with id : " + product.getCategory().getId());

        // checking if category is a leaf category
        if (productCategory.get().isLeafCategory())
            throw new GenericUserValidationFailedException("Category associated with id : " + product.getCategory().getId() + " is not a leaf category.");


        // setting fields
        //Product product = modelMapper.map(co, Product.class);
        product.setId(product.getId());    //TODO : ModelMapper is setting this id as the category id, fix this bug.
        //log.trace("ProductService -> addNewProduct -> Product object : " + product);

        // setting these explicitly because model mapper uses reflection and hence default constructor is not called
       // product.setIsReturnable(co.getIsReturnable() != null ? co.getIsReturnable() : false);
        //product.setIsCancellable(co.getIsCancellable() != null ? co.getIsReturnable() : false);
        product.setReturnable(false);
        product.setCancellable(false);
        product.setActive(false);
        product.setCategory(productCategory.get());

       //Seller productSeller = sellerRepository.findByEmail(sellerEmail);
        //product.setSeller(productSeller);

        // persisting
        productRepository.save(product);

        // sending email to Admin for intimidation
        //emailSenderService.sendEmail(emailSenderService.getAdminNewProductAddedIntimidationEmail(product.toString()));

        // writing to RabbitMQ
        //log.trace("ProductService -> addNewProduct -> writing product to RabbitMQ");
       // rabbitTemplate.convertAndSend("ProjectExchange", "productUpdate", product);

        return new ResponseEntity(HttpStatus.CREATED);
    }



    //Get Product
    public List<Product> viewProduct(@PathVariable long id) throws ResourceNotFoundException {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id : " + id + " is not found"));

        return productRepository.findAll();
    }

    //Delete Product
    public String deleteProduct(@PathVariable long id) {

        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            productRepository.deleteById(id);
            return "Product Deleted Successfully";
        } else
            return "Product Id " + id + " not found ! ";
    }

}*/



