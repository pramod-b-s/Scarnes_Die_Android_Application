package com.google.pramodbs.scarnes_die;

import android.app.Application;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.graphics.drawable.BitmapDrawable;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.*;
import java.util.Random;
import android.view.View.OnClickListener;
import java.lang.InterruptedException;
import android.os.Handler;


public class MainActivity extends AppCompatActivity{

    public TextView playertrntxt;
    public TextView playertxt;
    public TextView comptrntxt;
    public TextView comptxt;
    public ImageView img;
    public TextView turn;
    Random rand=new Random();
    public Button roll;
    public Button hold;
    public Button reset;
    public String pscr="Your Score : ";
    public String ptrnscr="Your Turn Score : ";
    public String cscr="Computer Score : ";
    public String ctrnscr="Computer Turn Score : ";
    public String wintxt="You Win !";
    public String cmpwintxt="Android Wins !";
    public String pltrn="Player Turn !";
    public String cmptrn="Computer Turn !";
    int score=0;
    int turnscore=0;
    int cmpscr=0;
    int cmptrnscr=0;
    int value,cmpval;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img=(ImageView)findViewById(R.id.imageView);
        playertrntxt=(TextView)findViewById(R.id.textView3);
        playertxt=(TextView)findViewById(R.id.textView);
        comptrntxt=(TextView)findViewById(R.id.textView4) ;
        comptxt=(TextView)findViewById(R.id.textView2) ;
        turn=(TextView)findViewById(R.id.textView5);
        roll=(Button)findViewById(R.id.button);
        hold=(Button)findViewById(R.id.button2);
        reset=(Button)findViewById(R.id.button3);
        roll.setOnClickListener(changeimage);
        hold.setOnClickListener(holdscore);
        reset.setOnClickListener(resetscore);
    }

        View.OnClickListener changeimage = new OnClickListener() {
            public void onClick(View v) {
                turn.setText(pltrn);
                value = rand.nextInt(6) + 1;
                turnscore+= value;
                switch (value) {
                    case 1:
                        img.setImageResource(R.drawable.dice1);
                        playertrntxt.setText(ptrnscr);
                        turnscore = 0;
                        value = 0;
                        turn.setText(cmptrn);
                        compturn();
                        break;
                    case 2:
                        img.setImageResource(R.drawable.dice2);
                        playertrntxt.setText(ptrnscr + turnscore);
                        break;
                    case 3:
                        img.setImageResource(R.drawable.dice3);
                        playertrntxt.setText(ptrnscr + turnscore);
                        break;
                    case 4:
                        img.setImageResource(R.drawable.dice4);
                        playertrntxt.setText(ptrnscr + turnscore);
                        break;
                    case 5:
                        img.setImageResource(R.drawable.dice5);
                        playertrntxt.setText(ptrnscr + turnscore);
                        break;
                    case 6:
                        img.setImageResource(R.drawable.dice6);
                        playertrntxt.setText(ptrnscr + turnscore);
                        break;
                }
            }
        };

        View.OnClickListener holdscore = new OnClickListener() {
            @Override
            public void onClick(View v) {
                turn.setText(cmptrn);
                score+= turnscore;
                turnscore = 0;
                if(score<100) {
                    playertxt.setText(pscr + score);
                    playertrntxt.setText(ptrnscr);
                    compturn();
                }
                else{
                    playertxt.setText(wintxt);
                    roll.setEnabled(false);
                    hold.setEnabled(false);
                    reset.setEnabled(false);
                    final Handler winhandl=new Handler();
                    final Runnable gap= new Runnable() {
                        public void run() {
                            finish();
                            android.os.Process.killProcess(android.os.Process.myPid());
                            winhandl.postDelayed(this, 0);
                        }
                    };
                    winhandl.postDelayed(gap,5000);
                }
            }
        };

        View.OnClickListener resetscore = new OnClickListener() {
            @Override
            public void onClick(View v) {
                turnscore = 0;
                score = 0;
                playertxt.setText(pscr);
                playertrntxt.setText(ptrnscr);
                comptrntxt.setText(ctrnscr);
                comptxt.setText(cscr);
                turn.setText(pltrn);
            }
        };


    public void compturn(){
        cmptrnscr=0;
        roll.setEnabled(false);
        hold.setEnabled(false);
        reset.setEnabled(false);

        final Handler mHandler = new Handler();

        final Runnable mUpdateTimeTask = new Runnable() {
            public void run(){
                cmpval = rand.nextInt(6) + 1;
                cmptrnscr += cmpval;
                if(cmpval==1){
                    img.setImageResource(R.drawable.dice1);
                    comptrntxt.setText(ctrnscr);
                    roll.setEnabled(true);
                    hold.setEnabled(true);
                    reset.setEnabled(true);
                    turn.setText(pltrn);
                   return;
                }
                if (cmpval == 2) {
                    img.setImageResource(R.drawable.dice2);
                    comptrntxt.setText(ctrnscr + cmptrnscr);
                }
                if (cmpval == 3) {
                    img.setImageResource(R.drawable.dice3);
                    comptrntxt.setText(ctrnscr + cmptrnscr);
                }
                if (cmpval == 4) {
                    img.setImageResource(R.drawable.dice4);
                    comptrntxt.setText(ctrnscr + cmptrnscr);
                }
                if (cmpval == 5) {
                    img.setImageResource(R.drawable.dice5);
                    comptrntxt.setText(ctrnscr + cmptrnscr);
                }
                if (cmpval == 6) {
                    img.setImageResource(R.drawable.dice6);
                    comptrntxt.setText(ctrnscr + cmptrnscr);
                }
                if (cmptrnscr >= 20) {
                    cmpscr += cmptrnscr;
                    comptxt.setText(cscr + cmpscr);
                    turn.setText(pltrn);
                    if(cmpscr>=100){
                        comptxt.setText(cmpwintxt);
                        final Handler winhandler=new Handler();
                        final Runnable gapwin= new Runnable() {
                            public void run() {
                                finish();
                                android.os.Process.killProcess(android.os.Process.myPid());
                            }
                        };
                        winhandler.postDelayed(gapwin,5000);
                    }
                    cmptrnscr=0;
                    comptrntxt.setText(ctrnscr);
                    roll.setEnabled(true);
                    hold.setEnabled(true);
                    reset.setEnabled(true);
                    return;
                }
                mHandler.postDelayed(this, 2000);
            }
        };
        mHandler.postDelayed(mUpdateTimeTask,2000);
    }

}
