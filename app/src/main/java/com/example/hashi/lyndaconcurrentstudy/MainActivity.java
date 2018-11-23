package com.example.hashi.lyndaconcurrentstudy;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "CodeRunner";

    // View object references
    private ScrollView mScroll;
    private TextView mLog;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScroll = findViewById(R.id.scrollLog);
        mLog = findViewById(R.id.tvLog);
        mProgressBar = findViewById(R.id.progress_bar);

        mLog.setText(R.string.lorem_ipsum);
    }

    public void runCode(View view)
    {
        log("The Code is running");
        displayProgressBar(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "The Code Completed");
                displayProgressBar(false);
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(runnable, 10000);
    }

    //  Clear the output, called from the onClick event in the layout file
    public void clearOutput(View v) {
        mLog.setText("");
        scrollTextToEnd();
    }

    //  Log output to logcat and the screen
    private void log(String message) {
        Log.i(TAG, message);
        mLog.append(message + "\n");
        scrollTextToEnd();
    }

    private void scrollTextToEnd() {
        mScroll.post(new Runnable() {
            @Override
            public void run() {
                mScroll.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    private void displayProgressBar(boolean display) {
        if (display) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }
}
