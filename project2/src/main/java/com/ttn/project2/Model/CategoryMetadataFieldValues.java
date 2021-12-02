/*package com.ttn.project2.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity

@Table(name = "CATEGORY_METADATA_FIELD_VALUES")
public class CategoryMetadataFieldValues {

    @EmbeddedId
    CategoryMetadataFieldValuesId id = new CategoryMetadataFieldValuesId();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("categoryMetadataFieldId")
    private CategoryMetadataField categoryMetadataField;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("categoryId")
    private Category category;

    @Column(name = "METADATA_VALUES")
    String values;

    public CategoryMetadataFieldValues(Category category, CategoryMetadataField field, String values) {
        this.category = category;
        this.categoryMetadataField = field;
        this.values = values;
    }


    public class CategoryMetadtaFieldValues implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long category_metadata_field_id;

        @OneToOne
        @JoinColumn(name = "id")
        private CategoryMetadataField category_metadata_field;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long category_id;

        @OneToOne
        @JoinColumn(name = "category_id")
        private Category category;

        private String values[];

    }
}*/

