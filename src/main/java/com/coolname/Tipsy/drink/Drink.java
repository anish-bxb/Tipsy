package com.coolname.Tipsy.drink;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Type;

import java.util.HashMap;
@Getter
@Entity
@Table
public class Drink {
    @Id
    @SequenceGenerator(name = "drink_sequence",
                        sequenceName = "drink_sequence",
                        allocationSize = 1)
    @GeneratedValue(generator = "drink_sequence", strategy = GenerationType.SEQUENCE)

    private Long id;

    private String name;
    @Column(columnDefinition = "text")
    private String description;
    @Getter
    @Column(columnDefinition = "text")
    private String ingredients;

    public Drink() {
    }

    public Drink(String name, Long id, String description, String ingredients) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.ingredients = ingredients;
    }

    public Drink(String name, String description,String ingredients) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Drink{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", description='" + description + '\'' +
                ", ingredients=" + ingredients;
    }
}
