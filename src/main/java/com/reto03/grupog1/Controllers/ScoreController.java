package com.reto03.grupog1.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reto03.grupog1.Entities.Score;
import com.reto03.grupog1.Services.ScoreService;

@RestController
@RequestMapping("/api/Scores")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Score> getAll(){
        return (List<Score>) scoreService.getAll();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Score addScore(@RequestBody Score score) {
        return (Score) scoreService.addScore(score);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Score updScore(@RequestBody Score score){
        return (Score) scoreService.updScore(score);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delScore(@PathVariable Integer id){
        scoreService.delScore(id);
    }
    
    @GetMapping ("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Score getScore(@PathVariable Integer id){
        return scoreService.getScore(id);
    }

    
}
