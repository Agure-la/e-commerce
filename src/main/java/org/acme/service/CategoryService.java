package org.acme.service;

import org.acme.model.Category;
import org.acme.repository.CategoryRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class CategoryService {

    @Inject
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> listCategories(){
        return categoryRepository.findAll().list();
    }

    public void createCategory(Category category){
        categoryRepository.persist(category);
    }

    public Category readCategory(String categoryName){
        return categoryRepository.findByCategoryName(categoryName);
    }

    public Optional<Category> readCategory(Integer categoryId){
        return categoryRepository.findByIdOptional(categoryId);
    }

    public void updateCategory(Integer categoryId, Category newCategory){
        Category category = categoryRepository.findById(categoryId);
        category.setCategoryName(newCategory.getCategoryName());
        category.setDescription(newCategory.getDescription());
        category.setProducts(newCategory.getProducts());
        category.setImageUrl(newCategory.getImageUrl());

        categoryRepository.persist(category);
    }
}
