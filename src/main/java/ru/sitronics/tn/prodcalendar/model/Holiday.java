package ru.sitronics.tn.prodcalendar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "holiday")
public class Holiday {
    @Id
//    @GeneratedValue
//    private UUID id;


    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @SequenceGenerator(name = "seqGen", sequenceName = "holiday_id_seq", allocationSize = 1)
    private long id;


    @NotNull
    private LocalDateTime date;
}