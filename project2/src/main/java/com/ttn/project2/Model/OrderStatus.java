/*package com.ttn.project2.Model;


import com.ttn.project2.Enum.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity

public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private OrderProduct order_product;

    //private enum from_status{};
    //private enum to_status {};

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Status status;

    private String transition_notes_comments;
}*/