package com.ttn.project2.controller;

import com.ttn.project2.Enum.Status;
import com.ttn.project2.ExceptionHandler.ResourceNotFoundException;
import com.ttn.project2.Model.*;
import com.ttn.project2.repository.CartRepo;
import com.ttn.project2.repository.OrderRepo;
import com.ttn.project2.repository.OrderedProductsRepo;
import com.ttn.project2.repository.ProductVariantRepo;
import com.ttn.project2.requests.CartAdd;
import com.ttn.project2.requests.PartialOrder;
import com.ttn.project2.responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@RequestMapping("/order")
@RestController
public class OrderController {
    @Autowired
    CartRepo cartRepo;

    @Autowired
    ProductVariantRepo productVariantRepo;

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    OrderedProductsRepo orderedProductsRepo;

    //Customer APIs

    // API to order products in cart
    @PostMapping("/all")
    public ResponseEntity<?> create(@RequestBody Map<String, String> param) {
        List<Cart> cartList = cartRepo.findAllByUserID(Integer.parseInt(param.get("userID")));
        if (!cartList.isEmpty()) {
            Orders order = new Orders();
            order.setUserID(Integer.parseInt(param.get("userID")));
            order.setStatus(Status.ORDERED_PLACED);
            orderRepo.save(order);

            for (Cart cart : cartList) {
                ProductVariants productVariants = null;
                try {
                    productVariants = productVariantRepo.getById(cart.getProductVariants().getId());

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
                OrderedProducts orderedProducts = new OrderedProducts();
                orderedProducts.setProducts(productVariants);
                orderedProducts.setQty(cart.getQuantity());
                orderedProducts.setOrder(order);
                orderedProductsRepo.save(orderedProducts);
                cartRepo.delete(cart);
            }
        } else {
            return ResponseEntity.ok(buildResponse("No Products in Cart"));
        }
        return ResponseEntity.ok(buildResponse("Order Placed Successfully"));
    }

    // API to order partial products in cart
    @PostMapping("/partial")
    public ResponseEntity<?> createPartial(@RequestBody PartialOrder partialOrder) {
        System.out.println(partialOrder.getCarts().get(0));
        List<Cart> cartList = new ArrayList<>();
        for (Integer cartID : partialOrder.getCarts()) {
            cartList.add(cartRepo.getById(cartID));
        }
        if (!cartList.isEmpty()) {
            Orders order = new Orders();
            order.setUserID(cartList.get(0).getUserID());
            order.setStatus(Status.ORDERED_PLACED);
            orderRepo.save(order);

            for (Cart cart : cartList) {
                ProductVariants productVariants = null;
                try {
                    productVariants = productVariantRepo.getById(cart.getProductVariants().getId());

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
                OrderedProducts orderedProducts = new OrderedProducts();
                orderedProducts.setProducts(productVariants);
                orderedProducts.setQty(cart.getQuantity());
                orderedProducts.setOrder(order);
                orderedProductsRepo.save(orderedProducts);
                cartRepo.delete(cart);
            }
        } else {
            return ResponseEntity.ok(buildResponse("No Products in Cart"));
        }
        return ResponseEntity.ok(buildResponse("Order Placed Successfully"));
    }

    // API to directly order a product
    @PostMapping("/direct")
    public ResponseEntity<?> createDirect(@RequestBody CartAdd cartAdd) {
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

            if (cartAdd.getQuantity() > productVariants.getQuantityAvailable()) {
                return ResponseEntity.ok("Not enoghut in stock");
            }
        } catch (EntityNotFoundException entityNotFoundException) {
            return ResponseEntity.ok(buildResponse("Product Variation " + productVariants.getName() + " is Invalid"));
        }

        Orders order = new Orders();
        order.setUserID(cartAdd.getUserID());
        order.setStatus(Status.ORDERED_PLACED);
        orderRepo.save(order);

        OrderedProducts orderedProducts = new OrderedProducts();
        orderedProducts.setProducts(productVariants);
        orderedProducts.setQty(cartAdd.getQuantity());
        orderedProducts.setOrder(order);
        orderedProductsRepo.save(orderedProducts);

        productVariants.setQuantityAvailable(productVariants.getQuantityAvailable() - cartAdd.getQuantity());
        productVariantRepo.save(productVariants);

        return ResponseEntity.ok(buildResponse("Order Placed Successfully"));
    }

    // API to cancel an order
    @PutMapping("/cancel/{orderId}")

    public ResponseEntity<?> cancel(@PathVariable Integer orderId) throws ResourceNotFoundException {

        Orders order = orderRepo.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("orderId : " + orderId + " is not found"));


        if (order.getStatus() != Status.ORDERED_PLACED && order.getStatus() != Status.ORDER_CONFIRMED) { //0 for placed and 1 for confirmed
            return ResponseEntity.ok(buildResponse("Not Cancellable"));
        }
        order.setStatus(Status.ORDER_CANCELLED);  // 3 for cancelled
        orderRepo.save(order);
        return ResponseEntity.ok(buildResponse("Order Cancelled successfully"));
    }

    // API to return an order
    @PutMapping("/return/{orderId}")
    public ResponseEntity<?> return0(@PathVariable Integer orderId) throws ResourceNotFoundException {

        Orders order = orderRepo.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("orderId : " + orderId + " is not found"));

        System.out.println(order.getStatus());
        if (order.getStatus() != Status.ORDER_DELIVERED) { //4 for Delivered
            return ResponseEntity.ok(buildResponse("Not Returnable"));
        }
        order.setStatus(Status.ORDER_RETURNED);  // 5 for returned
        orderRepo.save(order);
        return ResponseEntity.ok(buildResponse("Order Return Request successful"));
    }

    // API to view my order
    @GetMapping("/get/{id}")
    public List<Orders> get(@PathVariable Integer id) throws ResourceNotFoundException {

        Orders orders = orderRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("orderId : " + id + " is not found"));

        return this.orderRepo.findAll();
    }


    // API to list all orders
    @GetMapping("/get/all/{userId}")

    public List<Orders> getAll(@PathVariable Integer userId) throws ResourceNotFoundException {

        Orders orders = orderRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("userId : " + userId + " is not found"));

        return this.orderRepo.findAll();
    }

    // Seller APIs

    // API to list all orders
    @GetMapping("/seller/products/{sellerID}")

    public List<OrderedProducts> getAllProducts(@PathVariable Integer sellerID) throws ResourceNotFoundException {

        OrderedProducts orders = orderedProductsRepo.findById(sellerID)
                .orElseThrow(() -> new ResourceNotFoundException("userId : " + sellerID + " is not found"));

        return this.orderedProductsRepo.findAllBySellerID(sellerID);
    }

    // API to change status of an order
    @PutMapping("/seller/update")
    public ResponseEntity<?> updateProduct(@RequestBody Map<String, String> param) {
        Orders order = orderRepo.getById(Integer.valueOf(param.get("orderID")));
        if(order==null) {
            return ResponseEntity.ok(buildResponse("OrderId  does not exists and valid"));
        }

        System.out.println(order.getStatus());
        if (order.getStatus() != Status.valueOf(String.valueOf(param.get("fromStatus")))) {
            return ResponseEntity.ok(buildResponse("Not Valid From Status"));
        }
        order.setStatus(Status.valueOf(String.valueOf(param.get("toStatus"))));
        orderRepo.save(order);
        return ResponseEntity.ok(buildResponse("Order Status Updated successful"));
    }

    // Admin APIs

    // API to list all orders
    @GetMapping("/details")

    public List<Orders> getAllOrders() {
        List<Orders> orders =  orderRepo.findAll();
        return orderRepo.findAll();
    }

    // API to list orders of a particular user
    @GetMapping("/details/{userId}")

    public List<Orders> getAllOrdersByUserId(@PathVariable Integer userId) throws ResourceNotFoundException {
        Orders orders = orderRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("userId : " + userId + " is not found"));
        return this.orderRepo.findAllByUserID(userId);
    }

    // API to change status of an order
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Map<String, String> param) {
        Orders order = orderRepo.getById(Integer.valueOf(param.get("orderID")));
        if(order==null) {
            return ResponseEntity.ok(buildResponse("OrderId  does not exists and valid"));
        }

        System.out.println(order.getStatus());
        if (order.getStatus() != Status.valueOf(String.valueOf(param.get("fromStatus")))) {
            return ResponseEntity.ok(buildResponse("Not Valid From Status"));
        }
        order.setStatus(Status.valueOf(String.valueOf(param.get("toStatus"))));
        orderRepo.save(order);
        return ResponseEntity.ok(buildResponse("Order Status Updated successful"));
    }


    public String buildResponse(String message) {
        Response response = new Response();
        response.setMessage(message);
        return response.getMessage();
    }
}