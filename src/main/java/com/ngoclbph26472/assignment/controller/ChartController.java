package com.ngoclbph26472.assignment.controller;

import com.ngoclbph26472.assignment.domail.ReportChartLow;
import com.ngoclbph26472.assignment.domail.ReportChartTop;
import com.ngoclbph26472.assignment.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("/assignment/chart")
public class ChartController {

    @Autowired
    OrdersService ordersService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("")
    public String barGraph(Model model, @RequestParam(value = "top",defaultValue = "Top") String top,@RequestParam(value = "date", defaultValue = "") LocalDate date) {
        if(top.equals("Top")){
            Map<String, Integer> surveyMap = new LinkedHashMap<>();
            for (ReportChartTop chart: ordersService.findAllTop(date)) {
                surveyMap.put(chart.getName(), chart.getQuantity().intValue());
            }
            model.addAttribute("surveyMap", surveyMap);
            return "chart/barGraph";
        }
        Map<String, Integer> surveyMap = new LinkedHashMap<>();
        for (ReportChartLow chart: ordersService.findAllLow()) {
            surveyMap.put(chart.getName(), chart.getQuantity());
        }
        model.addAttribute("surveyMap", surveyMap);
        return "chart/barGraph";
    }
}
