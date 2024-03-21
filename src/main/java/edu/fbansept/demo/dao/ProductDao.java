package edu.fbansept.demo.dao;

import edu.fbansept.demo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

    Optional<Product> findByNom(String nomRecherche);

}
