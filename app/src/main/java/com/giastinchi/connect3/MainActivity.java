package com.giastinchi.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //The initial player = blue = 0
    //The second player = yellow = 1
    int activePlayer = 0;

    boolean isActive = true;

    //Value 2 means that the slot is free
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    //Those are the triples of rows, colums, diagonals who allows the player to win
    int [][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void dropIn(View view){

        ImageView counter = (ImageView) view;

        int counterTag = Integer.parseInt(counter.getTag().toString());

        if(gameState[counterTag] == 2 && isActive){

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

            for(int[]winningPosition : winningPositions){

                if(gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2){

                    isActive = false;

                    String winner = "";
                    //Blue has won
                    if(gameState[winningPosition[0]] == 0){

                        winner = "Blue";

                    }else{

                        winner = "Yellow";

                    }

                    TextView winnerMessage = (TextView)findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " has won!");

                    //The winner has been chosen so displays play again Message
                    LinearLayout linearLayout = (LinearLayout)findViewById(R.id.playAgainLayout);
                    linearLayout.setVisibility(View.VISIBLE);

                }else{

                    boolean gameIsOver = true;

                    for(int counterState : gameState) {

                        if (counterState == 2) gameIsOver = false;

                    }
                    if(gameIsOver){

                            TextView winnerMessage = (TextView)findViewById(R.id.winnerMessage);
                            winnerMessage.setText("It's a Draw");
                            LinearLayout linearLayout = (LinearLayout)findViewById(R.id.playAgainLayout);
                            linearLayout.setVisibility(View.VISIBLE);

                    }


                }
            }
        }
    }

    public void playAgain(View view){

        activePlayer = 0;

        isActive = true;

        for (int i = 0; i < gameState.length; i++){

            gameState[i] = 2;

        }

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.playAgainLayout);
        linearLayout.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++){

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
