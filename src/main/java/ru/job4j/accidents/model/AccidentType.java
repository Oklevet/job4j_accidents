package ru.job4j.accidents.model;

import lombok.*;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Entity
public class AccidentType {

    @EqualsAndHashCode.Include
    private int id;

    private String name;
}