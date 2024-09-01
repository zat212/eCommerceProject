package com.lastProject.Repository;

import com.lastProject.Entity.OrderReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface OrderReportRepository extends JpaRepository<OrderReport,Integer> {
    @Query("SELECT r FROM OrderReport r WHERE DATE(r.orderDate) = :date")
    List<OrderReport> findAllByOrderDate(Date date);

    @Query("SELECT r FROM OrderReport r WHERE FUNCTION('MONTH', r.orderDate) = :month AND FUNCTION('YEAR', r.orderDate) = :year")
    List<OrderReport> findAllByMonthAndYear(@Param("month") int month, @Param("year") int year);
}
