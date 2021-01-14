package senanayake.udayanga.com.androidsection4connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0; //0 = yellow, 1 = red

    boolean gameIsActive = true;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2}; // 2 mean non played

    int[][] winningPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playAgain(View view) {

        gameIsActive = true;

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
        linearLayout.setVisibility(View.INVISIBLE);

        activePlayer = 0; //0 = yellow, 1 = red

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    public void dropPin(View view) {
        ImageView counter = (ImageView) view;

        int tappedCount = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCount] == 2 && gameIsActive) {
            gameState[tappedCount] = activePlayer;
            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).setDuration(300);

            for (int[] winningPosition : winningPosition) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {
                    System.out.println(gameState[winningPosition[0]]);
                    //Someone has won

                    gameIsActive = false;

                    String winner = "Red";
                    if (gameState[winningPosition[0]] == 0) {
                        winner = "Yellow";
                    }

                    TextView winnerMessage = findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + "is the winner!!!");
                    LinearLayout linearLayout = findViewById(R.id.playAgainLayout);
                    linearLayout.setVisibility(View.VISIBLE);
                }else{

                    boolean gameIsOver = true;

                   for(int counterState: gameState){
                       if(counterState==2){
                            gameIsOver = false;
                       }
                   }
                   if(gameIsOver){
                       TextView winnerMessage = findViewById(R.id.winnerMessage);
                       winnerMessage.setText("Game is draw!!!");
                       LinearLayout linearLayout = findViewById(R.id.playAgainLayout);
                       linearLayout.setVisibility(View.VISIBLE);
                   }
                }
            }
        }
    }
}