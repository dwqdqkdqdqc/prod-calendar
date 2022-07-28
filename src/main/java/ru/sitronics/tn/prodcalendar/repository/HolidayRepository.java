package ru.sitronics.tn.prodcalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sitronics.tn.prodcalendar.model.Holiday;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {
}