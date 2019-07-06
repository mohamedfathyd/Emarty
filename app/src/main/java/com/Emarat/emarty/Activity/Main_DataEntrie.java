package com.Emarat.emarty.Activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.Emarat.emarty.Adapter.RecyclerAdapter_first_annonce;
import com.Emarat.emarty.R;
import com.Emarat.emarty.model.Apiclient_home;
import com.Emarat.emarty.model.apiinterface_home;
import com.Emarat.emarty.model.contact_annonce;

import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main_DataEntrie extends AppCompatActivity {
    private RecyclerAdapter_first_annonce recyclerAdapter_annonce;
    Button add_modir,delete_modir,motaba,motaba_all,add_annonce,delete_annonce;
    private List<contact_annonce> contactList_annonce;
    ProgressBar progressBar;
    private apiinterface_home apiinterface;
    int x=0;
    private RecyclerView recyclerView,recyclerView2;
    private RecyclerView.LayoutManager layoutManager1,layoutManager2;
    CountDownTimer countDownTimer;

    int y=0;

    private static final String EXTRA_TEXT = "text";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__data_entrie);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Droid.ttf", true);

        progressBar=(ProgressBar)findViewById(R.id.progressBar_subject);
        progressBar.setVisibility(View.VISIBLE);

        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true));

        recyclerView2=findViewById(R.id.recyclerview2);
        layoutManager2 = new GridLayoutManager(this, 3);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recyclerView2.setHasFixedSize(true);
        fetchInfo_annonce();


        try {


            final int counter=100*5000;

            countDownTimer=   new CountDownTimer(counter, 5000) {

                public void onTick(long millisUntilFinished) {
                    // Toast.makeText(MainActivity.this , ""+(millisUntilFinished / 1000),Toast.LENGTH_LONG).show();
                    recyclerView2.smoothScrollToPosition(y);
                    y++;
                    if(y>x){
                        y=0;
                    }
                    //here you can have your logic to set text to edittext

                }

                public void onFinish() {

                }

            }.start();}
        catch (Exception e){}
        add_modir=findViewById(R.id.add_modir);
        delete_modir=findViewById(R.id.delete_modir);

        add_modir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_DataEntrie.this,New_Recervation.class));
            }
        });
        delete_modir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_DataEntrie.this,My_recervations.class));
            }
        });

    }
    public void fetchInfo_annonce(){
        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<List<contact_annonce>> call = apiinterface.getcontacts_annonce();
        call.enqueue(new Callback<List<contact_annonce>>() {
            @Override
            public void onResponse(Call<List<contact_annonce>> call, Response<List<contact_annonce>> response) {
                try{
                    contactList_annonce = response.body();
                    if(!contactList_annonce.isEmpty()|| contactList_annonce.equals(null)){
                        progressBar.setVisibility(View.GONE);
                        x=contactList_annonce.size();
                        recyclerAdapter_annonce=new RecyclerAdapter_first_annonce(Main_DataEntrie.this,contactList_annonce,recyclerView2);
                        recyclerView2.setAdapter(recyclerAdapter_annonce);}
                    progressBar.setVisibility(View.GONE);}
                catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<List<contact_annonce>> call, Throwable t) {

            }
        });
    }}
