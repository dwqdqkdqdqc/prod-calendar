package ru.sitronics.tn.prodcalendar.model;

import lombok.*;
import org.hibernate.Hibernate;
import ru.sitronics.tn.prodcalendar.model.base.BaseLongId;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(callSuper = true)
@Builder
@Entity
@Table(name = "holiday")
public class Holiday extends BaseLongId {
    @NotNull
    @Column(name = "date")
    private LocalDateTime date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Holiday holiday = (Holiday) o;
        return super.equals(o) && Objects.equals(date, holiday.getDate());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}