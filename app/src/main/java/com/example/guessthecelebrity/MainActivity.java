package com.example.guessthecelebrity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Game game;
    Button btn1, btn2,btn3,btn4;
    ImageView imageView;
    TextView textViewCorrect,textViewNrOfQuestions;
    GameView gameView;
    Random random;
    int correctAnswer = 0;
    int quantityOfCorrectQuestions = 0;
    int nrOfquestion = 1;


    public void answerClick(View view){
        nrOfquestion++;
        //if good toast correct, if wrong toast wrong
        if(view.getTag().equals(Integer.toString(correctAnswer))){
            Toast.makeText(this,"Dobrze! :)",Toast.LENGTH_SHORT).show();
            quantityOfCorrectQuestions++;
            textViewCorrect.setText("Dobrych odp: " +String.valueOf(quantityOfCorrectQuestions));
        }
        else
            Toast.makeText(this,":( >>" + gameView.getCorrectActorName(),Toast.LENGTH_SHORT).show();
        //another picture load and new names
        gameView = game.nextQuestion();
        textViewNrOfQuestions.setText(Integer.toString(nrOfquestion));
        setView();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.first_button);
        btn2 = findViewById(R.id.second_button);
        btn3 = findViewById(R.id.third_button);
        btn4 = findViewById(R.id.fourth_button);
        imageView = findViewById(R.id.image_view);
        textViewCorrect = findViewById(R.id.text_view_correct);
        textViewNrOfQuestions = findViewById(R.id.text_view_number_of_questions);

        game = new Game();

        textViewCorrect.setText("Dobrych odp: 0");
        textViewNrOfQuestions.setText("1" );

        random = new Random();

        gameView = game.nextQuestion();
        if(gameView!=null)
            setView();
    }


    public void setView(){

        if(gameView != null){
            int i = 0;

            imageView.setImageBitmap(gameView.getImageOfActor());

            correctAnswer = random.nextInt(4) + 1;//1 to 4

            if(btn1.getTag().equals(Integer.toString(correctAnswer))) btn1.setText(gameView.getCorrectActorName());
            else btn1.setText(gameView.getOtherActorsNames()[i++]);
            if(btn2.getTag().equals(Integer.toString(correctAnswer))) btn2.setText(gameView.getCorrectActorName());
            else btn2.setText(gameView.getOtherActorsNames()[i++]);
            if(btn3.getTag().equals(Integer.toString(correctAnswer))) btn3.setText(gameView.getCorrectActorName());
            else btn3.setText(gameView.getOtherActorsNames()[i++]);
            if(btn4.getTag().equals(Integer.toString(correctAnswer))) btn4.setText(gameView.getCorrectActorName());
            else btn4.setText(gameView.getOtherActorsNames()[i++]);

        }
        else
            Toast.makeText(this,"Brak pytań",Toast.LENGTH_SHORT).show();
    }


}
