package com.lastProject.Service;

import com.lastProject.Entity.OrderReport;
import com.lastProject.Repository.OrderReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.List;

@Service
public class OrderReportService {

    @Autowired
    private OrderReportRepository orderReportRepository;

    public List<OrderReport> getReportsByDate(Date date) {
        return orderReportRepository.findAllByOrderDate(date);
    }
    public List<OrderReport> getReportsByMonthAndYear(int month, int year, Model model) {
        List<OrderReport> reports = orderReportRepository.findAllByMonthAndYear(month, year);
        double totalPrice = reports.stream()
                .mapToDouble(OrderReport::getTotalPrice)
                .sum();
        model.addAttribute("totalPrice", totalPrice);

        return reports;
    }

}
