package edu.fbansept.demo.dao;

import edu.fbansept.demo.models.Category;
import edu.fbansept.demo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {

    Optional<Category> findByNom(String nomRecherche);

}
