package com.example.hussnain.sunshine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
private TextView grademx;
private TextView grademn;
private TextView hm;
private TextView loc;
private TextView pr;
private TextView win;
private  TextView wth;
private TextView dnm;
private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        grademx=findViewById(R.id.grade2);
        grademn=findViewById(R.id.grade22);
        hm=findViewById(R.id.humditytxt);
        loc=findViewById(R.id.locationtxt);
        pr=findViewById(R.id.pressurtxt);
        win=findViewById(R.id.windtxt);
        wth=findViewById(R.id.clear2);
        dnm=findViewById(R.id.dayname3);
        imageView=findViewById(R.id.icon4);
        WeatherInformation information= (WeatherInformation) getIntent().getSerializableExtra("key");
grademx.setText(String.valueOf(information.getMaxTemp()));
grademn.setText(String.valueOf(information.getMinTemp()));
hm.setText(String.valueOf(information.getHumidity()));
loc.setText(information.getLocation());
pr.setText(String.valueOf(information.getPressure()));
win.setText(String.valueOf(information.getWind()));
wth.setText(information.getDescription());
dnm.setText(information.getDayofweek());
if (information.getDescription().toLowerCase().contains("rain")){
    imageView.setImageResource(R.drawable.ic_rain);
}else if (information.getDescription().toLowerCase().contains("cloud")){
    imageView.setImageResource(R.drawable.ic_mobileme_logo_of_black_cloud);
}else
    imageView.setImageResource(R.drawable.ic_sun);
    }

}
