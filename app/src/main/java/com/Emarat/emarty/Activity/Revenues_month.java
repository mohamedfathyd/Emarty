package com.Emarat.emarty.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.Emarat.emarty.Adapter.RecyclerAdapter_recervations;
import com.Emarat.emarty.R;
import com.Emarat.emarty.model.Apiclient_home;
import com.Emarat.emarty.model.Reservation_content;
import com.Emarat.emarty.model.apiinterface_home;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Revenues_month extends AppCompatActivity {
    Toolbar toolbar;
    ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter_recervations recyclerAdapter_secondry;
    private List<Reservation_content> contactList;
    private apiinterface_home apiinterface;
    int id = 0;
    String name;
ImageView aa;
    Spinner spinner;
    int month;
    String Date;
    int user_id;
    int amoun=0;
    int sec_id;
    int Emara_num;
    private SharedPreferences sharedpref;
    private SharedPreferences.Editor edt;
    Typeface myTypeface;
    Intent intent;
    TextView textView,amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revenues_month);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_subject);
        textView = (TextView) findViewById(R.id.toolbar_title);
        amount=(TextView)findViewById(R.id.amount);
        progressBar.setVisibility(View.GONE);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        intent = getIntent();
        aa=findViewById(R.id.aa);
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
                }
                if (spinner.getSelectedItem().toString().equals("فبراير")) {
                    month=2;
                    fetchInfo();
                }
                if (spinner.getSelectedItem().toString().equals("مارس")) {
                    month=3;
                    fetchInfo();
                }
                if (spinner.getSelectedItem().toString().equals("ابريل")) {
                    month=4;
                    fetchInfo();
                }
                if (spinner.getSelectedItem().toString().equals("مايو")) {
                    month=5;
                    fetchInfo();
                }
                if (spinner.getSelectedItem().toString().equals("يونيو")) {
                    month=6;
                    fetchInfo();
                }
                if (spinner.getSelectedItem().toString().equals("يوليو")) {
                    month=7;
                    fetchInfo();
                }
                if (spinner.getSelectedItem().toString().equals("اغسطس")) {
                    month=8;
                    fetchInfo();
                }
                if (spinner.getSelectedItem().toString().equals("سبتمبر")) {
                    month=9;
                    fetchInfo();
                }  if (spinner.getSelectedItem().toString().equals("اكتوبر")) {
                    month=10;
                    fetchInfo();
                }
                if (spinner.getSelectedItem().toString().equals("نوفمبر")) {
                    month=11;
                    fetchInfo();
                }
                if (spinner.getSelectedItem().toString().equals("ديسمبر")) {
                    month=12;
                    fetchInfo();
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
        recyclerView = (RecyclerView) findViewById(R.id.review);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_subject);
        progressBar.setVisibility(View.VISIBLE);
        layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        aa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addtocsv();
            }
        });
    }

    public void fetchInfo() {
        amoun=0;
        progressBar.setVisibility(View.VISIBLE);
        apiinterface = Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<List<Reservation_content>> call = apiinterface.get_all_recervations_month(Emara_num,month,Date);
        call.enqueue(new Callback<List<Reservation_content>>() {
            @Override
            public void onResponse(Call<List<Reservation_content>> call, Response<List<Reservation_content>> response) {
                contactList = response.body();
                progressBar.setVisibility(View.GONE);
                for(int i=0;i<contactList.size();i++){
                    amoun+=contactList.get(i).getPrice();
                }
                amount.setText(amoun+""+"ريال");
                recyclerAdapter_secondry = new RecyclerAdapter_recervations (Revenues_month.this, contactList);
                recyclerView.setAdapter(recyclerAdapter_secondry);


            }

            @Override
            public void onFailure(Call<List<Reservation_content>> call, Throwable t) {

            }
        });
    }


    public void  addtocsv(){

        final ArrayList<String[]> data = new ArrayList<>();
        final String[] a=new String[100];
        final String[] b=new String[100];
        final String[] c=new String[100];
        final String[] d=new String[100];
        final String[] e=new String[100];
        final String[] f=new String[100];
        final String[] g=new String[100];
        for(int i= 0;i<contactList.size();i++){
            a[i]=contactList.get(i).getName();
            b[i]=contactList.get(i).getPhone();
            c[i]=contactList.get(i).getDuration();
            d[i]=contactList.get(i).getPrice()+"ريال";
            e[i]=contactList.get(i).getDate_from();
            f[i]=contactList.get(i).getDate_to();
            g[i]=contactList.get(i).getMonths();

        }
        data.add(a);
        data.add(b);
        data.add(c);
        data.add(d);
        data.add(e);
        data.add(f);
        data.add(g);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("اسم الملف ليتم حفظه : ");

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    File root = new File(Environment.getExternalStorageDirectory(), "الدفتر الألكترونى");
                    if (!root.exists()) {
                        root.mkdirs();
                    }
                    File gpxfile = new File(root, input.getText().toString()+".txt");
                    FileWriter writer = new FileWriter(gpxfile);
                    writer.append( "        المجموع         :" +amoun+""+"ريال"+"\n\n\n") ;
                    for(int i= 0;i<contactList.size();i++){
                        writer.append("الاسم :" +contactList.get(i).getName()+"\n");
                        writer.append("رقم الهاتف :"+ contactList.get(i).getPhone()+"\n");
                        writer.append("المده  :"+ contactList.get(i).getDuration()+"\n");
                        writer.append("السعر  :"+ contactList.get(i).getPrice()+"ريال"+ "\n\n" );
                        writer.append("___________________________________________________________"+"\n");



                    }
                    writer.flush();
                    writer.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }
}
