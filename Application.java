package com.autoshop.model;

import com.autoshop.model.enums.ApplicationStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Application implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private float price;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status = ApplicationStatus.WAITING;

    @ManyToOne
    private Automobile automobile;
    @ManyToOne
    private AppUser owner;

    public Application(Automobile automobile, AppUser owner) {
        this.price = automobile.getPrice();
        this.automobile = automobile;
        this.owner = owner;
    }
}
