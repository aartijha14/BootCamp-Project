/*package com.ttn.project2.Model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Auditable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode

@FieldDefaults(level = AccessLevel.PRIVATE)
    @Entity
    @NoArgsConstructor
    @Table(name = "CATEGORY")
    public class Category  {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        Long id;

        @Column(name = "NAME")
        String name;

        @Column(name = "IS_DELETED")
        Boolean isDeleted;

        @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
        Set<Products> productSet;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "PARENT_ID")
        Category parentCategory;

        @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        Set<Category> subCategoriesSet;

        @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        Set<CategoryMetadataFieldValues> fieldValuesSet;

        public void addFieldValues(CategoryMetadataFieldValues... values) {
            if(values != null) {
                if(fieldValuesSet == null)
                    fieldValuesSet = new HashSet<>();

                for (CategoryMetadataFieldValues value : values) {
                    value.setCategory(this);
                    fieldValuesSet.add(value);
                }
            }
        }

        public void addSubCategory(Category... categories) {
            if(categories != null) {
                if(subCategoriesSet == null)
                    subCategoriesSet = new HashSet<>();

                for (Category category : categories) {
                    category.setParentCategory(this);
                    subCategoriesSet.add(category);
                }

            }
        }

        public void addProduct(Products... products) {
            if (products != null) {
                if (productSet == null)
                    productSet = new HashSet<>();

                for (Products product : products) {
                    if (!productSet.contains(product)) {
                        productSet.add(product);
                        product.setCategory(this);
                    }
                }

            }
        }

        public boolean isLeafCategory() {
            return subCategoriesSet == null;
        }

        public boolean isRootCategory() {
            return parentCategory == null;
        }


    }*/



