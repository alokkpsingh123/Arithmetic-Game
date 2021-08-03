package com.example.braintranier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;

    ArrayList<Integer> answers =new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView resultTextView;
    int score=0,numberOfQue=0;
    TextView scoreTextView;
    TextView queTextView;
    TextView timerTextView;
    ConstraintLayout gameLayout;

    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;

    public void playAgain(View view){
        score=0;
        numberOfQue=0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQue));
        playAgainButton.setVisibility(View.INVISIBLE);
        newQue();

        resultTextView.setText("");

        CountDownTimer countDownTimer=new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long l) {

                timerTextView.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();

    }

    public void chooseAnswer(View view){
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            resultTextView.setText("Correct!");
            score++;
        }else{
            resultTextView.setText("Wrong :(");
        }
        numberOfQue++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQue));
        newQue();
    }

    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));
    }

    public  void newQue(){
        Random random = new Random();
        queTextView= findViewById(R.id.queTextView);
        int a=random.nextInt(21);
        int b=random.nextInt(21);
        queTextView.setText(Integer.toString(a)+ "+" + Integer.toString(b));

        locationOfCorrectAnswer= random.nextInt(4);
        answers.clear();
        for(int i=0;i<4;i++){
            if(i==locationOfCorrectAnswer) {
                answers.add(a+b);
            }else{
                int wrongAnswer=random.nextInt(41);
                while(wrongAnswer==a+b){
                    wrongAnswer=random.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton= findViewById(R.id.goButton);
        goButton.setVisibility(View.VISIBLE);

        queTextView= findViewById(R.id.queTextView);
        timerTextView=findViewById(R.id.timerTextView);
        gameLayout=findViewById(R.id.gameLayout);


        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        playAgainButton=findViewById(R.id.playAgainButton);


        resultTextView=findViewById(R.id.correctView);
        scoreTextView=findViewById(R.id.scoreTextView);
        gameLayout.setVisibility(View.INVISIBLE);
    }
}