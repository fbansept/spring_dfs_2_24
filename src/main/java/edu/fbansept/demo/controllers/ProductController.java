package edu.fbansept.demo.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import edu.fbansept.demo.dao.ProductDao;
import edu.fbansept.demo.models.Product;
import edu.fbansept.demo.views.ProductView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    ProductDao productDao;

    @GetMapping("/product/list")
    @JsonView(ProductView.class)
    public List<Product> liste() {

        List<Product> productList = productDao.findAll();

        return productList;

    }

    @GetMapping("/product/{id}")
    @JsonView(ProductView.class)
    public ResponseEntity<Product> get(@PathVariable int id) {

        Optional<Product> optionalProduct =  productDao.findById(id);

        if(optionalProduct.isPresent()) {
            return new ResponseEntity<>(optionalProduct.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/product-by-nom/{nom}")
    @JsonView(ProductView.class)
    public ResponseEntity<Product> getByNom(@PathVariable String nom) {

        Optional<Product> optionalProduct =  productDao.findByNom(nom);

        if(optionalProduct.isPresent()) {
            return new ResponseEntity<>(optionalProduct.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/product/{id}")
    @JsonView(ProductView.class)
    public ResponseEntity<Product> delete(@PathVariable int id) {

        Optional<Product> optionalProduct =  productDao.findById(id);

        if(optionalProduct.isPresent()) {
            productDao.deleteById(id);
            return new ResponseEntity<>(optionalProduct.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product")
    @JsonView(ProductView.class)
    public ResponseEntity<Product> create(@RequestBody @Valid Product product) {
        product.setId(null);
        productDao.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/product/{id}")
    @JsonView(ProductView.class)
    public ResponseEntity<Product> update(@RequestBody @Valid Product product, @PathVariable int id) {
        product.setId(id);

        Optional<Product> optionalProduct =  productDao.findById(id);

        if(optionalProduct.isPresent()) {
            productDao.save(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
