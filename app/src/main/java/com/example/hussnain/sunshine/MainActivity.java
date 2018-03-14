package com.example.hussnain.sunshine;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity  {
    private Context context;
    private RecyclerView recyclerView;
    private TextView textView;
    private static final long SECONDS_ONE_DAY=86400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.context = this;
new OurAsyncTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.setting_id:
                Intent intent=new Intent(this,Main2Activity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class OurAdapter extends RecyclerView.Adapter<OurAdapter.ViewHolder>{
        private final WeatherInformation[]data;
        public OurAdapter(WeatherInformation[]data){
            this.data=data;

        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_main1,parent,false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            WeatherInformation item=data[position];
            holder.description.setText(item.getDescription());
//          holder.humidity.setText(String.valueOf(item.getHumidity()));
            holder.wgrade1.setText(String.valueOf(item.getMaxTemp()));
            holder.wgrade2.setText(String.valueOf(item.getMinTemp()));
            holder.wgrade1.setText(item.getMaxTemp()+"\u00B0");
            holder.wgrade2.setText(item.getMinTemp()+"\u00B0");
            holder.dayname.setText(item.getDayofweek());
           // holder.imageView.setImageResource(item.getImage());
            if(item.getDescription().toLowerCase().contains("rain")){

               holder.imageView.setImageResource(R.drawable.ic_rain);

            }else if (item.getDescription().toLowerCase().contains("clouds")){
               holder.imageView.setImageResource(R.drawable.ic_mobileme_logo_of_black_cloud);
            }else{
                holder.imageView.setImageResource(R.drawable.ic_sun);

            }
        }

        @Override
        public int getItemCount() {
            return data.length;
        }
       class ViewHolder extends RecyclerView.ViewHolder {
           public TextView description;
           public TextView humidity;
           public TextView wgrade1;
           public TextView wgrade2;
          public ImageView imageView;
          public TextView speed;
          public TextView location;
          public TextView dayname;
            public ViewHolder(View itemView) {
               super(itemView);
               description=itemView.findViewById(R.id.cloud);
               //humidity=itemView.findViewById(R.id.hm);
               wgrade1=itemView.findViewById(R.id.grade3);
               wgrade2=itemView.findViewById(R.id.grade4);
              dayname=itemView.findViewById(R.id.dayname2);
              imageView=itemView.findViewById(R.id.icon);
               itemView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                      int position=getAdapterPosition();
                       WeatherInformation sunshine=OurAdapter.this.data[position];

                       Intent mintent=new Intent(context,Main3Activity.class);
                        mintent.putExtra("key",sunshine);
                       startActivity(mintent);
                   }
               });
           }
       }
    }
    private class OurAsyncTask extends AsyncTask<Void,Void,Void> {
        WeatherInformation[]cities;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this,"Data Downloading",Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            if (cities!=null){
            recyclerView = findViewById(R.id.item);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            OurAdapter adapter=new OurAdapter(cities);
            recyclerView.setAdapter(adapter);

            }else
                Toast.makeText(MainActivity.this,"No data",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                String json=getfromUrl("http://api.openweathermap.org/data/2.5/forecast?q=gujrat&APPID=6deef80072e76064fb9d84408ab92987&units=metric&cnt=7");
                JSONObject jsonObject=new JSONObject(json);
                JSONArray list=jsonObject.getJSONArray("list");
                cities=new WeatherInformation[list.length()];
                for (int i = 0; i <list.length() ; i++) {
                    WeatherInformation weatherInformation=new WeatherInformation();
                    JSONObject object=list.getJSONObject(i);
                    JSONObject main=object.getJSONObject("main");
                    JSONArray weatherArray=object.getJSONArray("weather");
                    if (weatherArray.length()>0){
                        JSONObject weatherObject=weatherArray.getJSONObject(0);
                        weatherInformation.setDescription(weatherObject.getString("description"));

                    }
                    weatherInformation.setHumidity(main.getDouble("humidity"));
                    weatherInformation.setPressure(main.getDouble("pressure"));
                    JSONObject windObject=object.getJSONObject("wind");
                    weatherInformation.setWind(windObject.getDouble("speed"));
                    JSONObject cityObject=jsonObject.getJSONObject("city");
                    weatherInformation.setLocation(cityObject.getString("name"));
                    weatherInformation.setMaxTemp(main.getDouble("temp_max"));
                    weatherInformation.setMinTemp(main.getDouble("temp_min"));
                    Calendar calendar=Calendar.getInstance();
                    SimpleDateFormat sd=new SimpleDateFormat("EEEE");
                   String dayofweek;
                   if (i==0){
                       dayofweek=sd.format(calendar.getTime());
                   }else{
                       dayofweek=sd.format(new Date().getTime()+i* TimeUnit.DAYS.toMillis(1));
                   }
                   weatherInformation.setDayofweek(dayofweek);

                    cities[i]=weatherInformation;
                }

            }catch (JSONException ex){
                ex.printStackTrace();
                Log.d("Bad Exception","hkjjdsjs");
            }catch (IOException e){
                e.getStackTrace();
                e.printStackTrace();
            }
            return null;
        }
    }
    public String getfromUrl(String murl)throws IOException{
        URL url=new URL(murl);
        HttpURLConnection connection=(HttpURLConnection)url.openConnection();
        InputStream inputStream=connection.getInputStream();
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder=new StringBuilder();
        String line;
        while ((line=bufferedReader.readLine())!=null){
            stringBuilder.append(line);
        }
        line=stringBuilder.toString();
        connection.disconnect();
        inputStream.close();
        stringBuilder.delete(0,stringBuilder.length());
        return line;
    }

}


