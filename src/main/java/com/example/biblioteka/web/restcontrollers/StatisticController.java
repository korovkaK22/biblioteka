package com.example.biblioteka.web.restcontrollers;

import com.example.biblioteka.entity.Statistic;
import com.example.biblioteka.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @GetMapping
    public List<Statistic> getAllStatistics() {
        return statisticService.findAllStatistics();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Statistic> getStatisticById(@PathVariable Integer id) {
        return statisticService.findStatisticById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Statistic createStatistic(@RequestBody Statistic statistic) {
        return statisticService.saveStatistic(statistic);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatistic(@PathVariable Integer id) {
        statisticService.deleteStatistic(id);
        return ResponseEntity.ok().build();
    }
}
