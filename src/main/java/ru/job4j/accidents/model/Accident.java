package ru.job4j.accidents.model;

import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "accidents")
@Getter
@Entity
public class Accident {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String text;

    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private AccidentType type;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "accident_rules",
            joinColumns = { @JoinColumn(name = "accidents_id")},
            inverseJoinColumns = { @JoinColumn(name = "rule_id")}
    )
    private Set<Rule> rules;
}