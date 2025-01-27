package ch.cern.todo.controller;


import ch.cern.todo.entity.Category;
import ch.cern.todo.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {
    CategoryService categoryService;

    @GetMapping("{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id){
        return new ResponseEntity<>(this.categoryService.getCategory(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> saveCategory(@RequestBody Category category){
        return new ResponseEntity<>(this.categoryService.saveCategory(category), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id){
        this.categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category){
        Category categoryToUpdate = this.categoryService.getCategory(id);
        if (category.getName() != null) {
            categoryToUpdate.setName(category.getName());
        }

        if (category.getDescription() != null) {
            categoryToUpdate.setDescription(category.getDescription());
        }
        return new ResponseEntity<>(this.categoryService.saveCategory(categoryToUpdate), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getCategories(){
        return new ResponseEntity<>(this.categoryService.getAllCategories(), HttpStatus.OK);
    }
}
