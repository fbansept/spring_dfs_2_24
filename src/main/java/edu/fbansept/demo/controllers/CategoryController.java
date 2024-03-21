package edu.fbansept.demo.controllers;

import edu.fbansept.demo.dao.CategoryDao;
import edu.fbansept.demo.models.Category;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

    @Autowired
    CategoryDao categoryDao;

    @GetMapping("/category/list")
    public List<Category> liste() {

        List<Category> categoryList = categoryDao.findAll();

        return categoryList;

    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> get(@PathVariable int id) {

        Optional<Category> optionalCategory =  categoryDao.findById(id);

        if(optionalCategory.isPresent()) {
            return new ResponseEntity<>(optionalCategory.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/category-by-nom/{nom}")
    public ResponseEntity<Category> getByNom(@PathVariable String nom) {

        Optional<Category> optionalCategory =  categoryDao.findByNom(nom);

        if(optionalCategory.isPresent()) {
            return new ResponseEntity<>(optionalCategory.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Category> delete(@PathVariable int id) {

        Optional<Category> optionalCategory =  categoryDao.findById(id);

        if(optionalCategory.isPresent()) {
            categoryDao.deleteById(id);
            return new ResponseEntity<>(optionalCategory.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/category")
    public ResponseEntity<Category> create(@RequestBody @Valid Category category) {
        category.setId(null);
        categoryDao.save(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<Category> update(@RequestBody @Valid Category category, @PathVariable int id) {
        category.setId(id);

        Optional<Category> optionalCategory =  categoryDao.findById(id);

        if(optionalCategory.isPresent()) {
            categoryDao.save(category);
            return new ResponseEntity<>(category, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
