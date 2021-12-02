package com.ttn.project2.Model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode


public class ProductCo {

    // mandatory

    @NotNull
    String name;

    @NotNull
    String brand;

    @NotNull
    Long categoryId;

    // optional

    String description;

    Boolean isCancellable;

    Boolean isReturnable;
}

