package com.ttn.project2.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cart")
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer quantity;
    private Integer userID;

    //@JsonBackReference
    @ManyToOne
    @JoinColumn(name = "product_variants_id", nullable = false)
    private ProductVariants productVariants;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductVariants getProductVariants() {
        return productVariants;
    }

    public void setProductVariants(ProductVariants productVariants) {
        this.productVariants = productVariants;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
