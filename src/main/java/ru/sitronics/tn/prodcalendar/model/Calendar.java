package ru.sitronics.tn.prodcalendar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@Entity
@Table(name = "calendar")
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String year;

    @ElementCollection
    private List<MonthOfYear> months;

    @JsonIgnore
    @Transient
    private List<String> transitions;

    @JsonIgnore
    @Transient
    private Map<String, Integer> statistic;

    public Calendar(String year, List<MonthOfYear> months) {
        this.year = year;
        this.months = months;
    }
}