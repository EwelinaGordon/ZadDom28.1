package pl.javastart.restoffers.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.javastart.restoffers.controller.Category;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryDAO {

    @Autowired
    private CategoryRepository categoryRepository;


    public CategoryDAO() {
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category findByName(String name){
        return categoryRepository.findByName(name);
    }

    public void persist(Category category) {
        categoryRepository.save(category);
    }

    public void remove(long id) {
        Optional<Category> result = categoryRepository.findById(id);
        categoryRepository.delete(result.get());
    }
}
