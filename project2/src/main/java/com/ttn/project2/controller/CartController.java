package com.ttn.project2.controller;

import com.ttn.project2.Model.Cart;
import com.ttn.project2.Model.ProductVariants;
import com.ttn.project2.repository.CartRepo;
import com.ttn.project2.repository.ProductVariantRepo;
import com.ttn.project2.requests.CartAdd;
import com.ttn.project2.responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

@RequestMapping("/cart")
@RestController
public class CartController {
    @Autowired
    CartRepo cartRepo;

    @Autowired
    ProductVariantRepo productVariantRepo;

    //API to add product in cart

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody CartAdd cartAdd) {
        ProductVariants productVariants = null;
        try {
            productVariants = productVariantRepo.getById(cartAdd.getProductVariantID());
            if (productVariants.getQuantityAvailable() <= 0) {
                return ResponseEntity.ok(buildResponse(productVariants.getName() + " is Stock Out"));
            }

            if (!productVariants.isActive()) {
                return ResponseEntity.ok(buildResponse(productVariants.getName() + " is not available"));
            }

            if (productVariants.getProducts() == null) {
                return ResponseEntity.ok(buildResponse("Product is deleted"));
            }
        } catch (EntityNotFoundException entityNotFoundException) {
            return ResponseEntity.ok(buildResponse("Product Variation is Invalid"));
        }

        Cart cart = cartRepo.findAllByUserIDAndProductVariants(cartAdd.getUserID(), productVariantRepo.getById(cartAdd.getProductVariantID()));
        if (cart != null) {
            cart.setProductVariants(productVariants);
            cart.setQuantity(cartAdd.getQuantity() + cart.getQuantity());
            cart.setUserID(cartAdd.getUserID());
        } else {
            cart = new Cart();
            cart.setProductVariants(productVariants);
            cart.setQuantity(cartAdd.getQuantity());
            cart.setUserID(cartAdd.getUserID());
        }
        cartRepo.save(cart);

        /*productVariants.setStock(productVariants.getStock() - cartAdd.getQuantity());
        productVariantRepo.save(productVariants);*/

        return ResponseEntity.ok(buildResponse(productVariants.getName() + " added to cart successfully"));
    }

    // API to view the cart

    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestBody Map<String, Integer> userID) {
        List<Cart> cartList = cartRepo.findAllByUserID(userID.get("userID"));
        return ResponseEntity.ok(cartList);
    }

    // API to delete product in cart

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProduct(@RequestBody Map<String, Integer> param) {
        ProductVariants productVariants = null;
        try {
            productVariants = productVariantRepo.getById(param.get("productVariantID"));
            productVariants.getName();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.ok(buildResponse("Product Variation ID is Invalid"));
        }

        Cart cart = cartRepo.findAllByUserIDAndProductVariants(param.get("userID"), productVariants);
        if (cart != null) {
            cartRepo.delete(cart);
        } else {
            return ResponseEntity.ok(buildResponse("Product does not exists on cart"));
        }

        return ResponseEntity.ok(buildResponse("Product removed successfully"));
    }

    // API to update product in cart

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody CartAdd cartAdd) {
        ProductVariants productVariants = null;
        try {
            productVariants = productVariantRepo.getById(cartAdd.getProductVariantID());
            if (productVariants.getQuantityAvailable() <= 0) {
                return ResponseEntity.ok(buildResponse(productVariants.getName() + " is Stock Out"));
            }

            if (!productVariants.isActive()) {
                return ResponseEntity.ok(buildResponse(productVariants.getName() + " is not available"));
            }

            if (productVariants.getProducts() == null) {
                return ResponseEntity.ok(buildResponse("Product is deleted"));
            }
        } catch (EntityNotFoundException entityNotFoundException) {
            return ResponseEntity.ok(buildResponse("Product Variation " + productVariants.getName() + " is Invalid"));
        }

        Cart cart = cartRepo.findAllByUserIDAndProductVariants(cartAdd.getUserID(), productVariantRepo.getById(cartAdd.getProductVariantID()));
        if (cart != null) {
            cart.setProductVariants(productVariants);
            cart.setQuantity(cartAdd.getQuantity());
            cart.setUserID(cartAdd.getUserID());
        } else {
            return ResponseEntity.ok(buildResponse("Product does not exists on cart"));
        }
        cartRepo.save(cart);

        return ResponseEntity.ok(buildResponse("Product Quantity updated successfully"));
    }

    // API to empty the cart

    @DeleteMapping("/delete/all")
    public ResponseEntity<?> clearCart(@RequestBody Map<String, Integer> param) {
        List<Cart> cart = cartRepo.findAllByUserID(param.get("userID"));

        if (!cart.isEmpty()) {
            cartRepo.deleteAll(cart);
        } else {
            return ResponseEntity.ok(buildResponse("No Product found on cart for this user"));
        }

        return ResponseEntity.ok(buildResponse("Cart cleared successfully"));
    }


    public String buildResponse(String message) {
        Response response = new Response();
        response.setMessage(message);
        return response.getMessage();
    }
}