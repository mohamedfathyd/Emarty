package com.Emarat.emarty.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.Emarat.emarty.Adapter.RecyclerAdapter_manager_delete;
import com.Emarat.emarty.R;
import com.Emarat.emarty.model.Apiclient_home;
import com.Emarat.emarty.model.apiinterface_home;
import com.Emarat.emarty.model.user_content;

import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Delete_DataEntry extends AppCompatActivity {

    Toolbar toolbar;
    ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter_manager_delete recyclerAdapter_secondry;
    private List<user_content> contactList;
    private apiinterface_home apiinterface;
    int id = 0;
    int Emara_num;
    private SharedPreferences sharedpref;
    private SharedPreferences.Editor edt;
    String name;
    int sec_id;
    Typeface myTypeface;
    Intent intent;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete__dataentry);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_subject);
        textView = (TextView) findViewById(R.id.toolbar_title);
        progressBar.setVisibility(View.VISIBLE);
        sharedpref = getSharedPreferences("ManoAd", Context.MODE_PRIVATE);
        edt = sharedpref.edit();
        Emara_num=sharedpref.getInt("emara_num",0);
        intent = getIntent();
        toolbar = (Toolbar) findViewById(R.id.app_bar);
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

        recyclerView = (RecyclerView) findViewById(R.id.review);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_subject);
        progressBar.setVisibility(View.VISIBLE);
        layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        fetchInfo();
    }

    public void fetchInfo() {
        apiinterface = Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<List<user_content>> call = apiinterface.all_dataentryformanager(Emara_num);
        call.enqueue(new Callback<List<user_content>>() {
            @Override
            public void onResponse(Call<List<user_content>> call, Response<List<user_content>> response) {
                contactList = response.body();
                progressBar.setVisibility(View.GONE);
                recyclerAdapter_secondry = new RecyclerAdapter_manager_delete(Delete_DataEntry.this, contactList);
                recyclerView.setAdapter(recyclerAdapter_secondry);


            }

            @Override
            public void onFailure(Call<List<user_content>> call, Throwable t) {

            }
        });
    }}
