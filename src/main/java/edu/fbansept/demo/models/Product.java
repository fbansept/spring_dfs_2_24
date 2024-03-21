package edu.fbansept.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import edu.fbansept.demo.views.CategoryView;
import edu.fbansept.demo.views.ProductView;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ProductView.class, CategoryView.class})
    protected Integer id;

    @Column(unique = true, length = 50)
    @Length(min= 3, max = 50, message = "Le nom doit avoir entre 3 et 50 caractères")
    @NotNull(message = "Le nom est obligatoire")
    @JsonView({ProductView.class, CategoryView.class})
    protected String nom;

    @DecimalMin(value = "0", inclusive = false, message = "Le prix doit être positif")
    @JsonView(ProductView.class)
    protected float prix;

    @ManyToOne(optional = false)
    @JsonView(ProductView.class)
    protected Category category;

    @ManyToMany
    @JoinTable(
            name = "product_tag",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @JsonView(ProductView.class)
    protected List<Tag> tagList = new ArrayList<>();

}
