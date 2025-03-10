package com.example.activityapplication;
import android.app.Application;


    public class MyApp extends Application {
        private int threadCounter = 0;

        public int getThreadCounter() {
            return threadCounter;
        }

        public void setThreadCounter(int threadCounter) {
            this.threadCounter = threadCounter;
        }

    }

