package com.reto03.grupog1.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto03.grupog1.Entities.Score;
import com.reto03.grupog1.Repository.ScoreRepository;

@Service
public class ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    public List<Score> getAll() {
        return (List<Score>) scoreRepository.getAll();
    }

    public Score addScore(Score score) {
        boolean bGrabar = true;

        if(score.getMessageText() == null)
            bGrabar = false;

        if(score.getStars() == null)
            bGrabar = false;

        if(score.getReservation().getIdReservation() == null)
            bGrabar = false;

        if(bGrabar ==true)
            return scoreRepository.addScore(score);
        else
            return score;
    }

    public Score updScore(Score score){
        boolean bGrabar = true;

        if(score.getIdScore() == null || score.getIdScore() == 0)
            bGrabar = false;

        if(bGrabar == true)
            return scoreRepository.updScore(score);
        else
            return score;
    }

    public void delScore(Integer id){
        scoreRepository.delScore(id);
    }

    public Score getScore(Integer id){
        return scoreRepository.getScore(id);
    }
}

