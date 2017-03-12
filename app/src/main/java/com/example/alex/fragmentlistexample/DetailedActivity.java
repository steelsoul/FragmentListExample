package com.example.alex.fragmentlistexample;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailedActivity extends AppCompatActivity {
    public class Car {
        private String name;
        private int redId;

        public Car(String name, int redId) {
            this.name = name;
            this.redId = redId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getRedId() {
            return redId;
        }

        public void setRedId(int redId) {
            this.redId = redId;
        }
    }

    private class CarAdapter extends BaseAdapter {

        private Context context;
        private ArrayList<Car> cars;

        public CarAdapter(Context context, ArrayList<Car> cars) {
            this.context = context;
            this.cars = cars;
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            TextView textView = new TextView(context);
            View view = convertView;
            if (view == null) {
                view = textView;
                textView.setTextSize(35);
            }

            textView.setText(cars.get(position).getName());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        DetailFragment fragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.fragment_detailed);
                        fragment.setImage(cars.get(position).getRedId());
                    } else {
                        Intent i = new Intent(context, DetailedActivity.class);
                        i.putExtra("image_resid", cars.get(position).getRedId());
                        startActivity(i);
                    }
                }
            });
            return view;
        }
    }

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

            DetailFragment detailFragment = (DetailFragment)
                    getFragmentManager().findFragmentById(R.id.fragment_detailed);

            detailFragment.setImage(resId);
        }
    }
}
