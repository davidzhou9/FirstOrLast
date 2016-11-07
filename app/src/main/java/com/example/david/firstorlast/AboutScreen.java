package com.example.david.firstorlast;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutScreen extends Activity {

    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /**********************************************
         TEST FOR GIT BRANCH
        **********************************************/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_screen);

        //Set up back button. Return to main menu
        backButton = (Button)(findViewById(R.id.backButton));
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                AboutScreen.this.finish();
            }
        });
    }
}
