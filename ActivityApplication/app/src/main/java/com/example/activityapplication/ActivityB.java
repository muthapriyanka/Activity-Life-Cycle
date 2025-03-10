package com.example.activityapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityB extends AppCompatActivity {

    private TextView CounterB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        CounterB = findViewById(R.id.textCounterB);
        Button btnFinishB = findViewById(R.id.btnFinishB);

        MyApp app = (MyApp) getApplication();
        int currentVal = app.getThreadCounter();
        app.setThreadCounter(currentVal + 5);

        CounterB.setText("Counter: " + app.getThreadCounter());

        btnFinishB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}