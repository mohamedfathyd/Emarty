package com.Emarat.emarty.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.Emarat.emarty.Adapter.RecyclerAdapter_exp;
import com.Emarat.emarty.Adapter.RecyclerAdapter_recervations;
import com.Emarat.emarty.R;
import com.Emarat.emarty.model.Apiclient_home;
import com.Emarat.emarty.model.Exp_content;
import com.Emarat.emarty.model.Reservation_content;
import com.Emarat.emarty.model.apiinterface_home;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Compare extends AppCompatActivity {

    Toolbar toolbar;

    List<SliceValue> pieData = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter_recervations recyclerAdapter_secondry;
    private List<Reservation_content> contactList;
    private List<Exp_content> contactList2;
    private apiinterface_home apiinterface;
    int id = 0;
    String name;

    Spinner spinner;
    int month;
    String Date;
    int user_id;
    int sec_id;
    PieChartView pieChartView;
    int amount,amoun;
    int Emara_num;
    private SharedPreferences sharedpref;
    private SharedPreferences.Editor edt;
    Typeface myTypeface;
    Intent intent;
    TextView textView,amounta,amountb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);
        amounta=(TextView)findViewById(R.id.amounta);
        amountb=(TextView)findViewById(R.id.amountb);
        textView = (TextView) findViewById(R.id.toolbar_title);
       // pieChartView = findViewById(R.id.chart);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        intent = getIntent();

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Droid.ttf", true);

        this.setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );
        spinner=findViewById(R.id.spin);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinner.getSelectedItem().toString().equals("يناير")) {
                    month=1;
                    fetchInfo();
                    fetchInfo2();
                }
                if (spinner.getSelectedItem().toString().equals("فبراير")) {
                    month=2;
                    fetchInfo();
                    fetchInfo2();
                }
                if (spinner.getSelectedItem().toString().equals("مارس")) {
                    month=3;
                    fetchInfo();
                    fetchInfo2();
                }
                if (spinner.getSelectedItem().toString().equals("ابريل")) {
                    month=4;
                    fetchInfo();
                    fetchInfo2();
                }
                if (spinner.getSelectedItem().toString().equals("مايو")) {
                    month=5;
                    fetchInfo();
                    fetchInfo2();
                }
                if (spinner.getSelectedItem().toString().equals("يونيو")) {
                    month=6;
                    fetchInfo();
                    fetchInfo2();
                }
                if (spinner.getSelectedItem().toString().equals("يوليو")) {
                    month=7;
                    fetchInfo();
                    fetchInfo2();
                }
                if (spinner.getSelectedItem().toString().equals("اغسطس")) {
                    month=8;
                    fetchInfo();
                    fetchInfo2();
                }
                if (spinner.getSelectedItem().toString().equals("سبتمبر")) {
                    month=9;
                    fetchInfo();
                    fetchInfo2();
                }  if (spinner.getSelectedItem().toString().equals("اكتوبر")) {
                    month=10;
                    fetchInfo();
                    fetchInfo2();
                }
                if (spinner.getSelectedItem().toString().equals("نوفمبر")) {
                    month=11;
                    fetchInfo();
                    fetchInfo2();
                }
                if (spinner.getSelectedItem().toString().equals("ديسمبر")) {
                    month=12;
                    fetchInfo();
                    fetchInfo2();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sharedpref = getSharedPreferences("ManoAd", Context.MODE_PRIVATE);
        edt = sharedpref.edit();
        user_id=sharedpref.getInt("id",0);
        Emara_num=sharedpref.getInt("emara_num",0);
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String formattedDate = df.format(c.getTime());
        Date =formattedDate;
        int total=amoun+amount;
       // float a1=(amoun*100)/total;
       // float a2=(amount*100)/total;

    }

    public void fetchInfo() {
        amoun=0;

        apiinterface = Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<List<Reservation_content>> call = apiinterface.get_all_recervations_month(Emara_num,month,Date);
        call.enqueue(new Callback<List<Reservation_content>>() {
            @Override
            public void onResponse(Call<List<Reservation_content>> call, Response<List<Reservation_content>> response) {
                contactList = response.body();

                for(int i=0;i<contactList.size();i++){
                    amoun+=contactList.get(i).getPrice();
                }
                amounta.setText(amoun+""+"ريال");



            }

            @Override
            public void onFailure(Call<List<Reservation_content>> call, Throwable t) {

            }
        });
    }
    public void fetchInfo2() {
        amount=0;

        apiinterface = Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<List<Exp_content>> call = apiinterface.get_all_exp_month(Emara_num,month,Date);
        call.enqueue(new Callback<List<Exp_content>>() {
            @Override
            public void onResponse(Call<List<Exp_content>> call, Response<List<Exp_content>> response) {
                contactList2 = response.body();

                for(int i=0;i<contactList2.size();i++){
                    amount+=contactList2.get(i).getPrice();
                }
                amountb.setText(amount+""+"ريال");

            }

            @Override
            public void onFailure(Call<List<Exp_content>> call, Throwable t) {

            }
        });
    }
}
