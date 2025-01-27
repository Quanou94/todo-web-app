package ch.cern.todo.service;

import ch.cern.todo.entity.Category;
import ch.cern.todo.exception.CategoryNotFoundException;
import ch.cern.todo.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Override
    public Category getCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        return unwrapCategory(category, id);
    }

    @Override
    public Category saveCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        this.categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return (List<Category>) this.categoryRepository.findAll();
    }

    static Category unwrapCategory(Optional<Category> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new CategoryNotFoundException(id);
    }

}
