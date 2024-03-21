package edu.fbansept.demo.models;

import com.fasterxml.jackson.annotation.JsonView;
import edu.fbansept.demo.views.CategoryView;
import edu.fbansept.demo.views.ProductView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(unique = true, length = 50)
    @Length(min= 3, max = 50, message = "Le nom doit avoir entre 3 et 50 caractères")
    @NotNull(message = "Le nom est obligatoire")
    @JsonView(ProductView.class)
    protected String nom;

}
