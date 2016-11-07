package com.example.david.firstorlast;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MainActivity extends Activity {

    private Button play;
    private Button instructions;
    private Button about;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up buttons
        play = (Button)(findViewById(R.id.playGameButton));
        instructions = (Button)(findViewById(R.id.instructions));
        about = (Button)(findViewById(R.id.aboutGame));
        title = (TextView)(findViewById(R.id.TitleBar));

        //set font styles
        Typeface flatwheat = Typeface.createFromAsset(getAssets(), "Flatwheat.ttf");
        title.setTypeface(flatwheat);

        //Play Button on click listener. Launches the game UI
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playIntent = new Intent(MainActivity.this, PlayGame.class);
                startActivity(playIntent);
                MainActivity.this.finish();
            }
        });

        //About Button on click listener. Launches about activity
         about.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent aboutIntent = new Intent(MainActivity.this, AboutScreen.class);
                 startActivity(aboutIntent);
                 overridePendingTransition(R.anim.fade_out, R.anim.fade_in); //special animation for changing activities
                 //do not finish this activity from this listener!
             }
         });
    }
}
