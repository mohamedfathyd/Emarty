package com.Emarat.emarty.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Emarat.emarty.Adapter.RecyclerAdapter_recervations;
import com.Emarat.emarty.R;
import com.Emarat.emarty.model.Apiclient_home;
import com.Emarat.emarty.model.Reservation_content;
import com.Emarat.emarty.model.apiinterface_home;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_new_Expenses extends AppCompatActivity {
    Toolbar toolbar;
    ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter_recervations recyclerAdapter_secondry;
    private List<Reservation_content> contactList;
    private apiinterface_home apiinterface;
    int id = 0;
    String name;
    ProgressDialog progressDialog;
    final Calendar myCalendar = Calendar.getInstance();
    Spinner spinner;
    int month;
    String Date;
    EditText input_name,input_price,input_datefrom;
    AppCompatButton sign;
    int user_id;
    int sec_id;
    int Emara_num;
    private SharedPreferences sharedpref;
    private SharedPreferences.Editor edt;
    Typeface myTypeface;
    Intent intent;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new__expenses);
        textView = (TextView) findViewById(R.id.toolbar_title);
        // progressBar.setVisibility(View.VISIBLE);
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
        input_name=findViewById(R.id.input_name);
        input_price=findViewById(R.id.input_phone);
        sign=findViewById(R.id.btn_signup);
        input_datefrom=findViewById(R.id.input_datefrom);
        spinner=findViewById(R.id.spin);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        input_datefrom.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Add_new_Expenses.this, date, myCalendar.get(Calendar.YEAR),0,0).show();
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinner.getSelectedItem().toString().equals("يناير")) {
                    month=1;

                }
                if (spinner.getSelectedItem().toString().equals("فبراير")) {
                    month=2;

                }
                if (spinner.getSelectedItem().toString().equals("مارس")) {
                    month=3;

                }
                if (spinner.getSelectedItem().toString().equals("ابريل")) {
                    month=4;

                }
                if (spinner.getSelectedItem().toString().equals("مايو")) {
                    month=5;

                }
                if (spinner.getSelectedItem().toString().equals("يونيو")) {
                    month=6;

                }
                if (spinner.getSelectedItem().toString().equals("يوليو")) {
                    month=7;

                }
                if (spinner.getSelectedItem().toString().equals("اغسطس")) {
                    month=8;

                }
                if (spinner.getSelectedItem().toString().equals("سبتمبر")) {
                    month=9;

                }  if (spinner.getSelectedItem().toString().equals("اكتوبر")) {
                    month=10;

                }
                if (spinner.getSelectedItem().toString().equals("نوفمبر")) {
                    month=11;

                }
                if (spinner.getSelectedItem().toString().equals("ديسمبر")) {
                    month=12;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sharedpref = getSharedPreferences("ManoAd", Context.MODE_PRIVATE);
        edt = sharedpref.edit();

        Emara_num=sharedpref.getInt("emara_num",0);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchInfo();
            }
        });
    }
    private void updateLabel() {
        String myFormat = "yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        input_datefrom.setText(sdf.format(myCalendar.getTime()));
    }
    public void fetchInfo() {
        progressDialog = ProgressDialog.show(Add_new_Expenses.this, "جاري الأضافة الى المصروفات", "Please wait...", false, false);
        progressDialog.show();




        apiinterface = Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<ResponseBody> call = apiinterface.getcontactsnewexpenses(input_name.getText().toString(),Integer.parseInt(input_price.getText().toString()),
                month,Emara_num,input_datefrom.getText().toString());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();

                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(Add_new_Expenses.this);
                dlgAlert.setMessage("تمت عملية الاضافة بنجاح ");
                dlgAlert.setTitle("الدفتر الألكترونى");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
               
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Add_new_Expenses.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
