package com.example.jstachuralab2.model;

import com.example.jstachuralab2.R;


public class AllQuestions {

    private int index;

    private Question[] allQuestions = {
            new Question(R.string.q_start, true),
            new Question(R.string.q_seas, false),
            new Question(R.string.q_continent, true),
            new Question(R.string.q_add3_4, true),
            new Question(R.string.q_add5_4, false),
            new Question(R.string.q_add6_2, false),
            new Question(R.string.q_flag, true),
            new Question(R.string.q_connecticut, false)
    };

    public Question getQuestion(int index){
        index = index % allQuestions.length;
        return allQuestions[index];
    }



}
