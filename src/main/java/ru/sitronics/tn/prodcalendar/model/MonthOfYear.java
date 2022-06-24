package ru.sitronics.tn.prodcalendar.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Embeddable;

@Data
@Embeddable
@NoArgsConstructor
public class MonthOfYear {
    private int month;
    private String days;
}