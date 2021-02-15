package com.example.jstachuralab2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.jstachuralab2.controller.NextQuestion;
import com.example.jstachuralab2.controller.Score;
import com.example.jstachuralab2.model.AllQuestions;
import com.example.jstachuralab2.model.Question;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_INDEX = "GAME_MAIN_ACTIVITY";

    private TextView questionView;
    private TextView scoreView;
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button summaryButton;

    AllQuestions allQuestions = new AllQuestions();
    NextQuestion nextQuestion = new NextQuestion(8);
    Score score = new Score(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        questionView = findViewById(R.id.questionView);
        scoreView = findViewById(R.id.scoreView);

        questionView.setText(R.string.q_start);
        scoreView.setText(String.valueOf(score.getScore()));


        trueButton = findViewById(R.id.t_button);
        falseButton = findViewById(R.id.f_button);
        nextButton = findViewById(R.id.next_button);
        summaryButton = findViewById(R.id.summaryButton);

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = nextQuestion.getCurrentQuestion();

                Question question = null;
                try {
                    question = allQuestions.getQuestion(index);
                } catch (Exception e) {
                    Log.d(TAG_INDEX, "index out of bounds");
                }

                if (question.isQuestionTrue()) {
                    score.correctAnswer();
                    scoreView.setText(String.valueOf(score.getScore()));
                }

                index = nextQuestion.getNextQuestionIndex();
                if (index == -1){
                    SummaryActivity(view);
                }
                else {
                    questionView.setText(allQuestions.getQuestion(index).getQuestionID());
                }
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = nextQuestion.getCurrentQuestion();
                if (index == -1){
                    SummaryActivity(view);
                }
                Question question = null;
                try {
                    question = allQuestions.getQuestion(index);
                } catch (Exception e) {
                    Log.d(TAG_INDEX, "index out of bounds");
                }

                if (!question.isQuestionTrue()) {
                    score.correctAnswer();
                    scoreView.setText(String.valueOf(score.getScore()));

                }

                index = nextQuestion.getNextQuestionIndex();
                if (index == -1){
                    SummaryActivity(view);
                }
                else {
                    questionView.setText(allQuestions.getQuestion(index).getQuestionID());
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = nextQuestion.getCurrentQuestion();
                if (index == -1){
                    SummaryActivity(view);
                }
                Question question = null;
                try {
                    question = allQuestions.getQuestion(index);
                } catch (Exception e) {
                    Log.d(TAG_INDEX, "index out of bounds");
                }

                score.skipQuestion();
                index = nextQuestion.getNextQuestionIndex();
                if (index == -1){
                    SummaryActivity(view);
                }
                else {


                    scoreView.setText(String.valueOf(score.getScore()));

                    questionView.setText(allQuestions.getQuestion(index).getQuestionID());
                }
            }
        });

        summaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SummaryActivity(v);
            }
        });

    }
    void SummaryActivity(View view){
        Intent i = new Intent(MainActivity.this, SummaryActivity.class);
        i.putExtra("score", score.getScore());
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
