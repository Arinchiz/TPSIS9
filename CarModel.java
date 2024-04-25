package com.autoshop.model;

import com.autoshop.controller.main.Main;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CarModel implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "carModel", cascade = CascadeType.ALL)
    private List<Automobile> automobiles = new ArrayList<>();

    public CarModel(String name) {
        this.name = name;
    }

    public float getIncomePrice() {
        return Main.round(automobiles.stream().reduce(0f, (i, automobile) -> i + automobile.getIncomePrice(), Float::sum));
    }
}
