package ru.sitronics.tn.prodcalendar.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "dates")
public class Date {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "day")
    private int day;

    @NotNull
    @Column(name = "month")
    private int month;

    @NotNull
    @Column(name = "year")
    private int year;
}