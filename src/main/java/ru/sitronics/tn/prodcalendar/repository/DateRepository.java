package ru.sitronics.tn.prodcalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sitronics.tn.prodcalendar.model.Date;

@Repository
public interface DateRepository extends JpaRepository<Date, Integer> { }