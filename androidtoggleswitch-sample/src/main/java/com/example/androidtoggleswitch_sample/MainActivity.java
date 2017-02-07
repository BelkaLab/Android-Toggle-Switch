package com.example.androidtoggleswitch_sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import belka.us.androidtoggleswitch.widgets.ToggleSwitch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToggleSwitch toggleSwitch = (ToggleSwitch) findViewById(R.id.n_items_toggle_switch);
        ArrayList<String> labels = new ArrayList<>();
        labels.add("A");
        labels.add("B");
        labels.add("C");
        labels.add("D");
        toggleSwitch.setLabels(labels);
    }
}
