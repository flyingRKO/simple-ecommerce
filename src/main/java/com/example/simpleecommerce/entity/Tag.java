package com.example.simpleecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "tags")
    private List<Product> products = new ArrayList<>();

    private Tag(String name) {
        this.name = name;
    }

    public static Tag of(String name) {
        return new Tag(name);
    }
}
