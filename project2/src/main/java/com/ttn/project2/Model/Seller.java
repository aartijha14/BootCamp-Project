package com.ttn.project2.Model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode


@Entity
@Table(name = "seller")
@PrimaryKeyJoinColumn(name = "user_id")

public class Seller extends User {

    @Column(name = "gst_no")
    private String gstNo;

    @Column(name = "company_contact")
    private Long companyContact;

    @Column(name = "company_name")
    private String companyName;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "SELLER_PRODUCT", joinColumns = @JoinColumn(name = "SELLER_ID"), inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    Set<Products> productSet;


    public Seller(){

        this.setActive(false);  // will be activated by Admin
        this.setDeleted(false);

        // spring security related fields
       // this.setAccountNonExpired(true);
        //this.setAccountNonLocked(true);
        //this.setCredentialsNonExpired(true);
        //this.setEnabled(true);
    }

    //@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //private UserAddress address;

    /*public void addAddress(UserAddress address)
    {
        if(address != null) {
            if (this.address == null) {
                this.address = address;
                address.setUser(this);
            }

        }
    }*/

    /*public void addProduct(Products... products) {
        if (products != null) {
            if (productSet == null)
                productSet = new HashSet<>();

            for (Products product : products) {
                if (!productSet.contains(product)) {
                    productSet.add(product);
                    product.setSeller(this);

                }
            }
        }
    }*/



}
