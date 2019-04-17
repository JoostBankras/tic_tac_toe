package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Game game;
    int[][] joe = {
            {R.id.button1,R.id.button2,R.id.button3},
            {R.id.button4,R.id.button5,R.id.button6},
            {R.id.button7,R.id.button8,R.id.button9}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null){
            game = (Game) savedInstanceState.getSerializable("game");
            for (int i = 0; i<3;i++){
                for (int j = 0; j<3;j++){
                    Button button = findViewById(joe[i][j]);
                    TileState old = game.OldState(j, i);
                    switch(old) {
                        case CROSS:
                            button.setText("X");
                            break;
                        case CIRCLE:
                            button.setText("O");
                            break;
                        case INVALID:
                            break;
                    }
                }
            }
        }
        else{
            game = new Game();
        }

    }

    public void tileClicked(View view) {
        if (game.won() == GameState.IN_PROGRESS){
            Button button = (Button) view;
            float x_pos = (button).getX();
            float y_pos = (button).getY();

            int x_posi = (int) (x_pos / 263);
            int y_posi = (int) (y_pos / 263);
            TileState state = game.choose(x_posi, y_posi);
            switch(state) {
                case CROSS:
                    button.setText("X");
                    break;
                case CIRCLE:
                    button.setText("O");
                    break;
                case INVALID:
                    break;
            }

        }
    else{
        TextView text1 = findViewById(R.id.TextView);
        text1.setVisibility(view.VISIBLE);
        }

    }

    public void resetClicked(View view) {
        for (int i = 0; i<3;i++){
            for (int j = 0; j<3;j++){
                Button button = findViewById(joe[i][j]);
                button.setText("");
            }
        }

        TextView text1 = findViewById(R.id.TextView);
        text1.setVisibility(view.INVISIBLE);
        game = new Game();

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("game", game);
    }

}
