package com.example.david.firstorlast;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;

public class PlayGame extends Activity implements View.OnClickListener{

    private TextView testOne;
    private TextView testTwo;
    private TextView inGameTitle;
    private TextView currentStreakText;

    private Button firstButton;
    private Button secondButton;

    private Scanner file;
    private ArrayList<HistoricalEvent> playList;

    private boolean first;
    private boolean second;
    private int currentStreakCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        playList = new ArrayList<HistoricalEvent>();

        //instantiate instance variables
        currentStreakCount = 0;

        //set up textViews & button
        testOne = (TextView)(findViewById(R.id.testTextOne));
        testTwo = (TextView)(findViewById(R.id.testTextTwo));
        inGameTitle = (TextView)(findViewById(R.id.inGameTitle));
        currentStreakText = (TextView)(findViewById(R.id.currentStreak));

        firstButton = (Button)(findViewById(R.id.selectOne));
        firstButton.setOnClickListener(this);
        secondButton = (Button)(findViewById(R.id.selectTwo));
        secondButton.setOnClickListener(this);


        Typeface onky = Typeface.createFromAsset(getAssets(), "OnkyIta.ttf");
        inGameTitle.setTypeface(onky);

        //read from file for historical events. Move to play activity class
        try {
            AssetManager assetManager = getAssets();
            file = new Scanner(assetManager.open("data.txt"));
        }
        catch (IOException e) {
            testOne.setText("ERROR");
        }

        //load the data into ArrayList of HistoricalEvents
        while(file!=null && file.hasNextLine()){
            playList.add(new HistoricalEvent(file.nextInt(), file.nextLine()));
        }

        //shuffle the ArrayList to select random events
        long seed = System.nanoTime();
        Collections.shuffle(playList, new Random(seed));
        startGame();

    }

    public void startGame(){

        Log.d("START GAME","GAME INITIATED");
        currentStreakText.setText("Current streak: "+currentStreakCount);

        if (playList.size()>=2) {
            HistoricalEvent firstEvent = playList.remove(playList.size() - 1);
            HistoricalEvent secondEvent = playList.remove(playList.size() - 1);

            first = false;
            second = false;

            if (firstEvent.compareTo(secondEvent) < 0) {
                first = true;
                second = false;
            } else {
                first = false;
                second = true;
            }

            testOne.setText(firstEvent.getEventName());
            testTwo.setText(secondEvent.getEventName());
        }
        else{
            Log.d("PLAYLIST ERROR", "NO MORE ITEMS IN LIST");
        }
    }

    @Override
    public void onClick(View v) {
        Log.d("CLICKED", "BUTTON CLICKED");
        Intent lost = new Intent(PlayGame.this, LostGame.class);
        lost.putExtra("streak", currentStreakCount);
        int id = v.getId();
        if (id==R.id.selectOne){
            if (first) {
                currentStreakCount++;
                startGame();
            }
            else{
                startActivity(lost);
                PlayGame.this.finish();
            }
        }
        else if (id==R.id.selectTwo){
            if (second){
                currentStreakCount++;
                startGame();
            }
            else{
                startActivity(lost);
                PlayGame.this.finish();
            }
        }
    }
}
