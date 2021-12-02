package com.ttn.project2.Model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CategoryCo {

    Long parentId;

    @NotNull
    String name;

}

