package com.example.tick_tac_tou;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //    0 - X
    //    1 - 0
    //    Null  = 2
    boolean gameActive = true;

    int activePlayer = 0;
    int [] gameState = {2,2,2,2,2,2,2,2,2};
    int [] [] winPositions = { {0,1,2},{3,4,5},{6,7,8},
                             {0,3,6 },{1,4,7},{2,5,8},
                             {0,4,8},{2,4,6}
                             };

    public void taptap(View view)
    {
        ImageView img = (ImageView)view;
        int tapedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive)
        {
            gameReset(view);
        }
        if(gameState[tapedImage] == 2 && gameActive)
        {
            gameState[tapedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer==0)
            {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O Turn");
            }
            else
            {
                img.setImageResource(R.drawable.oo);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X Turn");
            }
            img.animate().translationYBy(1000f).setDuration(300);

            for(int [] winpos : winPositions )
            {
                if( ( gameState[winpos[0]] == gameState[winpos[1]] ) &&
                        ( gameState[winpos[1]] == gameState[winpos[2]] )&&
                        (  gameState[winpos[1]] != 2 ) )
                {
                    //someone has won!!
                    gameActive = false;
                    String winnerStr;
                    if(gameState[winpos[0]] == 0) {
                        winnerStr = "X has WON...! ";
                    }
                    else {
                        winnerStr = "O has WON...! ";
                    }

                    TextView status = findViewById(R.id.status);
                    status.setText(winnerStr);

                }

            }


        }
    }

    public void gameReset(View view)
    {
        gameActive = true;
        activePlayer = 0;
        for(int i=0;i<gameState.length;i++) {
            gameState[i] = 2;
        }

        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("Tap to play again");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}