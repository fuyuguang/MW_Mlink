package com.jiayou.fyg.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ExplainActivity extends AppCompatActivity {

    private TextView mtextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String desc = getIntent().getExtras().getString("desc","eeor");
        mtextView = (TextView) findViewById(R.id.text_view);
        mtextView.setError(desc);

    }
}