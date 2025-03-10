package com.example.activityapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView Counter;
    private TextView OnRestartCounter;

    private Thread backgroundThread;
    private volatile boolean isRunning = true;

    private int RestartCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Counter = findViewById(R.id.textCounter);
        OnRestartCounter = findViewById(R.id.textOnRestartCount);

        Button btnStartB = findViewById(R.id.btnStartB);
        Button btnStartC = findViewById(R.id.btnStartC);
        Button btnDialog  = findViewById(R.id.btnDialog);
        Button btnCloseApp = findViewById(R.id.btnClose);

        startBackgroundCounter();

        btnStartB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityB.class);
                startActivity(intent);
            }
        });


        btnStartC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityC.class);
                startActivity(intent);
            }
        });

        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(intent);
            }
        });


        btnCloseApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });
    }

    private void startBackgroundCounter() {
        final MyApp app = (MyApp) getApplication();

        backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    int currentVal = app.getThreadCounter();
                    app.setThreadCounter(currentVal + 1);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Counter.setText(String.format("%04d", app.getThreadCounter()));
                        }
                    });

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                }
            }
        });
        backgroundThread.start();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        RestartCount++;
        OnRestartCounter.setText("onRestart() count: " + RestartCount);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunning = false;
        backgroundThread.interrupt();
    }
}