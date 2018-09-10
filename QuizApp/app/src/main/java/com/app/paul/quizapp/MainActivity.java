package com.app.paul.quizapp;

import android.content.Intent;
import android.graphics.Color;
import android.renderscript.RenderScript;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private QuestionRepository questions;
    private List<Question> repository;
    private RecyclerView recyclerQuestions;
    private Button submit;
    private int cont = 0;
    private int total;
    String cad = "";
    private final String MY_QUESTION_ADAPTER = "MY_QUESTION_ADAPTER";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = findViewById(R.id.button_submit);

        questions =  new QuestionRepository();
        repository = questions.createRepo();

        recyclerQuestions = findViewById(R.id.recyclerview_questions);
        recyclerQuestions.setHasFixedSize(true);
        final AdapterRecyclerQuestions adapterRecyclerQuestions = new AdapterRecyclerQuestions(this, repository);
        recyclerQuestions.setAdapter(adapterRecyclerQuestions);
        recyclerQuestions.setLayoutManager(new LinearLayoutManager(this));



        total = 0;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cont == 0) {
                    adapterRecyclerQuestions.reveal();
                    recyclerQuestions.getAdapter().notifyDataSetChanged();
                    //recreate();
                    cont ++;
                    submit.setText("RESTART");
                    for (Question question : repository) {
                        if (question.isCorrect()){
                            total++;
                        }
                    }
                    //Shows the number of right answers
                    Toast.makeText(MainActivity.this, "Right Answers: "+total, Toast.LENGTH_LONG).show();
                }
                else{
                    //Starts a new questionary
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(MY_QUESTION_ADAPTER, recyclerQuestions.getLayoutManager().onSaveInstanceState());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null){
            recyclerQuestions.getLayoutManager().onRestoreInstanceState(savedInstanceState.getParcelable(MY_QUESTION_ADAPTER));
        }
    }
}
