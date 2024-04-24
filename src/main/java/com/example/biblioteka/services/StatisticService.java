package com.example.biblioteka.services;

import com.example.biblioteka.dto.StatisticDto;
import com.example.biblioteka.entity.Statistic;
import com.example.biblioteka.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatisticService {

    @Autowired
    private StatisticRepository statisticRepository;

    public List<Statistic> findAllStatistics() {
        return statisticRepository.findAll();
    }

    public Optional<Statistic> findStatisticById(Integer id) {
        return statisticRepository.findById(id);
    }

    public Statistic saveStatistic(Statistic statistic) {
        return statisticRepository.save(statistic);
    }

    public void deleteStatistic(Integer id) {
        statisticRepository.deleteById(id);
    }

    public void addViewsToWritingStatistic(int id){
        Statistic statistic = statisticRepository.findByWritingId(id).get();
        statistic.setViews(statistic.getViews()+1);
        statisticRepository.save(statistic);
    }

}
