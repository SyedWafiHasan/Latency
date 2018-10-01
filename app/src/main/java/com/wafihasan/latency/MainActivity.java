package com.wafihasan.latency;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    String[] names = {"Red", "Brown", "Green", "Purple", "Yellow", "Blue", "Pink"};
    String[] colours = {"#F44336", "#795548", "#4CAF50", "#9C27B0", "#FFEB3B", "#3F51B5", "#E91E63"};
    Random r = new Random();
    TextView p1Area, p2Area;
    Button button1, button2;
    View view;
    Handler handler;
    Runnable runnable;
    int score1 = 0, score2 = 0, name_index, colour_index, i = 0;
    int turns = r.nextInt(20);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        p1Area = findViewById(R.id.p1Area);
        p2Area = findViewById(R.id.p2Area);
        setColours();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        if(handler != null)
            handler.removeCallbacks(runnable);
    }

    public void setColours()
    {
        name_index = r.nextInt(names.length);
        colour_index = r.nextInt(colours.length);
        handler = new Handler();
        runnable = new Runnable()
        {
            @Override
            public void run()
            {
                if (i < turns && name_index != colour_index)
                {
                    handler.postDelayed(runnable, 1000);
                    name_index = r.nextInt(names.length);
                    colour_index = r.nextInt(colours.length);
                    p1Area.setText(names[name_index]);
                    p1Area.setTextColor(Color.parseColor(colours[colour_index]));
                    p2Area.setTextColor(Color.parseColor(colours[colour_index]));
                    p2Area.setText(names[name_index]);
                    button1 = findViewById(R.id.button1);
                    button2 = findViewById(R.id.button2);
                    button1.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            p1Minus();
                        }
                    });
                    button2.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            p2Minus();
                        }
                    });
                    i++;
                }
                else
                {
                    p1Area.setText(names[name_index]);
                    p1Area.setTextColor(Color.parseColor(colours[name_index]));
                    p2Area.setTextColor(Color.parseColor(colours[name_index]));
                    p2Area.setText(names[name_index]);
                    button1 = findViewById(R.id.button1);
                    button2 = findViewById(R.id.button2);
                    button1.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            p1Plus();
                        }
                    });
                    button2.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            p2Plus();
                        }
                    });
                    handler.removeCallbacks(runnable);
                }

            }

        };
        handler.post(runnable);

    }

    public void p1Plus()
    {
        button1 = findViewById(R.id.button1);
        score1 += 1;
        button1.setText("Score : "+score1);
    }

    public void p2Plus()
    {
        button2 = findViewById(R.id.button2);
        score2 += 1;
        button2.setText("Score :"+score2);
    }

    public void p1Minus()
    {
        button1 = findViewById(R.id.button1);
        score1 -= 1;
        button1.setText("Score : "+score1);
    }

    public void p2Minus()
    {
        button2 = findViewById(R.id.button2);
        score2 -= 1;
        button2.setText("Score :"+score2);
    }
}