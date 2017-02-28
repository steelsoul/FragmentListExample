package com.example.alex.fragmentlistexample;

import android.content.res.Configuration;
import android.support.v4.content.res.ConfigurationHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            this.finish();
            return;
        }

        setContentView(R.layout.activity_detailed);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int resId = extras.getInt("image_resid");

            DetailedFragment detailedFragment = (DetailedFragment)
                    getFragmentManager().findFragmentById(R.id.fragment_detailed);

            detailedFragment.setImage(resId);
        }
    }
}
