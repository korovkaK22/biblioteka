package com.example.biblioteka.dto;

import com.example.biblioteka.entity.Statistic;
import lombok.*;

@Getter
@Setter
@ToString
public class StatisticDto {

    private Integer id;

    private WritingsDto writing;

    private float averageRating;

    private int views;

    private int commentsAmount;

    public StatisticDto(Statistic statistic) {
        id = statistic.getId();
        writing = new WritingsDto(statistic.getWriting());
        averageRating = statistic.getAverageRating();
        views = statistic.getViews();
        commentsAmount = statistic.getCommentsAmount();
    }

}
