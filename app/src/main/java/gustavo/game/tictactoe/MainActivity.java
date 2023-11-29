package gustavo.game.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.view.View;
import android.view.View.OnClickListener;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import gustavo.game.tictactoe.R;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    private Button gameGrid[][]=new Button[3][3];
    private Button newGameButton;
    private TextView messageTextView;

    private int turn;
    private String message;
    private boolean gameOver;
    private String gameString;

    MediaPlayer mp;
    MediaPlayer newG;
    MediaPlayer click;
    MediaPlayer inv;
    MediaPlayer games;
    MediaPlayer tie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp=MediaPlayer.create(this, R.raw.congratulations);
        newG=MediaPlayer.create(this, R.raw.newg);
        click=MediaPlayer.create(this, R.raw.click);
        inv=MediaPlayer.create(this, R.raw.invalids);
        games=MediaPlayer.create(this, R.raw.games);
        tie=MediaPlayer.create(this, R.raw.tie);

        gameGrid[0][0]=(Button) findViewById(R.id.square1);
        gameGrid[0][1]=(Button) findViewById(R.id.square2);
        gameGrid[0][2]=(Button) findViewById(R.id.square3);
        gameGrid[1][0]=(Button) findViewById(R.id.square4);
        gameGrid[1][1]=(Button) findViewById(R.id.square5);
        gameGrid[1][2]=(Button) findViewById(R.id.square6);
        gameGrid[2][0]=(Button) findViewById(R.id.square7);
        gameGrid[2][1]=(Button) findViewById(R.id.square8);
        gameGrid[2][2]=(Button) findViewById(R.id.square9);

        newGameButton=(Button) findViewById(R.id.newGameButton);
        messageTextView=(TextView) findViewById(R.id.messageTextView);

        //event handlers
        for(int x=0; x<gameGrid.length; x++)
        {
            for(int y=0; y< gameGrid[x].length; y++)
            {
                gameGrid[x][y].setOnClickListener(this);
            }
        }

        newGameButton.setOnClickListener(this);

        //clear
        for(int x=0; x<gameGrid.length; x++)
        {
            for(int y=0; y< gameGrid[x].length; y++)
            {
                gameGrid[x][y].setText(" ");
                games.start();

            }
        }

        //start values
        turn=1;
        gameOver=false;
        message="Player X's turn";
        messageTextView.setText(message);
        gameString="        ";

    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.newGameButton:
                //clear;
                for(int x=0; x<gameGrid.length; x++)
                {
                    for(int y=0; y< gameGrid[x].length; y++)
                    {
                        gameGrid[x][y].setText(" ");
                        newG.start();
                    }
                }
                turn=1;
                gameOver=false;
                message="Player X's turn";
                messageTextView.setText(message);
                gameString="         ";
                break;
            default:
                if(!gameOver)
                {
                    Button b=(Button) v;
                    if(b.getText().equals(" ")) {
                        if (turn % 2 != 0) {
                            b.setText("X");
                            click.start();
                            message="Player O's turn";
                        }
                        else
                        {
                            b.setText("O");
                            click.start();
                            message="Player X's turn";
                        }
                        turn++;
                        checkforGameOver();
                    }
                    else{
                        inv.start();
                        message="That square is taken. Try again.";}
                }
        }
        messageTextView.setText(message);
    }

    private void checkforGameOver()
    {
        //rows
        for(int x=0; x<3;x++)
        {
            if(!gameGrid[x][0].getText().equals(" ")&&
                    gameGrid[x][0].getText().equals(gameGrid[x][1].getText())&&
                    gameGrid[x][1].getText().equals(gameGrid[x][2].getText())
            )
            {
                message=gameGrid[x][0].getText()+" wins";
                mp.start();
                gameOver=true;
                return;
            }
        }

        //columns
        for(int y=0; y<3;y++)
        {
            if(!gameGrid[0][y].getText().equals(" ")&&
                    gameGrid[0][y].getText().equals(gameGrid[1][y].getText())&&
                    gameGrid[1][y].getText().equals(gameGrid[2][y].getText())
            )
            {
                message=gameGrid[0][y].getText()+" wins";
                mp.start();
                gameOver=true;
                return;
            }
        }

        //diagonal 1
        if(!gameGrid[0][0].getText().equals(" ")&&
                gameGrid[0][0].getText().equals(gameGrid[1][1].getText())&&
                gameGrid[1][1].getText().equals(gameGrid[2][2].getText())
        )
        {
            message=gameGrid[0][0].getText()+" wins";
            mp.start();
            gameOver=true;
            return;
        }
        //diagonal 2
        if(!gameGrid[2][0].getText().equals(" ")&&
                gameGrid[2][0].getText().equals(gameGrid[1][1].getText())&&
                gameGrid[0][2].getText().equals(gameGrid[1][1].getText())
        )
        {
            message=gameGrid[2][0].getText()+" wins";
            mp.start();
            gameOver=true;
            return;
        }

        //

        if(turn>9)
        {
            message="It is a tie";
            tie.start();
            gameOver=true;
            return;
        }
        gameOver=false;
    }
}
