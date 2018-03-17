package com.example.hussnain.sunshine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Main3Activity extends AppCompatActivity {
    private TextView grademx;
    private TextView grademn;
    private TextView hm;
    private TextView loc;
    private TextView pr;
    private TextView win;
    private TextView wth;
    private TextView dnm;
    private ImageView imageView;
    private TextView dates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        grademx = findViewById(R.id.grade2);
        grademn = findViewById(R.id.grade22);
        hm = findViewById(R.id.humditytxt);
        loc = findViewById(R.id.locationtxt);
        pr = findViewById(R.id.pressurtxt);
        win = findViewById(R.id.windtxt);
        wth = findViewById(R.id.clear2);
        dnm = findViewById(R.id.dayname3);
        imageView = findViewById(R.id.icon4);
        dates = findViewById(R.id.date2);
        WeatherInformation information = (WeatherInformation) getIntent().getSerializableExtra("key");
        grademx.setText(String.valueOf(information.getMaxTemp() + "\u00B0"));
        grademn.setText(String.valueOf(information.getMinTemp() + "\u00B0"));
        hm.setText(String.valueOf(information.getHumidity() + "%"));
        loc.setText(information.getLocation());
        pr.setText(String.valueOf(information.getPressure() + "hPa"));
        win.setText(String.valueOf(information.getWind() + "km/h NE"));
        wth.setText(information.getDescription());
        dnm.setText(information.getDayofweek());
        DateFormat df = new SimpleDateFormat("MMM dd", Locale.US);
         String time_chat_s = df.format(information.getDate()*1000);
        dates.setText(time_chat_s);
        if (information.getDescription().toLowerCase().contains("rain")) {
            imageView.setImageResource(R.drawable.ic_rain);
        } else if (information.getDescription().toLowerCase().contains("cloud")) {
            imageView.setImageResource(R.drawable.ic_mobileme_logo_of_black_cloud);
        } else
            imageView.setImageResource(R.drawable.ic_sun);
    }

}
