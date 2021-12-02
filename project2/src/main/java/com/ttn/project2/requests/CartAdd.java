package com.ttn.project2.requests;

public class CartAdd {
    private Integer productVariantID;
    private Integer quantity;
    private Integer userID;

    public Integer getProductVariantID() {
        return productVariantID;
    }

    public void setProductVariantID(Integer productVariantID) {
        this.productVariantID = productVariantID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}