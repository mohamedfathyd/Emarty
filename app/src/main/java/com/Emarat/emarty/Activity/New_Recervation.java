package com.Emarat.emarty.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.Emarat.emarty.R;
import com.Emarat.emarty.model.Apiclient_home;
import com.Emarat.emarty.model.apiinterface_home;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class New_Recervation extends AppCompatActivity {
    EditText input_dateto,input_name,input_phone,input_id,input_price,input_datefrom,input_duration;
    ImageView image;
    AppCompatButton btn_signup;
    Spinner spinner;
    String current_date;
    Toolbar toolbar;
    final Calendar myCalendar = Calendar.getInstance();
    Bitmap bitmap;
    int month;
    int dataentry_id;
int emara_num;
    private apiinterface_home apiinterface;
    private SharedPreferences sharedpref;
    private SharedPreferences.Editor edt;
    Call<ResponseBody> call = null;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__recervation);
        Intializer();
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Droid.ttf", true);

        sharedpref = getSharedPreferences("ManoAd", Context.MODE_PRIVATE);
        edt = sharedpref.edit();
   emara_num=sharedpref.getInt("emara_num",0);
        dataentry_id=sharedpref.getInt("id",0);
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
                new DatePickerDialog(New_Recervation.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel1();
            }

        };

        input_dateto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(New_Recervation.this, date1, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchInfo();
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
    }
    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        input_datefrom.setText(sdf.format(myCalendar.getTime()));
    }
    private void updateLabel1() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        input_dateto.setText(sdf.format(myCalendar.getTime()));
    }
            public void Intializer(){
        input_dateto=findViewById(R.id.input_dateto);
        input_name=findViewById(R.id.input_name);
        input_id=findViewById(R.id.input_id);
        input_datefrom=findViewById(R.id.input_datefrom);
        input_phone=findViewById(R.id.input_phone);
        input_price=findViewById(R.id.input_price);
        image=findViewById(R.id.image);
        btn_signup=findViewById(R.id.btn_signup);
        input_duration=findViewById(R.id.input_duration);
        spinner=findViewById(R.id.spin);
    }
    public void fetchInfo() {
        progressDialog = ProgressDialog.show(New_Recervation.this, "جاري أتمام عملية الحجز", "Please wait...", false, false);
        progressDialog.show();




        apiinterface = Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<ResponseBody> call = apiinterface.getcontactsmakerecervation(input_name.getText().toString(),input_phone.getText().toString()
        ,input_id.getText().toString(), Integer.parseInt(input_duration.getText().toString()),input_datefrom.getText().toString(),input_dateto.getText().toString(),
                month, Integer.parseInt(input_price.getText().toString()),emara_num,dataentry_id);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();

                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(New_Recervation.this);
                dlgAlert.setMessage("تمت عملية الحجز بنجاح ");
                dlgAlert.setTitle("الدفتر الألكترونى");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
                addd();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(New_Recervation.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void addd() {
        final String filedate="اسم المستأجر : "+input_name.getText().toString()+"\n \n "+
                "هاتف المستأجر :"+input_phone.getText().toString()+"\n\n"+
                "رقم هوية المستأجر :"+input_id.getText().toString()+"\n\n"+
                "تاريخ بدأ الايجار :"+input_datefrom.getText().toString()+"\n\n"+
                "تاريخ انتهاء الايجار :"+input_dateto.getText().toString()+"\n\n"+
                "المدة :" + input_duration.getText().toString()+"\n\n"+
                "السعر :"+ input_price.getText().toString()+"ريال"+"\n\n\n"+
                "                           الدفتر الألكترونى                            ";

                ;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("اسم الملف ليتم حفظه :");

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
                    writer.append(filedate);
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
