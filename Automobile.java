package com.autoshop.model;

import com.autoshop.controller.main.Main;
import com.autoshop.model.enums.ApplicationStatus;
import com.autoshop.model.enums.EngineType;
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
public class Automobile implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;
    private String photo;
    private float price;
    private String origin;
    private int count;

    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @ManyToOne
    private CarModel carModel;
    @OneToMany(mappedBy = "automobile", cascade = CascadeType.ALL)
    private List<Application> applications = new ArrayList<>();

    public Automobile(String name, String photo, float price, String origin, int count, EngineType engineType, CarModel carModel) {
        this.name = name;
        this.photo = photo;
        this.price = price;
        this.origin = origin;
        this.count = count;
        this.engineType = engineType;
        this.carModel = carModel;
    }

    public void set(String name, float price, String origin, int count, EngineType engineType, CarModel carModel) {
        this.name = name;
        this.photo = photo;
        this.price = price;
        this.origin = origin;
        this.count = count;
        this.engineType = engineType;
        this.carModel = carModel;
    }

    public float getIncomePrice() {
        return Main.round(applications.stream().reduce(0f, (i, application) -> {
            if (application.getStatus() == ApplicationStatus.DONE) return i + application.getPrice();
            return i;
        }, Float::sum));
    }

    public int getIncomeCount() {
        return applications.stream().reduce(0, (i, application) -> {
            if (application.getStatus() == ApplicationStatus.DONE) return i + 1;
            return i;
        }, Integer::sum);
    }
}