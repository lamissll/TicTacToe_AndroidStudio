package com.giastinchi.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //The initial player = blue = 0
    //The second player = yellow = 1
    int activePlayer = 0;

    //Value 2 means that the slot is free
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    public void dropIn(View view){

        ImageView counter = (ImageView) view;

        int counterTag = Integer.parseInt(counter.getTag().toString());

        if(gameState[counterTag] == 2){

            counter.setTranslationY(-1000f);

            gameState[counterTag] = activePlayer;

            if(activePlayer == 0){

                counter.setImageResource(R.drawable.blue);
                counter.animate().translationYBy(1000f).setDuration(500);

                activePlayer = 1;

            } else{

                counter.setImageResource(R.drawable.yellow);
                counter.animate().translationYBy(1000f).setDuration(500);

                activePlayer = 0;

            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
