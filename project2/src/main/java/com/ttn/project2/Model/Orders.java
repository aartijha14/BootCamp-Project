package com.ttn.project2.Model;

import com.ttn.project2.Enum.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "orders")

public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userID;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Status status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orders")
    private Set<OrderedProducts> orderedProductsSet;

    private double amount_paid;

    @CreationTimestamp
    private Date date_created;
    //private String payment_method;
    //private String customer_address_city;
    //private String customer_address_state;
    //private String customer_address_country;
    //private String customer_address_address_line;
    //private String customer_address_zip_code;
    //private String customer_address_label;

}
