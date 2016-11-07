package com.example.david.firstorlast;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class InitialLoadingScreen extends Activity {
     private final int SPLASH_SCREEN_DELAY = 5000; //amount of time for the splash screen to be delayed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intialloadingscreen);

        //set up textview, font style, and text.
        TextView loading = (TextView)(findViewById(R.id.loading));
        Typeface type = Typeface.createFromAsset(getAssets(), "OnkyIta.ttf");
        loading.setTypeface(type);
        loading.setText("Loading Game...");

        //run splash screen wait screen
        new Handler().postDelayed(new Runnable(){
            public void run(){
                Intent mainIntent = new Intent(InitialLoadingScreen.this, MainActivity.class); //go to main activity
                startActivity(mainIntent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out); //special animation for changing activities
                InitialLoadingScreen.this.finish();
            }
        }, SPLASH_SCREEN_DELAY);

    }
}
