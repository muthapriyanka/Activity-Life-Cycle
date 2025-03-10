package com.example.activityapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityC extends AppCompatActivity {
    private TextView CounterC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);

        CounterC = findViewById(R.id.textCounterC);
        Button btnFinishC = findViewById(R.id.btnFinishC);

        MyApp app = (MyApp) getApplication();
        int currentVal = app.getThreadCounter();
        app.setThreadCounter(currentVal + 10);

        CounterC.setText("Counter: " + app.getThreadCounter());

        btnFinishC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
