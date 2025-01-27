package ch.cern.todo.service;

import ch.cern.todo.entity.Category;

import java.util.List;

public interface CategoryService {

    Category getCategory(Long id);

    Category saveCategory(Category category);

    void deleteCategory(Long id);

    List<Category> getAllCategories();
}
