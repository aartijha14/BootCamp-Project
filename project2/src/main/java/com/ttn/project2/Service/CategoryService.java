/*package com.ttn.project2.Service;

import com.ttn.project2.ExceptionHandler.ResourceNotFoundException;
import com.ttn.project2.Model.Category;
import com.ttn.project2.Model.CategoryCo;
import com.ttn.project2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public ResponseEntity addNewCategory(CategoryCo co) {

        //checkCategoryNameUniquenessAtTheRootLevelUtil(co.getName());

        Category newCategory = new Category();
        Optional<Category> parentCategory = null;

        if (co.getParentId() != null) {
            parentCategory = categoryRepository.findById(co.getParentId());

           // if (!parentCategory.isPresent())
             //   throw new ResourceNotFoundException("No category found with id : " + co.getParentId());

            // checking name uniqueness within the subtree
            //checkCategoryNameUniquenessWithinSubtreeUtil(co.getName(), parentCategory.get());

            newCategory.setParentCategory(parentCategory.get());
            parentCategory.get().addSubCategory(newCategory);

        }

        newCategory.setName(co.getName());  // since no need to check name uniqueness within the subtree

        categoryRepository.save(newCategory);

        return new ResponseEntity<Long>(newCategory.getId(), null, HttpStatus.CREATED);
    }

}*/
