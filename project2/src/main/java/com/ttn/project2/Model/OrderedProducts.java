package com.ttn.project2.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ordered_products")
public class OrderedProducts implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer qty;
    private Integer sellerID;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonIgnore
    @JsonBackReference
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "product_variants_id", nullable = false)
    private ProductVariants productVariants;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getSellerID() {
        return sellerID;
    }

    public void setSellerID(Integer sellerID) {
        this.sellerID = sellerID;
    }

    public ProductVariants getProducts() {
        return productVariants;
    }

    public void setProducts(ProductVariants products) {
        this.productVariants = products;
    }

    public void setOrder(Orders order) {
        this.orders = order;
    }

}