/*package com.ttn.project2.Model;

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
@NoArgsConstructor
@Entity

@Table(name = "CATEGORY_METADATA_FIELD")
public class CategoryMetadataField  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "NAME", unique = true, nullable = false)
    String name;

    @OneToMany(mappedBy = "categoryMetadataField", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<CategoryMetadataFieldValues> fieldValuesSet;

    public void addFieldValues(CategoryMetadataFieldValues... values) {
        if(values != null) {
            if(fieldValuesSet == null)
                fieldValuesSet = new HashSet<>();

            for (CategoryMetadataFieldValues value : values) {
                value.setCategoryMetadataField(this);
                fieldValuesSet.add(value);
            }
        }
    }

    public CategoryMetadataField(String name) {
        this.name = name;
    }

}*/

