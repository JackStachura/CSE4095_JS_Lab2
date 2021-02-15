package com.example.jstachuralab2.controller;

public class Score {
    private static int score;

    public Score(int start_score){
        score = start_score;
    }

    public void correctAnswer(){
        //increase score by 20
        score += 10;
    }
    public void resetScore(){
        score = 0;
    }
    public void skipQuestion(){
        //decrease score by 5
        score -= 5;
    }

    public int getScore(){
        return score;
    }


}
